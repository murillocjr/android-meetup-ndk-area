package com.example.android.camera2basic;

import android.graphics.Bitmap;
import android.media.Image;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.util.Log;

import org.opencv.android.Utils;
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

    public native  String processMatJNI(long matImage);

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public void run() {
        Mat matImage = ImageUtils.convertYuv420888ToMat(mImage,false);

//        Bitmap tmp =  Bitmap.createBitmap(matImage.cols(),matImage.rows(),null, false);
//        Utils.matToBitmap(matImage, tmp);
//
//        Log.i("__bm", tmp.toString());

        String r = processMatJNI(matImage.getNativeObjAddr());

        Log.i("__bm", r);
        mFragment.updateText(r);

        mImage.close();

    }

}