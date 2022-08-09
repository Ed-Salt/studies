/**
********************************************************************************
*
*   @file       panorama.cxx
*
*   @brief      Transform a series of images into a panoramic image
*
*   @date       09/04/21
*
*   @author     Edward Salt
*
*   @todo
*
********************************************************************************
*/

#include <iostream>				// Prints out an error with 'cerr' if there is an error
#include <stdexcept>			// Generates an exception with an error message
#include <opencv2/opencv.hpp>	// For OpenCV's functions
#include <string>

using namespace std;
using namespace cv;

Mat autoCrop(const Mat& anImage);
void callback(int, void*);


//-----------------------------
int main(int argc, char** argv)
//-----------------------------
{
	if (argc < 6 && argc > 3) {

		// Open images
		Mat images[2];
		string output_filename = argv[argc - 1];
		int iCount = argc - 2;
		for (int i = 0; i < iCount; i++) {
			images[i] = imread(argv[i+1], IMREAD_COLOR);
			if (images[i].empty())
			{
				throw runtime_error("an image could not be loaded, the program will terminate");
			}
		}

		// Features
		// Detect
		Ptr<FeatureDetector> detector = ORB::create();

		vector<KeyPoint> image_keypoints[2];
		detector->detect(images[0], image_keypoints[0]);
		detector->detect(images[1], image_keypoints[1]);

		// Describe
		Ptr<DescriptorExtractor> extractor = ORB::create();
		Mat image_descriptors[2];
		extractor->compute(images[0], image_keypoints[0], image_descriptors[0]);
		extractor->compute(images[1], image_keypoints[1], image_descriptors[1]);

		// Match
		BFMatcher matcher(NORM_HAMMING);
		vector<DMatch> matches;
		matcher.match(image_descriptors[0], image_descriptors[1], matches);

		double min_distance = numeric_limits<double>::max();
		double max_distance = -numeric_limits<double>::max();

		for (int i = 0; i < matches.size(); i++)
		{
			if (matches[i].distance < min_distance) {
				min_distance = matches[i].distance;
			} 
			else if (matches[i].distance > max_distance) {
				max_distance = matches[i].distance;
			}
		}

		vector<DMatch> good_matches;
		double thresh = min_distance + (max_distance - min_distance) / 2.0;
		thresh = 44;

		for (int i = 0; i < matches.size(); i++)
		{
			if (matches[i].distance < thresh)
			{
				good_matches.push_back(matches[i]);
			}
		}

		// Draw matches
		Mat imageMatches;

		drawMatches(images[0], image_keypoints[0], images[1], image_keypoints[1], good_matches, imageMatches);
		imshow("Matches", imageMatches);
		waitKey(0);
		destroyAllWindows();

		// Warping
		std::vector<cv::Point2f> image_point_sets[2];

		// Look at each good match
		for (std::vector< cv::DMatch >::const_iterator ite = good_matches.begin();
			ite != good_matches.end(); ++ite) { 
			// Get the keypoints from the good match
			cv::KeyPoint imPoint[2];
			imPoint[0] = KeyPoint(image_keypoints[0][ite->queryIdx]);
			imPoint[1] = KeyPoint(image_keypoints[1][ite->trainIdx]);

			// Add the corresponding 2D points
			image_point_sets[0].push_back(imPoint[0].pt);
			image_point_sets[1].push_back(imPoint[1].pt);
		}

		Mat homography_matrix(findHomography(image_point_sets[1], image_point_sets[0], RANSAC));

		Mat panorama_image;
		warpPerspective(images[1], panorama_image, homography_matrix, Size(images[0].cols + images[1].cols, images[0].rows + images[1].rows));
		//*
		imshow("transformed_right_image", panorama_image);
		waitKey(0);
		//*/

		Mat left_ROI(panorama_image(Rect(0, 0, images[0].cols, images[0].rows)));
		images[0].copyTo(left_ROI);
		//*
		imshow("left_ROI", left_ROI);
		waitKey(0);
		imshow("panorama", panorama_image);
		waitKey(0);
		//*/

		panorama_image = autoCrop(panorama_image);
		imshow("panorama", panorama_image);
		waitKey(0);

		// Write the image
		if (!cv::imwrite(output_filename, panorama_image))
		{
			// The image has not been written

			// Create an error message
			std::string error_message;
			error_message = "Could not write the image \"";
			error_message += output_filename;
			error_message += "\".";

			// Throw an error
			throw error_message;
		}
	}
	else if (argc < 4) {
		throw runtime_error("insufficient arguments");
	}
	else {
		throw runtime_error("too many arguments");
	}
}

//------------------------------
Mat autoCrop(const Mat& anImage)
//------------------------------
{
	// Convert to grey scale
	Mat grey_image;
	cvtColor(anImage, grey_image, COLOR_BGR2GRAY);

	// Convert to binary
	Mat binary_image;
	threshold(grey_image, binary_image, 1, 255, THRESH_BINARY);

	// Initialise the size of the bounding box
	int width = binary_image.cols;
	int height = binary_image.rows;

	uint8_t* pixelPtr = (uint8_t*)binary_image.data;
	int cn = binary_image.channels();

	// Crop along the horizontal axis
	for (int y = binary_image.rows - 1; y > 0; --y)
	{
		bool still_black = true;
		for (int x = binary_image.cols - 1; x > 0; --x)
		{
			if (pixelPtr[y * binary_image.cols * cn + x * cn] != 0 && still_black)
			{
				still_black = false;

				if (width > x) width = x;
			}
		}
	}

	// Crop the image
	Rect bounding_rectangle(0, 0, width, height);
	binary_image = binary_image(bounding_rectangle).clone();
	pixelPtr = (uint8_t*)binary_image.data;

	// Crop along the vertical axis
	for (int x = binary_image.cols - 1; x > 0; --x)
	{
		bool still_black = true;
		for (int y = binary_image.rows - 1; y > 0; --y)
		{
			if (pixelPtr[y * binary_image.cols * cn + x * cn] != 0 && still_black)
			{
				still_black = false;

				if (height > y) height = y;
			}
		}
	}

	bounding_rectangle = Rect(0, 0, width, height);
	return (anImage(bounding_rectangle));
}
/*
void callback(int slider_value, int, void*)
{
	vector<DMatch> good_matches;
	double threshold_distance = min_distance + (max_distance - min_distance) * slider_value / 100.0;

	for (int i = 0; i < matches.size(); i++)
	{
		if (threshold_distance > matches[i].distance)
		{
			good_matches.push_back(matches[i]);
		}
	}
	Mat img_matches;
	drawMatches(images[0], keypoints1, images[1], keypoints2, good_matches, img_matches);
	imshow("Matches", img_matches);
}//*/