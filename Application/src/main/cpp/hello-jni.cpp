/*
 * Copyright (C) 2016 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 */
#include <jni.h>
#include <opencv2/core.hpp>
#include "AreaCalculator.h"

AreaCalculator areaCalculator;

extern "C"
JNIEXPORT jstring JNICALL
Java_com_example_android_camera2basic_ImageProcessor_processMatJNI(
        JNIEnv* env, jobject thiz, jlong inputImage
)
{
    jstring response;
    response = env->NewStringUTF(areaCalculator.processMat(*((Mat*)inputImage)).c_str());
    return response;

}
