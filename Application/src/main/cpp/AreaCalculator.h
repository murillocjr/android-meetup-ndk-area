/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * File:   AreaCalculator.h
 * Author: netmaster
 *
 * Created on April 22, 2018, 5:18 PM
 */

#ifndef AREACALCULATOR_H
#define AREACALCULATOR_H

#include <iostream>
#include <fstream>
#include <opencv2/opencv.hpp>


using namespace std;
using namespace cv;


class AreaCalculator {
public:
    String processMat(Mat frame);

};

#endif /* AREACALCULATOR_H */

