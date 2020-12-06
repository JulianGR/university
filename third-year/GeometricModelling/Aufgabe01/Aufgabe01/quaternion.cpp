////////////////////////////////////////////////////////////////////
//
//	Georg Umlauf, (c) 2012
//
////////////////////////////////////////////////////////////////////

#include "quaternion.h"
#include <iostream>

Quaternion::Quaternion()
{
	Re = 1;
	Im = Vector(0,0,0);
}

Quaternion::Quaternion(double re, Vector im)
{
	Re = re;
	Im = im;
}

Quaternion::Quaternion(Vector im)
{
	Re = im.getNorm2() == 0 ? 1 : 0;
	Im = im;
}

Quaternion::~Quaternion()
{
}

Quaternion Quaternion::operator + (const Quaternion& q)
{
	return Quaternion(Re + q.Re, Im + q.Im);
}

Quaternion Quaternion::operator - (const Quaternion& q)
{
	return Quaternion(Re - q.Re, Im - q.Im);
}

Quaternion Quaternion::operator * (const Quaternion& q)
{
	return Quaternion((Re * q.Re) - (Im * q.Im),
		(q.Im * Re) + (Im * q.Re) + (Im ^ q.Im));
}

Quaternion Quaternion::operator * (const double a)
{
	return Quaternion(Re * a, Im * a);
}

Quaternion Quaternion::operator / (const double a)
{
	return Quaternion(Re / a, Im / a);
}

Quaternion Quaternion::operator / (const Quaternion& q)
{
	return Quaternion(Re / q.Re, Vector(Im[0] / q.Im[0], Im[1] / q.Im[1], Im[2] / q.Im[2]));
}

double Quaternion::getNorm()
{
	return sqrt(getNorm2());
}

double Quaternion::getNorm2()
{
	return Re * Re + Im.getNorm2();
}

Quaternion Quaternion::conjugate()
{
	return Quaternion(Re, -Im);
}

Quaternion Quaternion::normalized()
{
	return getNorm2() == 0 ? Quaternion(0, Vector(0, 0, 0)) : Quaternion(Re, Im) / getNorm();
}

Quaternion Quaternion::inverse()
{
	return getNorm2() == 0 ? Quaternion(0,Vector(0,0,0)) : conjugate() / getNorm2();
}

Quaternion Quaternion::power(double t)
{
	double phi = acos(Re / getNorm());
	Vector n = Im / Im.getNorm();

	return Quaternion(cos(t * phi), n * sin(t * phi)) * pow(getNorm(), t);
}

Quaternion Quaternion::arccos()
{
	return Quaternion(acos(Re), Vector(acos(Im[0]), acos(Im[1]), acos(Im[2])));
}

Quaternion Quaternion::sinus()
{
	return Quaternion(sin(Re), Vector(sin(Im[0]), sin(Im[1]), sin(Im[2])));
}

double Quaternion::getRe()
{
	return Re;
}

Vector Quaternion::getIm()
{
	return Im;
}
