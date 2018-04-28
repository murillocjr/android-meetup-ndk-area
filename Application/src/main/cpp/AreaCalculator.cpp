/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * File:   AreaCalculator.cpp
 * Author: netmaster
 *
 * Created on April 22, 2018, 5:18 PM
 */

#include "AreaCalculator.h"


int redCount(Mat hsv)
{
    Mat1b mask1, mask2;
    inRange(hsv, Scalar(0, 70, 50), Scalar(10, 255, 255), mask1);
    inRange(hsv, Scalar(170, 70, 50), Scalar(180, 255, 255), mask2);

    Mat1b mask = mask1 | mask2;

    //imshow("Mask", mask);
    //waitKey();

    int s = cv::sum( mask )[0];
    s=s/255;

    //cout<<hsv.cols<<","<<hsv.rows<<","<<s<<"...."<<endl;

    return s;
}

int blueCount(Mat hsv)
{
    Mat1b mask;
    inRange(hsv, Scalar(110, 70, 50), Scalar(130, 255, 255), mask);


    //imshow("Mask", mask);
    //waitKey();

    int s = cv::sum( mask )[0];
    s=s/255;

    //cout<<hsv.cols<<","<<hsv.rows<<","<<s<<"...."<<endl;

    return s;
}

String AreaCalculator::processMat(Mat frame)
{

    Mat3b hsv;
    cvtColor(frame, hsv, COLOR_BGR2HSV);

    Vec3b color = hsv.at<Vec3b>(Point(200,300));
    
    int red = redCount(hsv.clone());
    int blue = blueCount(frame.clone());


    ostringstream os;
    os<<color;
    os<<",";
    os<<red;
    os<<",";
    os<<blue;
    os<<",";
    os<<frame.cols;
    os<<",";
    os<<frame.rows;

    return os.str();;
}

