#ifndef KNOTS_H
#define KNOTS_H
#include "points.h"

class Knots : public Points
{
public:
    Knots();
    void insertKnot(float value);
    void insertKnotX(float x);
    void setValueX(int i, float x);
    float getValue(int i);

    int getKnotIntervalStartIndex(double value);
    void setValue(int i, float value);
};

#endif // KNOTS_H
