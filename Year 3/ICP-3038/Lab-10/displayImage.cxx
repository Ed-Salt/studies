// Name:    displayImage
// Synopsis:displayImage infile
// Author:	Edward Salt
// Date:	28/01/2021
// Purpose:	Load, open and display an image file in a window, using OpenCV.
// ToDo:    
// Options: infile: path to an image file.

#include <exception> // Header for catching exceptions
#include <iostream>  // Header to display text in the console
#include <opencv2/opencv.hpp> // Main OpenCV header

using namespace std;
using namespace cv;

//-----------------------------
int main(int argc, char** argv)
//-----------------------------
{
    try
    {
        // Check the command line
        if (argc != 2)
        {
            // Create an error message
            std::string error_message;
            error_message = "usage: ";
            error_message += argv[0];
            error_message += " <input_image>";

            // Throw an error
            throw error_message;
        }
        std::string input_filename(argv[1]);

        // Create an image instance
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

        // Create a string to contain the window title
        string window_title;
        window_title = "Display \"";
        window_title += input_filename;
        window_title += "\"";

        // Create the window
        cv::namedWindow(window_title, cv::WINDOW_AUTOSIZE);

        // Show the image in the window
        cv::imshow(window_title, image);

        // Wait for a user input to leave the window
        cv::waitKey(0);

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
    system("pause");
#endif
#endif

    return 0;
}