CMAKE_MINIMUM_REQUIRED(VERSION 3.1)
PROJECT(ICP3038--Lab10--Intro2OpenCV)

set (CMAKE_CXX_STANDARD 11)
set(CMAKE_CXX_STANDARD_REQUIRED ON)
set(CMAKE_CXX_EXTENSIONS OFF)

IF (WIN32)
    SET (CMAKE_PREFIX_PATH ${CMAKE_PREFIX_PATH} "C:\\opencv\\build")
ENDIF (WIN32)

FIND_PACKAGE(OpenCV REQUIRED)

ADD_EXECUTABLE (displayImage   displayImage.cxx)
ADD_EXECUTABLE (rgb2grey       rgb2grey.cxx)
ADD_EXECUTABLE (logScale       logScale.cxx)
ADD_EXECUTABLE (meanFilter     meanFilter.cxx)
ADD_EXECUTABLE (medianFilter   medianFilter.cxx)
ADD_EXECUTABLE (gaussianFilter gaussianFilter.cxx)

TARGET_INCLUDE_DIRECTORIES(displayImage PUBLIC ${OpenCV_INCLUDE_DIRS})
TARGET_INCLUDE_DIRECTORIES(rgb2grey PUBLIC ${OpenCV_INCLUDE_DIRS})
TARGET_INCLUDE_DIRECTORIES(logScale PUBLIC ${OpenCV_INCLUDE_DIRS})
TARGET_INCLUDE_DIRECTORIES(meanFilter PUBLIC ${OpenCV_INCLUDE_DIRS})
TARGET_INCLUDE_DIRECTORIES(medianFilter PUBLIC ${OpenCV_INCLUDE_DIRS})
TARGET_INCLUDE_DIRECTORIES(gaussianFilter PUBLIC ${OpenCV_INCLUDE_DIRS})

TARGET_LINK_LIBRARIES (displayImage   ${OpenCV_LIBS})
TARGET_LINK_LIBRARIES (rgb2grey       ${OpenCV_LIBS})
TARGET_LINK_LIBRARIES (logScale       ${OpenCV_LIBS})
TARGET_LINK_LIBRARIES (meanFilter     ${OpenCV_LIBS})
TARGET_LINK_LIBRARIES (medianFilter   ${OpenCV_LIBS})
TARGET_LINK_LIBRARIES (gaussianFilter ${OpenCV_LIBS})

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

    IF (EXISTS "${OpenCV_DIR}/x64/vc15/bin/opencv_videoio_msmf${CV_VERSION_STRING}_64.dll")
        FILE (COPY        "${OpenCV_DIR}/x64/vc15/bin/opencv_videoio_msmf${CV_VERSION_STRING}_64.dll"
              DESTINATION "${CMAKE_CURRENT_BINARY_DIR}/")
    ELSE ()
        MESSAGE (WARNING "opencv_videoio_msmf${CV_VERSION_STRING}_64.dll is not in ${OpenCV_DIR}/x64/vc15/bin/, you have to make sure is it in the PATH or to copy it manually in your project binary directory")
    ENDIF ()

    IF (EXISTS "${OpenCV_DIR}/x64/vc15/bin/opencv_videoio_msmf${CV_VERSION_STRING}_64d.dll")
        FILE (COPY        "${OpenCV_DIR}/x64/vc15/bin/opencv_videoio_msmf${CV_VERSION_STRING}_64d.dll"
              DESTINATION "${CMAKE_CURRENT_BINARY_DIR}/")
    ELSE ()
        MESSAGE (WARNING "opencv_videoio_msmf${CV_VERSION_STRING}_64d.dll is not in ${OpenCV_DIR}/x64/vc15/bin/, you have to make sure is it in the PATH or to copy it manually in your project binary directory")
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