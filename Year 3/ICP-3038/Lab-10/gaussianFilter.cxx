// Name:    gaussianFilter
// Synopsis:gaussianFilter [-display] infile outfile [radius]
// Author:	Edward Salt
// Date:	04/02/2021
// Purpose:	Loads an image file, apply gaussian filter, and optionaly displays in a window, using OpenCV.
// ToDo:
// Options:	infile: path to input image file.
//			outfile: path to output image file.
//          radius: the kernel radius. Default value: 1. (Optional)
//          sigmaX: kernel standard deviation in X. Default value from radius (Optional)
//          sigmaY: kernel standard deviation in Y. Default value from sigmaX (Optional)
//			-display: display greyscale image in a window. (Optional)

#include <exception> // Header for catching exceptions
#include <iostream>  // Header to display text in the console
#include <opencv2/opencv.hpp> // Main OpenCV header
#include "rgb2grey.h"
#include <cstdlib>

using namespace std;
using namespace cv;

//-----------------------------
int main(int argc, char** argv)
//-----------------------------
{
    try
    {
        // Check the command line
        if (argc < 3 || argc > 7)
        {
            // Create an error message
            std::string error_message;
            error_message = "usage: ";
            error_message += argv[0];
            error_message += " <input_image>";

            // Throw an error
            throw error_message;
        }
        std::string input_filename;

        std::string output_filename;

        bool display_image = false;

        int radius = 1;

        int sigmaX = 0;

        int sigmaY = 0;

        // if -display was used (instead of out/input)
        if ((strstr(argv[1], "-display") || strstr(argv[2], "-display"))
            && argc == 3)
        {
            // Create an error message
            std::string error_message;
            error_message = "Could not open or find image \"";
            error_message += "-display";
            error_message += "\".";

            // Throw an error
            throw error_message;
        }

        //std::string* arg = argv;
        //char arg[] = { '-','d','i','s','p','l','a','y' };
        input_filename = argv[1];
        output_filename = argv[2];
        if (argc >= 4) {
            if (argv[1][0] == '-') {
                input_filename = argv[2];
                display_image = true;
            }
            else if (argv[2][0] == '-') {
                output_filename = argv[3];
                display_image = true;
            }
            else if (argv[3][0] == '-') {
                display_image = true;
            }
            else if (isdigit(argv[3][0])) {
                radius = atoi(argv[3]);
            }
            if (argc == 5) {
                if (argv[4][0] == '-') {
                    display_image = true;
                }
                else if (isdigit(argv[4][0])) {
                    if (radius == 1) {
                        radius = atoi(argv[4]);
                    }
                    else {
                        sigmaX = atoi(argv[4]);
                    }
                }
            }
            if (argc == 6) {
                if (argv[5][0] == '-') {
                    display_image = true;
                }
                else if (isdigit(argv[5][0])) {
                    if (radius == 1) {
                        radius = atoi(argv[5]);
                    }
                    else if (sigmaX == 0) {
                        sigmaX = atoi(argv[5]);
                    }
                    else {
                        sigmaY = atoi(argv[5]);
                    }
                }
            }
            if (argc == 7) {
                if (argv[6][0] == '-') {
                    display_image = true;
                }
                else if (isdigit(argv[6][0])) {
                    if (radius == 1) {
                        radius = atoi(argv[6]);
                    }
                    else {
                        sigmaY = atoi(argv[6]);
                    }
                }
            }
        }

        float kernel_width = radius * 2 + 1;
        float kernel_height = radius * 2 + 1;

        // Filter size
        cv::Size filter_size(kernel_width, kernel_height);

        if (sigmaX == 0) {
            sigmaX = filter_size.width;
        }
        if (sigmaY == 0) {
            sigmaY = sigmaX;
        }

        // Create image instances
        cv::Mat image;
        cv::Mat filtered_image;

        // Open and read the image
        image = cv::imread(input_filename, cv::IMREAD_COLOR);

        // The image has not been loaded
        if (!image.data)
        {
            // Create an error message
            std::string error_message;
            error_message = "Could not open or find the image \"";
            error_message += input_filename;
            error_message += "\".";

            // Throw an error
            throw error_message;
        }


        // Apply filter
        cv::GaussianBlur(image, filtered_image, filter_size, sigmaX, sigmaY);

        // Write the image
        if (!cv::imwrite(output_filename, filtered_image))
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
        if (display_image) {
            // Create a string to contain the window title
            string window_title;
            window_title = "Display \"";
            window_title += output_filename;
            window_title += "\"";

            // Create the window
            cv::namedWindow(window_title, cv::WINDOW_AUTOSIZE);

            // Show the image in the window
            cv::imshow(window_title, filtered_image);

            // Wait for a user input to leave the window
            cv::waitKey(0);
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
