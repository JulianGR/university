#ifndef GLWIDGET_H
#define GLWIDGET_H

#include <QGLWidget>
#include "points.h"

class GLWidget : public QGLWidget
{
    Q_OBJECT
public:
    GLWidget(QWidget *parent=0);
    ~GLWidget();

public slots:
    void setIntersection       (int    state);
    void setSelfIntersection   (int    state);
    void setEpsilonDraw        (double value);
    void setEpsilonIntersection(double value);

protected:
    void paintGL              ();
    void initializeGL         ();
    void resizeGL             (int width, int height);
    void mouseMoveEvent       (QMouseEvent *event);
    void mousePressEvent      (QMouseEvent *event);
    void mouseDoubleClickEvent(QMouseEvent *event);

    Points getCurvePoints(Points points);
    Points deCasteljau(Points points, double t);

    Points findIntersections(Points& curve1, Points& curve2);
    Points degreeElevation(Points& curve);
    Points subdivision(std::vector<Points> points, double t);
    Points intersectLineSegments(Points& curve1, Points& curve2);
    boolean crossesLineSegment(Points& base, Points& tested);
    boolean crossesLineSegment(Points& base, int i, Points& tested, int j);
    
    Points findSelfIntersections(Points& curve);
    Points removeEndPoints(Points& segment1, Points& segment2, Points& intersections);
    std::vector<Points> findSelfIntersectionParts(Points& curve);
    Points derivatives(Points& curve);
    Points derivativePoints(Points& curve, double t);
    double sumOfAngles(Points& tangents);

    void drawCurve(Points points);
    void drawPoints(Points& pointsToDraw);
    
    Points findMin(Points& points);
    Points findMax(Points& points);
    boolean intervalsIntersect(double min1, double max1, double min2, double max2);
    double dotProduct(double vector1X, double vector1Y, double vector2X, double vector2Y);
    double size(double vectorX, double vectorY);
    double pointsEqual(double vector1X, double vector1Y, double vector2X, double vector2Y, double precision);

    void extend(Points curve, double pointX, double pointY, double t);

private:
    QPointF transformPosition(QPoint p);
    Points  points;
	float   aspectx, aspecty;
    float   epsilon_draw, epsilon_intersection;
    int     clickedPoint;
    bool    doIntersection,doSelfIntersection;
};



#endif // GLWIDGET_H
