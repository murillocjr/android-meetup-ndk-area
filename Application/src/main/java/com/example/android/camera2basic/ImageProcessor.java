package com.example.android.camera2basic;

import android.media.Image;
import org.opencv.core.Mat;

/**
 * Created by netmaster on 4/24/18.
 */

public class ImageProcessor implements Runnable {

    private final Image mImage;
    private final Camera2BasicFragment mFragment;



    ImageProcessor(Image image, Camera2BasicFragment fragment) {
        mImage = image;
        mFragment = fragment;
    }

    public native  String stringFromJNI(long matImage);

    @Override
    public void run() {
        Mat matImage = ImageUtils.imageToMat(mImage);

        //Log.i("__image",stringFromJNI());
        mFragment.updateText(stringFromJNI(matImage.getNativeObjAddr()));

        mImage.close();

    }

}