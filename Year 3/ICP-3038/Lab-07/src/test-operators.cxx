#include <iostream>

#include "Image.h"
#include "gtest/gtest.h"


using namespace std;
//*
// Test the default constructor
TEST(Operators, FloatImageOperators)
{
    Image input_image({0, 1, 2, 3, 4, 5, 6 , 7}, 4, 2);

    Image image_product = 2 * input_image;
    ASSERT_EQ(image_product.getWidth(), input_image.getWidth());
    ASSERT_EQ(image_product.getHeight(), input_image.getHeight());
    
    Image image_sum = 2 + input_image;
    ASSERT_EQ(image_sum.getWidth(), input_image.getWidth());
    ASSERT_EQ(image_sum.getHeight(), input_image.getHeight());
    
    cout << image_sum << endl;
    size_t k = 0;
    for (size_t j = 0; j < image_product.getHeight(); ++j)
    {
        for (size_t i = 0; i < image_product.getWidth(); ++i, ++k)
        {
            ASSERT_NEAR(image_product(i, j), k * 2, 1e-6);
            ASSERT_NEAR(image_sum(i, j), k + 2, 1e-6);
        }
    }
}
//*/

TEST(Operators, RMSEOperators)
{
    Image I1({ 1, 1, 1, 1, 1, 1 }, 2, 3);
    // I1.getRMSE(I1); <-- must be close to 0
    ASSERT_NEAR(I1.getRMSE(I1), 0.0, 1e-6);

    //*
    // I1.getRMSE(I1 * 3); must be close to sqrt(1/6 * 6 * (1 - 3)^2), i.e. 2
    ASSERT_NEAR(I1.getRMSE(I1 * 3), 2.0, 1e-5);
  
    //*/
    // I1.getRMSE(I1 * 9); must be close to sqrt(1/6 * 6 * (1 - 9)^2), i.e. 8
    ASSERT_NEAR(I1.getRMSE(I1 * 9), 8.0, 1e-5);
    //*/
}

TEST(Operators, ZNCCOperators)
{
    Image I1({ 1, 2, 3, 4, 5, 6 }, 2, 3);
    Image I2(!I1); // Negative of I1
    Image I3({ 6, 6, 6, 0, 0, 0 }, 2, 3); // A two-tone image

    // Same image
    // I1.getZNCC(I1); <-- must be close to 1
    ASSERT_NEAR(I1.getZNCC(I1), 1.0, 1e-5);

    // I1.getZNCC(10. + 4. * I1); <-- must be close to 1
    ASSERT_NEAR(I1.getZNCC(10. + 4. * I1), 1.0, 1e-5);

    // Negative image
    // I1.getZNCC(I2); <-- must be close to -1
    ASSERT_NEAR(I1.getZNCC(I2), -1.0, 1e-5);

    // I1.getZNCC(10. + 4. * I2); <-- must be close to -1
    ASSERT_NEAR(I1.getZNCC(10. + 4. * I2), -1.0, 1e-6);

    // Different image
    // I1.getZNCC(I3); <-- must be between -1 and 1
    double value1 = I1.getZNCC(I3);
    ASSERT_GT(value1, -1.0);
    ASSERT_LT(value1, 1.0);

    // I1.getZNCC(10. + 4. * I3); <-- must be between -1 and 1
    double value2 = I1.getZNCC(10. + 4. * I3);
    ASSERT_GT(value2, -1.0);
    ASSERT_LT(value2, 1.0);
}