/**
********************************************************************************
*
*   @file       Pyramid.cxx
*
*   @brief      Create guassian and laplacian pyramids from an image. Can reconstruct the two pyramids and optionally display them.
*
*   @date       17/02/2021
*
*   @author     Edward Salt
*
*   @todo
*
********************************************************************************
*/

#include <exception> // Header for catching exceptions
#include <iostream>  // Header to display text in the console
#include <opencv2/opencv.hpp> // Main OpenCV header
#include "Pyramid.h"

using namespace std;
using namespace cv;

vector<Mat> p_laplacian_pyramid;

//-------------------------------------------------------
void createGaussianPyramid(const Mat& anOriginalImage,
    vector<Mat>& aGaussianPyramid,
    size_t aNumberOfLevels)
    //-------------------------------------------------------
{
    // Make sure the pyramid is empty
    aGaussianPyramid.clear();

    // Add the original image
    Mat source = anOriginalImage;
    aGaussianPyramid.push_back(source);

    // Add the other sizes
    for (unsigned int i(0); i < aNumberOfLevels - 1; ++i)
    {
        // Compute the new image
        Mat dst;
        pyrDown(source, dst, Size(source.cols / 2, source.rows / 2));

        // Store the new image in the pyramid
        aGaussianPyramid.push_back(dst);

        // The new image becomes the new source
        source = dst;
    }
}

//--------------------------------------------------------------
void createLaplacianPyramid(const vector<Mat>& aGaussianPyramid,
    vector<Mat>& aLaplacianPyramid)
    //--------------------------------------------------------------
{
    // Create an empty pyramid
    aLaplacianPyramid.clear();

    // Last image ID to have been processed
    unsigned int last_id(aGaussianPyramid.size());

    // Add the original image
    Mat source = aGaussianPyramid[--last_id];
    aLaplacianPyramid.push_back(source);

    for (unsigned int i = 0; i < aGaussianPyramid.size() - 1; ++i)
    {
        Mat dst;
        pyrUp(source, dst, Size(source.cols * 2, source.rows * 2));

        // Get the new source from the Gaussian pyramid
        source = aGaussianPyramid[--last_id];

        // Store the new image in the pyramid
        aLaplacianPyramid.push_back(source - dst);
    }
}

Mat reconstruct(const vector<Mat>& aLaplacianPyramid, int aLevel)
{
    Mat reconstruction;

    if (aLaplacianPyramid.size())
    {
        for (int i = 0; i < aLaplacianPyramid.size() - aLevel; ++i)
        {
            if (i == 0)
            {
                reconstruction = aLaplacianPyramid[i];
            }
            else
            {
                Mat dst;
                pyrUp(reconstruction, dst, Size(reconstruction.cols * 2, reconstruction.rows * 2));

                reconstruction = dst + aLaplacianPyramid[i];
            }
        }
    }

    return reconstruction;
}

size_t isPowerOfTwo(size_t aValue)
{
    size_t i = 1;

    while (i < aValue) i *= 2;

    return i;
}

void swapHalves(Mat& anImage1, Mat& anImage2)
{
    // Check if the two images have the same size
    if (anImage1.cols != anImage2.cols || anImage1.rows != anImage2.rows)
    {
        throw runtime_error("The two images don't have the same size. Cannot swap halves");
    }

    // Get the half width
    int half_width = anImage1.cols / 2;

    // Define the left half as a rectangle
    // Not sure why CLing is not happy
    //Rect rectangle1(0, 0, half_width, aImage1.rows);
    Rect rectangle;
    rectangle.x = 0;
    rectangle.y = 0;
    rectangle.width = half_width;
    rectangle.height = anImage1.rows;

    // Move the left half of aImage1 into aImage2, and
    // the left half of aImage2 into aImage1
    cv::Mat ROI1 = anImage1(rectangle);
    cv::Mat ROI2 = anImage2(rectangle);

    Mat temp = ROI1.clone();

    ROI2.copyTo(ROI1);
    temp.copyTo(ROI2);
}

Mat displayPyramid(const std::vector<Mat>& aPyramid)
{
    cv::Scalar background_colour(51, 34, 184, 255);
    cv::Mat window_data(256, 256, CV_8UC3, background_colour);

    if (aPyramid.size())
    {
        int EDGE = 2;
        bool ascending(true);

        Mat tmp;

        if (aPyramid.front().rows > aPyramid.back().rows)
        {
            tmp = aPyramid.front();
            ascending = true;
        }
        else
        {
            tmp = aPyramid.back();
            ascending = false;
        }

        window_data = Mat(tmp.rows * 1.5 + EDGE * 2.0,
            tmp.cols + EDGE * (aPyramid.size() + 1),
            CV_8UC3,
            background_colour);

        int x_offset(EDGE);
        int y_offset(EDGE);

        for (unsigned int i = 0; i < aPyramid.size(); ++i)
        {
            if (ascending)
            {
                tmp = aPyramid[i];
            }
            else
            {
                tmp = aPyramid[aPyramid.size() - 1 - i];
            }

            normalize(tmp, tmp, 0, 255, NORM_MINMAX, CV_8UC3);
            Mat targetROI = window_data(Rect(x_offset, y_offset, tmp.cols, tmp.rows));

            tmp.copyTo(targetROI);

            if (i == 0)
            {
                y_offset += tmp.rows + EDGE;
            }
            else
            {
                x_offset += tmp.cols + EDGE;
            }
        }
    }

    return window_data;
}

