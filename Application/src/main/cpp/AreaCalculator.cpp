#include "AreaCalculator.h"

int redCount(Mat hsv)
{
    Mat1b mask1, mask2;
    inRange(hsv, Scalar(0, 70, 50), Scalar(10, 255, 255), mask1);
    inRange(hsv, Scalar(165, 70, 50), Scalar(180, 255, 255), mask2);

    Mat1b mask = mask1 | mask2;

    //imshow("Mask", mask);
    //waitKey();

    int s = cv::sum( mask )[0];
    s=s/255;

    return s;
}

int blueCount(Mat hsv)
{
    Mat1b mask;
    inRange(hsv, Scalar(100, 50, 30), Scalar(130, 255, 255), mask);

    //imshow("Mask", mask);
    //waitKey();

    int s = cv::sum( mask )[0];
    s=s/255;

    return s;
}

String AreaCalculator::processMat(Mat frame)
{

    int factor = 10;
    resize(frame,frame,cvSize((frame.cols)/factor,(frame.rows)/factor));

    //imshow("Mask", frame);
    //waitKey();

    Mat3b hsv;
    cvtColor(frame, hsv, COLOR_BGR2HSV);

    int red = redCount(hsv.clone());
    int blue = blueCount(hsv.clone());

    double proportion = (double)red/(double)blue;


    ostringstream os;
    os<<round(proportion);
    os<<",";
    os<<proportion;
    os<<",";
    os<<red;
    os<<",";
    os<<blue;
    os<<",";

    return os.str();
}

