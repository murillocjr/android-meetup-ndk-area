cmake_minimum_required(VERSION 3.4.1)

set(OpenCV_DIR /home/oamakas/persistentLib/OpenCV-android-sdk_310/sdk/native/jni)

find_package( OpenCV REQUIRED )
if(OpenCV_FOUND)
   message("Found OpenCV")
   message("Includes: " ${OpenCV_INCLUDE_DIRS})
endif(OpenCV_FOUND)

add_library(hello-jni SHARED
            hello-jni.cpp
            AreaCalculator.cpp)

# Include libraries needed for hello-jni lib
target_link_libraries(hello-jni
                      android
                      log
                      ${OpenCV_LIBS})