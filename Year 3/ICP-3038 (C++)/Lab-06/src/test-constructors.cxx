#include <iostream>
#include "gtest/gtest.h"
#include "Image.h"

using namespace std;


TEST(TestContructors, CArrayConstructor)
{
    float p_c_array[] = { 1, 2, 3, 4, 5, 6, 7, 8 };
    Image test_c_array_constructor(p_c_array, 4, 2);

    ASSERT_EQ(test_c_array_constructor.getWidth(), 4);
    ASSERT_EQ(test_c_array_constructor.getHeight(), 2);

    size_t k = 0;
    for (size_t j = 0; j < test_c_array_constructor.getHeight(); ++j)
    {
        for (size_t i = 0; i < test_c_array_constructor.getWidth(); ++i, ++k)
        {
            ASSERT_EQ(test_c_array_constructor(i, j), p_c_array[k]);
        }
    }
}
TEST(TestContructors, DefaultConstructor)
{
    Image test_default_constructor;

    const char* file_name_C = "sky.jpg";
    string file_name_CXX = file_name_C;

    Image test_fileName_C(file_name_C);
    Image test_fileName_CXX(file_name_CXX);

    float p_c_array[] = { 1, 2, 3, 4, 5, 6, 7, 8 };
    Image test_c_array_constructor(p_c_array, 4, 2);

    vector<float> p_cxx_array = { 1, 2, 3, 4, 5, 6, 7, 8 };
    Image test_cxx_array_constructor(p_cxx_array, 2, 4);

    Image test_constant_constructor(0.0, 6, 3);

    Image test_copy_constructor = test_constant_constructor;

    ASSERT_EQ(test_default_constructor.getWidth(), 0);
    ASSERT_EQ(test_default_constructor.getHeight(), 0);
    EXPECT_TRUE(test_default_constructor.getPixelPointer() == NULL);
}

/*
int main()
{
    // Test the default constructor
    Image test_default_constructor;

    // Test the constructors from C and C++ strings
    const char* file_name_C = "sky.jpg";
    string file_name_CXX = file_name_C;

    Image test_fileName_C(file_name_C);
    Image test_fileName_CXX(file_name_CXX);

    // Test the constructor from a C array
    float p_c_array[] = {1, 2, 3, 4, 5, 6, 7, 8};
    Image test_c_array_constructor(p_c_array, 4, 2);

    // Test the constructor from a C++ STL vector
    vector<float> p_cxx_array = {1, 2, 3, 4, 5, 6, 7, 8};
    Image test_cxx_array_constructor(p_cxx_array, 2, 4);

    // Test the constructor from a constant
    Image test_constant_constructor(0.0, 6, 3);

    // Test the copy constructor
    Image test_copy_constructor = test_constant_constructor;

    cout << test_default_constructor.getWidth() << " " << test_default_constructor.getHeight() << " " << test_default_constructor.getPixelPointer() << endl << endl;
    cout << test_fileName_C << endl << endl;
    cout << test_fileName_C << endl << endl;
    cout << test_c_array_constructor << endl << endl;
    cout << test_cxx_array_constructor << endl << endl;
    cout << test_constant_constructor << endl << endl;
    cout << test_copy_constructor << endl;

    return 0;
}
//*/