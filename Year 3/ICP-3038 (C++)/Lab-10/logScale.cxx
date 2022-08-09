// Name:    logScale
// Synopsis:logScale [-display] infile outfile
// Author:	Edward Salt
// Date:	28/01/2021
// Purpose:	Loads an image file, converts it to greyscale, and optionaly displays in a window, using OpenCV.
// ToDo:
// Options:	infile: path to input image file.
//			outfile: path to output image file.
//			-display: display greyscale image in a window. (Optional)

#include <exception> // Header for catching exceptions
#include <iostream>  // Header to display text in the console
#include <opencv2/opencv.hpp> // Main OpenCV header
#include "rgb2grey.h"

using namespace std;
using namespace cv;

//-----------------------------
int main(int argc, char** argv)
//-----------------------------
{
    try
    {
        // Check the command line
        if (argc < 3 || argc > 4)
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

        input_filename = argv[1];
        output_filename = argv[2];
        if (argc == 4) {
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
            else {
                // Create an error message
                std::string error_message;
                error_message = "Optional value should be formatted: \"";
                error_message += "-display";
                error_message += "\".";

                // Throw an error
                throw error_message;
            }
        }

        // Create image instance
        cv::Mat image;

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

        // Convert to grey
        cv::Mat grey_image;
        cv::cvtColor(image, grey_image, cv::COLOR_RGB2GRAY);

        // Convert to float
        cv::Mat float_image;
        grey_image.convertTo(float_image, CV_32FC1);

        // Log transformation
        cv::Mat log_image;
        cv::log(float_image + 1.0, log_image);

        // Normalisation
        cv::Mat normalised_image;
        cv::normalize(log_image, normalised_image, 0, 255, cv::NORM_MINMAX, CV_8UC1);

        // Write the image
        if (!cv::imwrite(output_filename, normalised_image))
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
            cv::imshow(window_title, normalised_image);

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
