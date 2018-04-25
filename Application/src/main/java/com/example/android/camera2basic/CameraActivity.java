/*
 * Copyright 2017 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.example.android.camera2basic;

import android.os.Bundle;
import android.support.annotation.Keep;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import org.opencv.android.BaseLoaderCallback;
import org.opencv.android.LoaderCallbackInterface;
import org.opencv.android.OpenCVLoader;

public class CameraActivity extends AppCompatActivity {

    Camera2BasicFragment fragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_camera);
        if (null == savedInstanceState) {
            fragment = Camera2BasicFragment.newInstance();
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.container, fragment)
                    .commit();
        }

        if (!OpenCVLoader.initDebug()) {
            Log.d("FILE", "Internal OpenCV library not found. Using OpenCV Manager for initialization");
            OpenCVLoader.initAsync(OpenCVLoader.OPENCV_VERSION_3_0_0, this, mLoaderCallback);
        } else {
            Log.d("FILE", "OpenCV library found inside package. Using it!");
            mLoaderCallback.onManagerConnected(LoaderCallbackInterface.SUCCESS);
        }
    }

    private BaseLoaderCallback mLoaderCallback = new BaseLoaderCallback(this) {
        @Override
        public void onManagerConnected(int status) {
            switch (status) {
                case LoaderCallbackInterface.SUCCESS: {
                    //System.loadLibrary("hello-jnicallback");
                }
                break;
                default: {
                    super.onManagerConnected(status);
                }
                break;
            }
        }
    };

    //////////////////From JNI Sample
    int hour = 0;
    int minute = 0;
    int second = 0;
    //TextView tickView;

    @Override
    public void onResume() {
        super.onResume();
        hour = minute = second = 0;
        //((TextView)findViewById(R.id.hellojniMsg)).setText(stringFromJNI());
        Log.i("__jni",stringFromJNI());
        startTicks();
    }

    @Override
    public void onPause () {
        super.onPause();
        StopTicks();
    }

    /*
     * A function calling from JNI to update current timer
     */
    @Keep
    private void updateTimer() {
        ++second;
        if(second >= 60) {
            ++minute;
            second -= 60;
            if(minute >= 60) {
                ++hour;
                minute -= 60;
            }
        }
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                String ticks = "" + CameraActivity.this.hour + ":" +
                        CameraActivity.this.minute + ":" +
                        CameraActivity.this.second;
                //CameraActivity.this.tickView.setText(ticks);
                Log.i("__jni",ticks);
                if (fragment!=null)
                    fragment.updateText(ticks);

            }
        });
    }
    static {
        System.loadLibrary("hello-jnicallback");
    }
    public native  String stringFromJNI();
    public native void startTicks();
    public native void StopTicks();
}

