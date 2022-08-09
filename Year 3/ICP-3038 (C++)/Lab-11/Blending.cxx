/**
********************************************************************************
*
*   @file       Blending.cxx
*
*   @brief		Blend two images
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

//-----------------------------
int main(int argc, char** argv)
//-----------------------------
{

    try
    {
        // Check the command line
        if (argc < 4 || argc > 5)
        {
            // Create an error message
            std::string error_message;
            error_message = "usage: ";
            error_message += argv[0];
            error_message += " <input_image>";

            // Throw an error
            throw error_message;
        }
        std::string input_filename[2];

        std::string output_filename;

        size_t levels;

        //bool display_image = false;

        // if -display was used (instead of out/input)
        if ((strstr(argv[1], "-display") || strstr(argv[2], "-display")
            || strstr(argv[3], "-display"))
            && argc <= 4)
        {
            // Create an error message
            std::string error_message;
            error_message = "Could not open or find image \"";
            error_message += "-display";
            error_message += "\".";

            // Throw an error
            throw error_message;
        }

        input_filename[0] = argv[1];
        input_filename[1] = argv[2];
        output_filename = argv[3];
        levels = std::stoi(argv[4]);;

        // Create image instances
        Mat images[2];

        // Open and read the images
        for (int i = 0; i < 2; i++) {
            images[i] = imread(input_filename[i], IMREAD_COLOR);
            if (!images[i].data)
            {
                throw " No data! -- Exiting the program ";
            }
        }


        // The image has not been loaded
        if (!images[0].data || !images[1].data)
        {
            // Create an error message
            std::string error_message;
            error_message = "One of the images \"";
            error_message += input_filename[0] + "\", or \"" + input_filename[1];
            error_message += "\" could not be found or opened.";

            // Throw an error
            throw error_message;
        }

        // Convert images to float

        images[0].convertTo(images[0], CV_32FC3);
        images[1].convertTo(images[1], CV_32FC3);

        // Check images have the same size

        if (images[0].rows != images[1].rows || images[0].cols != images[1].cols)
        {
            throw "The two images don't have the same size -- Exiting the program";
        }

        for (int i = 0; i < 2; i++) {
            size_t width = isPowerOfTwo(images[i].cols);
            size_t height = isPowerOfTwo(images[i].rows);

            if (width != images[i].cols || height != images[i].rows)
            {
                throw "Both image's size must be a power of two. -- Exiting the program";
            }
        }

        // Gaussian Pyramids
        vector<Mat> imageGaussian[2];
        createGaussianPyramid(images[0], imageGaussian[0], levels);
        createGaussianPyramid(images[1], imageGaussian[1], levels);

        Mat imageGaussian_vis[2];


        for (int i = 0; i < 2; i++) {

            // Display & save

            imageGaussian_vis[i] = displayPyramid(imageGaussian[i]);

            imwrite("gaussian_pyramid_" + input_filename[i], imageGaussian_vis[i]);

            // Write the image
            if (!cv::imwrite("gaussian_pyramid_" + input_filename[i], imageGaussian_vis[i]))
            {
                // The image has not been written

                // Create an error message
                std::string error_message;
                error_message = "Could not write the image \"";
                error_message += "gaussian_pyramid_" + input_filename[i];
                error_message += "\".";

                // Throw an error
                throw error_message;
            }

            // Create a string to contain the window title
            string window_title;
            window_title = "Display \"";
            window_title += "gaussian_pyramid_" + input_filename[i];
            window_title += "\"";

            // Create the window
            cv::namedWindow(window_title, cv::WINDOW_AUTOSIZE);

            // Show the image in the window
            cv::imshow(window_title, imageGaussian_vis[i]);

            // Wait for a user input to leave the window
            cv::waitKey(0);
            //}

        }

        // Laplacian Pyramids
        vector<Mat> imageLaplacian[2];
        createLaplacianPyramid(imageGaussian[0], imageLaplacian[0]);
        createLaplacianPyramid(imageGaussian[1], imageLaplacian[1]);

        Mat imageLaplacian_vis[2];


        for (int i = 0; i < 2; i++) {

            // Display & save

            imageLaplacian_vis[i] = displayPyramid(imageLaplacian[i]);

            imwrite("laplacian_pyramid_" + input_filename[i], imageLaplacian_vis[i]);

            // Write the image
            if (!cv::imwrite("laplacian_pyramid_" + input_filename[i], imageLaplacian_vis[i]))
            {
                // The image has not been written

                // Create an error message
                std::string error_message;
                error_message = "Could not write the image \"";
                error_message += "laplacian_pyramid_" + input_filename[i];
                error_message += "\".";

                // Throw an error
                throw error_message;
            }

            // Create a string to contain the window title
            string window_title;
            window_title = "Display \"";
            window_title += "laplacian_pyramid_" + input_filename[i];
            window_title += "\"";

            // Create the window
            cv::namedWindow(window_title, cv::WINDOW_AUTOSIZE);

            // Show the image in the window
            cv::imshow(window_title, imageLaplacian_vis[i]);

            // Wait for a user input to leave the window
            cv::waitKey(0);
            //}

        }

        // Swapping halves

        for (size_t i = 0; i < imageLaplacian[0].size(); ++i)
        {
            swapHalves(imageLaplacian[0][i], imageLaplacian[1][i]);
        }        
        
        Mat morphLaplacian_vis[2];


        for (int i = 0; i < 2; i++) {

            // Display & save

            morphLaplacian_vis[i] = displayPyramid(imageLaplacian[i]);
             
            string fname = "morph_laplacian_pyramid_" + to_string(i + 1) + ".png";
            imwrite(fname, morphLaplacian_vis[i]);

            // Write the image
            if (!cv::imwrite(fname, morphLaplacian_vis[i]))
            {
                // The image has not been written

                // Create an error message
                std::string error_message;
                error_message = "Could not write the image \"";
                error_message += fname;
                error_message += "\".";

                // Throw an error
                throw error_message;
            }

            // Create a string to contain the window title
            string window_title;
            window_title = "Display \"";
            window_title += fname;
            window_title += "\"";

            // Create the window
            cv::namedWindow(window_title, cv::WINDOW_AUTOSIZE);

            // Show the image in the window
            cv::imshow(window_title, morphLaplacian_vis[i]);

            // Wait for a user input to leave the window
            cv::waitKey(0);
            //}

        }

        // Reconstruction

        Mat reconstruction[2];

        reconstruction[0] = reconstruct(imageLaplacian[0], 0);
        reconstruction[1] = reconstruct(imageLaplacian[1], 0);

        for (int i = 0; i < 2; i++) {

            // Display & save

            reconstruction[i].convertTo(reconstruction[i], CV_8UC3);

            string fname = "synthesis" + to_string(i + 1) + ".png";
            imwrite(fname, reconstruction[i]);

            // Write the image
            if (!cv::imwrite(fname, reconstruction[i]))
            {
                // The image has not been written

                // Create an error message
                std::string error_message;
                error_message = "Could not write the image \"";
                error_message += fname;
                error_message += "\".";

                // Throw an error
                throw error_message;
            }

            // Create a string to contain the window title
            string window_title;
            window_title = "Display \"";
            window_title += fname;
            window_title += "\"";

            // Create the window
            cv::namedWindow(window_title, cv::WINDOW_AUTOSIZE);

            // Show the image in the window
            cv::imshow(window_title, reconstruction[i]);

            // Wait for a user input to leave the window
            cv::waitKey(0);
            //}

        }
    }
    // An error occured
    catch (const std::exception& error)
    {
        // Display an error message in the console
        cerr << error.what() << endl;
    }
    catch (const std::string& error)
    {
        // Display an error message in the console
        cerr << error << endl;
    }
    catch (const char* error)
    {
        // Display an error message in the console
        cerr << error << endl;
    }
    catch (...)
    {
        // Display an error message in the console
        cerr << "Unkown error caught" << endl;
    }

#ifdef WIN32
#ifdef _DEBUG
    //system("pause");
#endif
#endif

    return 0;
}
