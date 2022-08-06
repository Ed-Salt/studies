/**
********************************************************************************
*
*   @file       MotionDetection.cxx
*
*   @brief      Removes background around foreground objects in a video
*
*   @date       28/03/21
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

Mat cleanBinaryImage(const Mat& aBinaryImage, int elementSize = 5);

Mat getForegroundMask(const Mat& aBackground, const Mat& aNewFrame, int aThreshold = 128);

//-----------------------------
int main(int argc, char** argv)
//-----------------------------
{
    // Open the default camera (see the 0 below)
    VideoCapture video_input(0);
    if (argc > 1) { // Change webcam input to video file
        video_input = VideoCapture(argv[1]);
    }    

    // Check VideoCapture documentation.
    if (!video_input.isOpened())
        throw runtime_error("OpenCV found no webcam or video, the program will terminate");
    


    // Create windows
    if (argc > 1) { // Video
        namedWindow("Video", WINDOW_GUI_EXPANDED);
    }
    else { // Webcam
        namedWindow("Webcam", WINDOW_GUI_EXPANDED);
    }
    namedWindow("Background", WINDOW_GUI_EXPANDED);
    namedWindow("Absolute difference", WINDOW_GUI_EXPANDED);
    namedWindow("Foreground mask", WINDOW_GUI_EXPANDED);
    namedWindow("Foreground", WINDOW_GUI_EXPANDED);

    Mat background;

    int threshold_value = 128;
    createTrackbar("Threshold: ", "Foreground mask", &threshold_value, 255, NULL);

    // Grab a new frame
    Mat frame;
    video_input >> frame;

    int FPS = video_input.get(CAP_PROP_FPS);

    VideoWriter video_output("output.avi", VideoWriter::fourcc('M', 'J', 'P', 'G'), FPS, Size(frame.cols, frame.rows));

    // Set background
    background = frame;
    cv::cvtColor(background, background, COLOR_BGR2GRAY);
    imshow("Background", background);
    medianBlur(background, background, 3); //5
    background.convertTo(background, CV_32F);

    int key = -1;
    while (key != 27 && key != 'q')
    {
        // Grab a new frame
        video_input >> frame;

        // Make sure everything went well
        if (frame.empty())
        {
            if (argc > 1) { // Video
                video_input.set(CAP_PROP_POS_FRAMES, 0);
            }
            else { // Webcam
                video_input.release(); // We are now done with the camera, stop it
                throw runtime_error("OpenCV cannot grab a new frame from the camera, the program will terminate");
            }            
        }
        // There is frame
        else
        {
            // Display the image
            if (argc > 1) { // Video
                imshow("Video", frame);
            }
            else { // Webcam
                imshow("Webcam", frame);
            }

            // Save the frame in the output file
            video_output << frame;

            // This is the background
            if (key == 'b')
            {
                /* // Setting background
                background = frame;
                cv::cvtColor(background, background, COLOR_BGR2GRAY);
                imshow("Background", background);
                medianBlur(background, background, 5);
                background.convertTo(background, CV_32F);
                //*/
            }
            // This is not the background
            else
            {
                // The background exists
                if (!background.empty())
                {
                    Mat foreground_mask = getForegroundMask(background, frame, threshold_value);

                    // Apply the foreground mask
                    Mat clean;
                    frame.copyTo(clean, foreground_mask);

                    int thresh = 100;
                    Mat canny_output;
                    Canny(foreground_mask, canny_output, thresh, thresh * 2);
                    vector<vector<Point> > contours;
                    vector<Vec4i> hierarchy;
                    findContours(canny_output, contours, hierarchy, RETR_TREE, CHAIN_APPROX_SIMPLE);
                    for (size_t i = 0; i < contours.size(); i++)
                    {
                        Scalar color = Scalar(0, 0, 255);
                        drawContours(clean, contours, (int)i, color, 2, LINE_8, hierarchy, 0);
                    }

                    imshow("Foreground", clean);
                }
            }
            //

        }

        key = waitKey(1);
    }

    destroyAllWindows();

    video_input.release();

    video_output.release();
}

Mat cleanBinaryImage(const Mat& aBinaryImage, int elementSize)
{
    Mat output;

    Mat element = getStructuringElement(MORPH_ELLIPSE,
        Size(elementSize, elementSize));

    morphologyEx(aBinaryImage, output, MORPH_CLOSE, element);
    morphologyEx(output, output, MORPH_OPEN, element);

    return output;
}

Mat getForegroundMask(const Mat& aBackground, const Mat& aNewFrame, int aThreshold)
{
    // Convert in greyscale
    Mat grey_frame;
    cv::cvtColor(aNewFrame, grey_frame, COLOR_BGR2GRAY);

    // Blur the image a bit
    medianBlur(grey_frame, grey_frame, 5);

    // Convert to float32
    grey_frame.convertTo(grey_frame, CV_32F);

    // Compute the foreground as the absolute difference
    Mat foreground;
    foreground = aBackground - grey_frame;
    foreground = abs(foreground);

    // Normalise the foreground
    normalize(foreground, foreground, 0, 255, NORM_MINMAX, CV_8UC1);

    // Display the foreground
    imshow("Absolute difference", foreground);

    // Apply a threshold to generate the foreground mask
    Mat foreground_mask;
    threshold(foreground, foreground_mask, aThreshold, 255, THRESH_BINARY);

    // Use mathematical morphology to clean the binary image
    foreground_mask = cleanBinaryImage(foreground_mask, 10); //15

    // Display the foreground mask
    imshow("Foreground mask", foreground_mask);

    // Return the mask
    return foreground_mask;
}