cmake_minimum_required(VERSION 3.10)

PROJECT (ICP3038-img-class VERSION 0.2)

# Use C++ 11
set(CMAKE_CXX_STANDARD 11) # C++11
set(CMAKE_CXX_STANDARD_REQUIRED ON) # C++11 is required (i.e. not optional)
set(CMAKE_CXX_EXTENSIONS OFF) # without compiler extensions like gnu++11

# Build GoogleTest
INCLUDE(cmake/External_GTest.cmake)

enable_testing()

# Compilation
ADD_EXECUTABLE(test-constructors
    include/Image.h
    src/Image.cxx
    src/test-constructors.cxx)

# Add include directories
TARGET_INCLUDE_DIRECTORIES(test-constructors PUBLIC include)
target_include_directories(test-constructors PUBLIC ${GTEST_INCLUDE_DIRS})

# Add linkage
target_link_directories(test-constructors PUBLIC ${GTEST_LIBS_DIR})
target_link_libraries(test-constructors ${GTEST_LIBRARIES})

# Add dependency
ADD_DEPENDENCIES(test-constructors googletest)

# Add the unit test
add_test (test-constructors test-constructors)
