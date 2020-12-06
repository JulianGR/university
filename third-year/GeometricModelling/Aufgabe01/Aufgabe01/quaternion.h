////////////////////////////////////////////////////////////////////
//
//	Georg Umlauf, (c) 2012
//
////////////////////////////////////////////////////////////////////
#pragma once

#include "AffineGeometry.h"
#include "AffineMap.h"

class Quaternion 
{
private:
	double Re;	// real      part
	Vector Im;	// imaginary part

public:
	// constructors/destructors
	Quaternion();									// default constructor
	Quaternion(double re, Vector im);
	Quaternion(Vector im);
	~Quaternion();

	// further necessary methods and operations...
	// arithmetic operations
	Quaternion operator + (const Quaternion& q);
	Quaternion operator - (const Quaternion& q);
	Quaternion operator * (const Quaternion& q);
	Quaternion operator * (const double a);
	Quaternion operator / (const double a);
	Quaternion operator / (const Quaternion& q);

	// other operations
	double getNorm();
	double getNorm2();
	Quaternion normalized();
	Quaternion conjugate();
	Quaternion inverse();
	Quaternion power(double t);
	Quaternion arccos();
	Quaternion sinus();

	// getters
	double getRe();
	Vector getIm();
};