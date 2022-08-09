cmake_minimum_required(VERSION 3.10)

PROJECT (ICP3038-img-class VERSION 0.2)


# Use C++ 11
set(CMAKE_CXX_STANDARD 11) # C++11
set(CMAKE_CXX_STANDARD_REQUIRED ON) # C++11 is required (i.e. not optional)
set(CMAKE_CXX_EXTENSIONS OFF) # without compiler extensions like gnu++11

# Find OpenCV

# Add where OpenCV might be installed (look in D: first, then in C:)
IF (WIN32)
    SET (CMAKE_PREFIX_PATH ${CMAKE_PREFIX_PATH} "D:/opencv/build")
    SET (CMAKE_PREFIX_PATH ${CMAKE_PREFIX_PATH} "C:/opencv/build")
ENDIF (WIN32)

FIND_PACKAGE(OpenCV REQUIRED)
IF(OpenCV_FOUND)
    add_definitions(-DHAS_OPENCV)

    # If windows is used, copy the dlls into the project directory
    SET (CV_VERSION_STRING ${OpenCV_VERSION_MAJOR}${OpenCV_VERSION_MINOR}${OpenCV_VERSION_PATCH})
    IF (WIN32)
        IF ( ${OpenCV_VERSION_MAJOR} EQUAL 4)
            IF (EXISTS "${OpenCV_DIR}/x64/vc15/bin/opencv_videoio_ffmpeg${CV_VERSION_STRING}_64.dll")
                FILE (COPY        "${OpenCV_DIR}/x64/vc15/bin/opencv_videoio_ffmpeg${CV_VERSION_STRING}_64.dll"
                      DESTINATION "${CMAKE_CURRENT_BINARY_DIR}/")
            ELSE ()
    			MESSAGE (WARNING "opencv_videoio_ffmpeg${CV_VERSION_STRING}_64.dll is not in ${OpenCV_DIR}/x64/vc15/bin/, you have to make sure is it in the PATH or to copy it manually in your project binary directory")
            ENDIF ()
    	ELSE ()
            IF (EXISTS "${OpenCV_DIR}/x64/vc15/bin/opencv_ffmpeg${CV_VERSION_STRING}_64.dll")
                FILE (COPY        "${OpenCV_DIR}/x64/vc15/bin/opencv_ffmpeg${CV_VERSION_STRING}_64.dll"
                      DESTINATION "${CMAKE_CURRENT_BINARY_DIR}/")
            ELSE ()
    			MESSAGE (WARNING "opencv_ffmpeg${CV_VERSION_STRING}_64.dll is not in ${OpenCV_DIR}/x64/vc15/bin/, you have to make sure is it in the PATH or to copy it manually in your project binary directory")
            ENDIF ()
    	ENDIF ()

        IF (EXISTS "${OpenCV_DIR}/x64/vc15/bin/opencv_world${CV_VERSION_STRING}.dll")
            FILE (COPY        "${OpenCV_DIR}/x64/vc15/bin/opencv_world${CV_VERSION_STRING}.dll"
                  DESTINATION "${CMAKE_CURRENT_BINARY_DIR}/")
        ELSE ()
    	    MESSAGE (WARNING "opencv_world${CV_VERSION_STRING}.dll is not in ${OpenCV_DIR}/x64/vc15/bin/, you have to make sure is it in the PATH or to copy it manually in your project binary directory")
        ENDIF ()

        IF (EXISTS "${OpenCV_DIR}/x64/vc15/bin/opencv_world${CV_VERSION_STRING}d.dll")
            FILE (COPY        "${OpenCV_DIR}/x64/vc15/bin/opencv_world${CV_VERSION_STRING}d.dll"
                  DESTINATION "${CMAKE_CURRENT_BINARY_DIR}/")
        ELSE ()
    	    MESSAGE (WARNING "opencv_world${CV_VERSION_STRING}d.dll is not in ${OpenCV_DIR}/x64/vc15/bin/, you have to make sure is it in the PATH or to copy it manually in your project binary directory")
        ENDIF ()
    ENDIF (WIN32)
ELSE(OpenCV_FOUND)
    MESSAGE(WARNING "OpenCV not found.")
ENDIF(OpenCV_FOUND)


# Build GoogleTest
INCLUDE(cmake/External_GTest.cmake)

# Enable unit testing
enable_testing()

# Compilation
ADD_EXECUTABLE(test-constructors
    include/Image.h
    src/Image.cxx
    src/test-constructors.cxx)

# Add dependency
ADD_DEPENDENCIES(test-constructors googletest)

# Add include directories
TARGET_INCLUDE_DIRECTORIES(test-constructors PUBLIC include)
target_include_directories(test-constructors PUBLIC ${GTEST_INCLUDE_DIRS})

IF(OpenCV_FOUND)
    target_include_directories(test-constructors PUBLIC ${OpenCV_INCLUDE_DIRS})
ENDIF(OpenCV_FOUND)

# Add linkage
target_link_directories(test-constructors PUBLIC ${GTEST_LIBS_DIR})
target_link_libraries(test-constructors ${GTEST_LIBRARIES} ${OpenCV_LIBS})

# Add the unit test
add_test (Constructors test-constructors)


# Compilation
ADD_EXECUTABLE(test-operators
    include/Image.h
    src/Image.cxx
    src/test-operators.cxx)

# Add dependency
ADD_DEPENDENCIES(test-operators googletest)

# Add include directories
TARGET_INCLUDE_DIRECTORIES(test-operators PUBLIC include)
target_include_directories(test-operators PUBLIC ${GTEST_INCLUDE_DIRS})

IF(OpenCV_FOUND)
    target_include_directories(test-operators PUBLIC ${OpenCV_INCLUDE_DIRS})
ENDIF(OpenCV_FOUND)

# Add linkage
target_link_directories(test-operators PUBLIC ${GTEST_LIBS_DIR})
target_link_libraries(test-operators ${GTEST_LIBRARIES} ${OpenCV_LIBS})

# Add the unit test
add_test (Operators test-operators)


# The documentation build is an option. Set it to ON by default
option(BUILD_DOC "Build documentation" ON)

# Check if Doxygen is installed
find_package(Doxygen)
if (DOXYGEN_FOUND)

    SET (PROJECT_NAME ICP3038-img-class)
    SET (PROJECT_DESCRIPTION "An Image class used in ICP3038 at Bangor University")

    # set input and output files
    set(DOXYGEN_IN ${CMAKE_CURRENT_SOURCE_DIR}/docs/Doxyfile.in)
    set(DOXYGEN_OUT ${CMAKE_CURRENT_BINARY_DIR}/Doxyfile)

    # Configure the file
    configure_file(${DOXYGEN_IN} ${DOXYGEN_OUT} @ONLY)

    # Add a custom target
    add_custom_target( doc_doxygen ALL
        COMMAND ${DOXYGEN_EXECUTABLE} ${DOXYGEN_OUT}
        WORKING_DIRECTORY ${CMAKE_CURRENT_BINARY_DIR}
        COMMENT "Generating API documentation with Doxygen"
        VERBATIM )
else (DOXYGEN_FOUND)
    message(WARNING "Doxygen need to be installed to generate the doxygen documentation")
endif (DOXYGEN_FOUND)
