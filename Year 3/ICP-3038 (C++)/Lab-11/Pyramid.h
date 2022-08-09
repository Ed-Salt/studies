#ifndef __Pyramid_h
#define __Pyramid_h

/**
********************************************************************************
*
*   @file       Pyramid.h
*
*   @brief      
*
*   @date       17/02/2021
*
*   @author     Edward Salt
*
*   @todo       
*
********************************************************************************
*/

//******************************************************************************
// Headers
//******************************************************************************
#include <vector>
#include <opencv2/opencv.hpp>


//--------------------------------------------------------------------------
/// Create a Gaussian pyramid.
/**
 * @param anOriginalImage:    the image to process
 * @param aGaussianPyramid:   the Gaussian pyramid
 * @param aNumberOfLevels:    the number of levels in the pyramid
 */
 //--------------------------------------------------------------------------
void createGaussianPyramid(const cv::Mat& anOriginalImage,
    std::vector<cv::Mat>& aGaussianPyramid,
    size_t aNumberOfLevels);


//--------------------------------------------------------------------------
/// Create an image to visualise a pyramid (Gaussian or Laplacian).
/**
 * @param aPyramid: the pyramid to visualise
 * @return  the visualisation of the pyramid
 */
 //--------------------------------------------------------------------------
cv::Mat displayPyramid(const std::vector<cv::Mat>& aPyramid);


//--------------------------------------------------------------------------
/// Create a Laplacian pyramid from a Gaussian pyramid.
/**
 * @param aGaussianPyramid:   the Gaussian pyramid
 * @param aLaplacianPyramid:  the corresponding Laplacian pyramid
 */
 //--------------------------------------------------------------------------
void createLaplacianPyramid(const std::vector<cv::Mat>& aGaussianPyramid,
    std::vector<cv::Mat>& aLaplacianPyramid);


//--------------------------------------------------------------------------
/// Reconstruct an image from the Laplacian pyramid at a given level.
/**
 * @param aLaplacianPyramid:    the Laplacian pyramid
 * @param aLevel:    the level
 * @return the corresponding reconstructed image
 */
 //--------------------------------------------------------------------------
cv::Mat reconstruct(const std::vector<cv::Mat>& aLaplacianPyramid, int aLevel);

// Checks an image is a power of two
size_t isPowerOfTwo(size_t aValue);

// Swaps half of an image's pixels with the other half of another image
void swapHalves(cv::Mat& anImage1, cv::Mat& anImage2);

#endif // __Pyramid_h

