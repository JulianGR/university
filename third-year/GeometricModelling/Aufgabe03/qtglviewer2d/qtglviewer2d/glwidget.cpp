#include "glwidget.h"
#include <QtGui>
#include <GL/glu.h>
#include "mainwindow.h"
#include <iostream>
#include <iostream>
#include <iostream>

GLWidget::GLWidget(QWidget *parent) : QGLWidget(parent)
{
    epsilon_draw = 0.05;

    // Hier Punkte hinzufügen: Schönere Startpositionen und anderer Grad!
    points.addPoint(-1.00,  0.5);
    points.addPoint(-0.30, -0.25);
    points.addPoint( 0.00,  0.5);
    points.addPoint( 0.30, -0.25);
    points.addPoint( 1.00,  0.5);
    points.addPoint( 0.70,  0.7);

    knots.insertKnot(0.05);
    knots.insertKnot(0.1);
    knots.insertKnot(0.3);
    knots.insertKnot(0.4);
    knots.insertKnot(0.5);
    knots.insertKnot(0.7);
    knots.insertKnot(0.9);
    //knots.insertKnot(0.95);
}

GLWidget::~GLWidget()
{
}

void GLWidget::paintGL()
{
    // clear
    glClear(GL_COLOR_BUFFER_BIT);

    // Koordinatensystem
    glColor3f(0.5,0.5,0.5);
    glBegin(GL_LINES);
    glVertex2f(-1.0, 0.0);
    glVertex2f( 1.0, 0.0);
    glVertex2f( 0.0,-1.0);
    glVertex2f( 0.0, 1.0);
    glEnd();

    // Kontrollunkte zeichnen
    glPointSize(7.0);
    glBegin(GL_POINTS);
    glColor3f(1.0,0.0,0.0);
    for (int i=0; i<points.getCount(); i++) {
        glVertex2f(points.getPointX(i),points.getPointY(i));
    }
    glEnd();

    // Kontrollpolygon zeichnen
    glColor3f(0.0,0.0,1.0);
    glBegin(GL_LINE_STRIP);
    for (int i=0; i<points.getCount(); i++) {
        glVertex2f(points.getPointX(i),points.getPointY(i));
    }
    glEnd();

    // Knoten zeichnen
    glColor3f(0.0,1.0,1.0);
    glBegin(GL_LINE_STRIP);
    for (int i=0; i<knots.getCount(); i++) {
        glVertex2f(knots.getPointX(i),knots.getPointY(i));
    }
    glEnd();
    glColor3f(1.0,0.0,1.0);
    glBegin(GL_POINTS);
    for (int i=0; i<knots.getCount(); i++) {
        glVertex2f(knots.getPointX(i),knots.getPointY(i));
    }
    glEnd();

    // Kurve zeichnen
    glColor3f(1.0,1.0,1.0);
    // AUFGABE: Hier Kurve zeichnen
    // dabei epsilon_draw benutzen
    drawCurve();
}

void GLWidget::drawCurve()
{
    // set m and n
    int m = points.getCount() - 1;
    int n = knots.getCount() - m - 2;
    int intervals = m + 1 - n;
    
    // initialize knot vector and control points for drawing
    // make B-spline start in first control point
    Points pointsToDraw = Points();
    for (int i = 0; i < n; i++) {
        pointsToDraw.addPoint(points.getPointX(0), points.getPointY(0));
    }
    for (int i = 0; i <= m; i++) {
        pointsToDraw.addPoint(points.getPointX(i), points.getPointY(i));
    }
    for (int i = 0; i < n; i++) {
        pointsToDraw.addPoint(points.getPointX(m), points.getPointY(m));
    }

    Knots knotsToDraw = Knots();
    for (int i = 1; i < knots.getCount()-1; i++) {
        knotsToDraw.insertKnot(knots.getValue(i));
    }
    for (int i = 1; i <= n; i++) {
        knotsToDraw.insertKnot(0.0);
        knotsToDraw.insertKnot(1.0);
    }

    // insert knots to change knot multiplicity to n
    for (int i = n + 1; i < knotsToDraw.getCount() - n - 1; i++) {
        double t = knotsToDraw.getValue(i);
        int multiplicity = 1;
        while(multiplicity < n) {
            insertKnot(t, &pointsToDraw, &knotsToDraw, i);
            multiplicity++;
            i++;
        }
    }

    // draw all Bezier curves
    Points points_list = Points();
    int i = 0;
    intervals = 1 + (pointsToDraw.getCount() - (n + 1)) / n;
    for (int j = 0; j < intervals; j++) {
        points_list = Points();
        for (int k = 0; k <= n; k++) {
            points_list.addPoint(pointsToDraw.getPointX(i), pointsToDraw.getPointY(i));
            i++;
        }
        i--;
        drawPoints(points_list);
        Points curvePoints1 = getCurvePoints(points_list);
        drawCurve(curvePoints1);
    }
}

// *****************************
//
// Functions from previous assignment
//
// *****************************

Points GLWidget::getCurvePoints(Points points)
{
    Points curvePoints = Points();
    for (double t = 0; t <= 1; t += epsilon_draw)
    {
        Points point = deCasteljau(points, t);
        curvePoints.addPoint(point.getPointX(0), point.getPointY(0));
    }
    curvePoints.addPoint(points.getPointX(points.getCount() - 1), points.getPointY(points.getCount() - 1));
    return curvePoints;
}

Points GLWidget::deCasteljau(Points points, double t)
{
    if (points.getCount() <= 1) return points;

    Points new_points = Points();
    for (int j = 0; j < points.getCount() - 1; j++)
    {
        double x_pos = (1.0 - t) * double(points.getPointX(j)) + t * double(points.getPointX(j + 1));
        double y_pos = (1.0 - t) * double(points.getPointY(j)) + t * double(points.getPointY(j + 1));
        new_points.addPoint(x_pos, y_pos);
    }
    return deCasteljau(new_points, t);
}

void GLWidget::drawCurve(Points points)
{
    glColor3f(1.0, 1.0, 1.0);
    glBegin(GL_LINE_STRIP);
    for (int i = 0; i < points.getCount(); i++)
    {
        glVertex2f(points.getPointX(i), points.getPointY(i));
    }
    glEnd();
}

void GLWidget::drawPoints(Points points)
{
    //glColor3f(1.0, 1.0, 1.0);
    glBegin(GL_POINTS);
    for (int i = 0; i < points.getCount(); i++)
    {
        glVertex2f(points.getPointX(i), points.getPointY(i));
    }
    glEnd();
}

// **********************************************

void GLWidget::insertKnot(double t, Points* pointsToDraw, Knots* knotsToDraw, int r)
{
    // set m and n
    int m = pointsToDraw->getCount() - 1;
    int n = knotsToDraw->getCount() - m - 2;

    // find which control points are affected
    Points aux = Points();
    for (int i = r - n; i <= r; i++) {
        if (i <= 0) {
            aux.addPoint(pointsToDraw->getPointX(0), pointsToDraw->getPointY(0));
        }
        else if (i >= pointsToDraw->getCount()) {
            aux.addPoint(pointsToDraw->getPointX(pointsToDraw->getCount() - 1), pointsToDraw->getPointY(pointsToDraw->getCount() - 1));
        }
        else {
            aux.addPoint(pointsToDraw->getPointX(i), pointsToDraw->getPointY(i));
        }
    }

    // add respective original control points and all from first step of de Boor algorithm
    Points toAdd = deBoorOneStep(aux, *knotsToDraw, t, n, r);
    Points newPoints = Points();
    for (int i = 0; i <= r - n && i < pointsToDraw->getCount(); i++) {
        newPoints.addPoint(pointsToDraw->getPointX(i), pointsToDraw->getPointY(i));
    }
    for (int i = 0; i < toAdd.getCount(); i++) {
        newPoints.addPoint(toAdd.getPointX(i), toAdd.getPointY(i));
    }
    for (int i = r; i < pointsToDraw->getCount(); i++) {
        newPoints.addPoint(pointsToDraw->getPointX(i), pointsToDraw->getPointY(i));
    }

    // assign new control point set to variable pointsToDraw
    *pointsToDraw = Points();
    for (int i = 0; i < newPoints.getCount(); i++) {
        pointsToDraw->addPoint(newPoints.getPointX(i), newPoints.getPointY(i));
    }

    // add knot to knot vector
    knotsToDraw->insertKnot(t);
}

Points GLWidget::deBoorOneStep(Points controlPoints, Knots knotVector, double t, int n, int r)
{
    Points result = Points();
    double newX, newY, alpha;
    double val1, val2;
    for (int i = 1; i < controlPoints.getCount(); i++) {
        int alphaI = i + r - n;
        if (alphaI >= knotVector.getCount()) val1 = 1.0;
        else if (alphaI < 0) val1 = 0.0;
        else val1 = knotVector.getValue(alphaI); // if index out of range, take first/last knot
        if (n + alphaI >= knotVector.getCount()) {
            result.addPoint(controlPoints.getPointX(controlPoints.getCount() - 1), controlPoints.getPointY(controlPoints.getCount() - 1));
            continue;
        }
        val2 = knotVector.getValue(n + alphaI /*+1-1*/); // j is always 1 in case we only use de Boor for knot insertion
        if (val1 == val2) {
            result.addPoint(controlPoints.getPointX(i), controlPoints.getPointY(i));
            continue;
        }
        alpha = (t - val1) / (val2 - val1);
        newX = (1.0 - alpha) * controlPoints.getPointX(i-1) + alpha * controlPoints.getPointX(i);
        newY = (1.0 - alpha) * controlPoints.getPointY(i-1) + alpha * controlPoints.getPointY(i);
        result.addPoint(newX, newY);
    }
    return result;
}


void GLWidget::initializeGL()
{
    resizeGL(width(),height());
}

void GLWidget::resizeGL(int width, int height)
{
    aspectx=1.0;
    aspecty=1.0;
    if (width>height) {
        aspectx=float(width)/height;
    }
    else {
        aspecty=float(height)/width;
    }
    glViewport(0,0,width,height);
    glMatrixMode(GL_PROJECTION);
    glLoadIdentity();
    gluOrtho2D(-aspectx,aspectx,-aspecty,aspecty);
    glMatrixMode(GL_MODELVIEW);
    glLoadIdentity();
}

QPointF GLWidget::transformPosition(QPoint p)
{
    return QPointF((2.0*p.x()/width() - 1.0)*aspectx,-(2.0*p.y()/height() - 1.0)*aspecty);
}

void GLWidget::mouseMoveEvent(QMouseEvent *event)
{
    if (event->buttons() & Qt::LeftButton) {
        QPoint pos = event->pos();
        QPointF posF = transformPosition(pos);
        if (clickedKnot >= 0) {
            knots.setValueX(clickedKnot,posF.x());
        }
        else {
            points.setPointX(clickedPoint,posF.x());
            points.setPointY(clickedPoint,posF.y());
        }
        update();
    }
}

void GLWidget::mousePressEvent(QMouseEvent *event)
{
    QPoint pos = event->pos();
    QPointF posF = transformPosition(pos);
    if (event->buttons() & Qt::LeftButton) {
        clickedPoint = points.getClosestPoint(posF.x(),posF.y());
        clickedKnot = knots.getClosestPoint(posF.x(),posF.y());
        if (points.getDistance(clickedPoint,posF.x(),posF.y()) <=
             knots.getDistance(clickedKnot, posF.x(),posF.y()))
        { // point was clicked
            points.setPointX(clickedPoint,posF.x());
            points.setPointY(clickedPoint,posF.y());
            clickedKnot = -1;
        }
        else
        { // knot was clicked
            knots.setValueX(clickedKnot,posF.x());
            clickedPoint = -1;
        }
    }
    if (event->buttons() & Qt::RightButton) {
        // AUFGABE: Hier Knoten in eine B-Spline-Kurve einfügen.
        double t = (posF.x() + 0.9) / 1.8;
        int i = knots.getKnotIntervalStartIndex(t);
        insertKnot(t, &points, &knots, i);
    }
    update();
}

void GLWidget::setEpsilonDraw(double value)
{
    epsilon_draw = value;
}
