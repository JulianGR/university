#include "glwidget.h"
#include <QtGui>
#include <GL/glu.h>
#include "mainwindow.h"
#include <math.h>

GLWidget::GLWidget(QWidget *parent) : QGLWidget(parent)
{
    doIntersection       = false;
    doSelfIntersection   = false;
    epsilon_draw         = (float)0.05;
    epsilon_intersection = (float)0.000005;

    // Hier Punkte hinzufügen (schönere Startpositionen!)
    points.addPoint(-1.00, -0.5);
    points.addPoint(-0.75, -0.5);
    points.addPoint(-0.50, -0.5);
    points.addPoint(-0.25, -0.5);
    points.addPoint( 0.00, -0.5);

    points.addPoint( 0.25,  0.5);
    points.addPoint( 0.50,  0.5);
    points.addPoint( 0.75,  0.5);
    points.addPoint( 1.00,  0.5);
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
    glColor3f(1.0,0.0,0.0);

    // Punkte
    glPointSize(7.0);
    glBegin(GL_POINTS);
    for (int i=0; i<points.getCount(); i++) {
        glVertex2f(points.getPointX(i),points.getPointY(i));
    }
	
    glEnd();

    // Hüllpolygone zeichnen
    glColor3f(0.0,0.0,1.0);
    glBegin(GL_LINE_STRIP);
    for (int i=0; i<5; i++) {
        glVertex2f(points.getPointX(i),points.getPointY(i));
    }
    glEnd();
    glBegin(GL_LINE_STRIP);
    for (int i=5; i<points.getCount(); i++) {
        glVertex2f(points.getPointX(i),points.getPointY(i));
    }
    glEnd();

    // Kurve
    glColor3f(1.0,1.0,1.0);
    // AUFGABE: Hier Kurve zeichnen - DONE
    // dabei epsilon_draw benutzen
    Points points_list1 = Points();
    for (int i = 0; i < 5; i++) {
        points_list1.addPoint(points.getPointX(i), points.getPointY(i));
    }
    Points curvePoints1 = getCurvePoints(points_list1);
    drawCurve(curvePoints1);
    
    Points points_list2 = Points();
    for (int i = 5; i < points.getCount(); i++) {
        points_list2.addPoint(points.getPointX(i), points.getPointY(i));
    }
    Points curvePoints2 = getCurvePoints(points_list2);
    drawCurve(curvePoints2);
	
    // Schnittpunkte zeichnen
    if (doIntersection) {
        glColor3f(0.0,1.0,0.0);
        // AUFGABE: Hier Schnitte zeichnen - DONE
        // dabei epsilon_intersection benutzen
        Points intersections = findIntersections(points_list1, points_list2);
        drawPoints(intersections);
    }
    if (doSelfIntersection) {
        glColor3f(1.0,0.0,1.0);
        // AUFGABE: Hier Selbstschnitte zeichnen - DONE
        // dabei epsilon_intersection benutzen
        Points selfIntersections = findSelfIntersections(points_list1);
        drawPoints(selfIntersections);
        selfIntersections = findSelfIntersections(points_list2);
        drawPoints(selfIntersections);
    }

    

    // C(n-1)-transitions
    // By uncommenting the following if-block and commenting the previous if-block, draw Ck-transitions instead of the self-intersections.
    // (I didn't know how to add a new button or where to implement the Ck-transitions functionality)

    /*
    if (doSelfIntersection) {
        // Fixed endpoint of the new segment in [0,0]. This point isn't affected by the first half (first segment) of the curve and cannot be moved.
        extend(points_list1, 0, 0, 1.5);
    }

    */
}

// *******************************************
// * C(n-1)-transitions                      *
// *******************************************

void GLWidget::extend(Points curve, double pointX, double pointY, double t)
{
    std::vector<Points> matrix;
    matrix.reserve(curve.getCount());
    matrix.insert(matrix.begin(), curve);
    Points tmp = subdivision(matrix, t);

    // take second part of the refined curve = the new segment's control points
    // one of the endpoints is provided as an argument, because we do C(n-1)-transition, not C(n)-transition
    int degree = curve.getCount() - 1;
    Points newSegmentControlPoints = Points();
    newSegmentControlPoints.addPoint(pointX, pointY);
    for (int i = degree + 1; i <= degree*2; i++) {
        newSegmentControlPoints.addPoint(tmp.getPointX(i), tmp.getPointY(i));
    }
    
    // draw new segment and its control points
    glColor3f(0.5, 0.5, 1.0);
    drawPoints(newSegmentControlPoints);
    glColor3f(0.5, 0.7, 1.0); 
    Points curvepoints = getCurvePoints(newSegmentControlPoints);
    drawCurve(curvepoints);
}


// *******************************************
// * Compute curve using de Casteljau        *
// *******************************************

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


// *******************************************
// * Find intersections                      *
// *******************************************

// p.5 s.43
Points GLWidget::findIntersections(Points& curveB, Points& curveC)
{
    if (curveB.getCount() == 0 || curveC.getCount() == 0) return Points();

    // compute axis-aligned bounding boxes (instead of convex hulls)
    Points minB = findMin(curveB);
    Points minC = findMin(curveC);
    Points maxB = findMax(curveB);
    Points maxC = findMax(curveC);

    Points result = Points();

    if (intervalsIntersect(minB.getPointX(0), maxB.getPointX(0), minC.getPointX(0), maxC.getPointX(0))
        && intervalsIntersect(minB.getPointY(0), maxB.getPointY(0), minC.getPointY(0), maxC.getPointY(0))) {
        // compute maximum of second forward differences - p.5 s.40
        double maxSecondForwardDifference = 0;
        double sfdX, sfdY;
        for (int i = 0; i < curveB.getCount() - 2; i++) {
            sfdX = abs(curveB.getPointX(i) - 2 * curveB.getPointX(i + 1) + curveB.getPointX(i + 2));
            sfdY = abs(curveB.getPointY(i) - 2 * curveB.getPointY(i + 1) + curveB.getPointY(i + 2));
            if (sfdX > maxSecondForwardDifference) maxSecondForwardDifference = sfdX;
            if (sfdY > maxSecondForwardDifference) maxSecondForwardDifference = sfdY;
        }
        int m = curveB.getCount() - 1;

        // condition for subdivision of curve B
        double tmpB = m * (m - 1) * maxSecondForwardDifference;
        if (tmpB > epsilon_intersection) {
            // compute refined curve from curve B using deCasteljau (degree elevation)
            std::vector<Points> matrix;
            matrix.reserve(curveB.getCount());
            matrix.insert(matrix.begin(), curveB);
            Points curveA = subdivision(matrix, 0.5);

            // compute first part of the refined curve and its intersections with curve C
            Points curveA1 = Points();
            for (int i = 0; i <= m; i++) {
                curveA1.addPoint(curveA.getPointX(i), curveA.getPointY(i));
            }
            result.addAllPoints(findIntersections(curveA1, curveC));

            // compute second part of the refined curve and its intersections with curve C
            Points curveA2 = Points();
            for (int i = m; i <= m*2; i++) {
                curveA2.addPoint(curveA.getPointX(i), curveA.getPointY(i));
            }
            result.addAllPoints(findIntersections(curveA2, curveC));
        }
        else {
            // compute maximum of second forward differences - p.5 s.40
            maxSecondForwardDifference = 0;
            sfdX = 0;
            sfdY = 0;
            for (int i = 0; i < curveC.getCount() - 2; i++) {
                sfdX = abs(curveC.getPointX(i) - 2 * curveC.getPointX(i + 1) + curveC.getPointX(i + 2));
                sfdY = abs(curveC.getPointY(i) - 2 * curveC.getPointY(i + 1) + curveC.getPointY(i + 2));
                if (sfdX > maxSecondForwardDifference) maxSecondForwardDifference = sfdX;
                if (sfdY > maxSecondForwardDifference) maxSecondForwardDifference = sfdY;
            }
            int n = curveC.getCount() - 1;

            // condition for subdivision of curve C
            double tmpC = n * (n - 1) * maxSecondForwardDifference;
            if (tmpC > epsilon_intersection) {
                // compute refined curve from curve C using deCasteljau (degree elevation)
                std::vector<Points> matrix;
                matrix.reserve(curveC.getCount());
                matrix.insert(matrix.begin(), curveC);
                Points curveD = subdivision(matrix, 0.5);

                // compute first part of the refined curve and its intersections with curve B
                Points curveD1 = Points();
                for (int i = 0; i <= n; i++) {
                    curveD1.addPoint(curveD.getPointX(i), curveD.getPointY(i));
                }
                result.addAllPoints(findIntersections(curveB, curveD1));

                // compute second part of the refined curve and its intersections with curve B
                Points curveD2 = Points();
                for (int i = n; i <= n * 2; i++) {
                    curveD2.addPoint(curveD.getPointX(i), curveD.getPointY(i));
                }
                result.addAllPoints(findIntersections(curveB, curveD2));
            }
            else {
                // intersect line segments of curve B and curve C
                Points pppp = intersectLineSegments(curveB, curveC);
                result.addAllPoints(pppp);
            }
        }
    }
    return result;
}

// p.5 s.34
Points GLWidget::degreeElevation(Points& curve)
{
    Points refinedCurve = Points();
    int n = curve.getCount() - 1;
    double newX, newY;

    refinedCurve.addPoint(curve.getPointX(0), curve.getPointY(0));
    for (int i = 1; i <= n; i++) {
        newX = (i / double(n + 1)) * curve.getPointX(i - 1) + (1 - i / double(n + 1)) * curve.getPointX(i);
        newY = (i / double(n + 1)) * curve.getPointY(i - 1) + (1 - i / double(n + 1)) * curve.getPointY(i);
        refinedCurve.addPoint(newX, newY);
    }
    return refinedCurve;
}

// p.5 s.36-38
Points GLWidget::subdivision(std::vector<Points> points, double t)
{
    int len = points.size();

    if (len == 0) return Points();

    if (points.at(len-1).getCount() == 1) {
        // final case - return upper diagonal and lower row (backwards) of triagonal matrix
        Points result = Points();
        for (int j = 0; j < len; j++)
        {
            result.addPoint(points.at(j).getPointX(0), points.at(j).getPointY(0));
        }
        for (int j = len - 2; j >= 0; j--)
        {
            result.addPoint(points.at(j).getPointX(len-j-1), points.at(j).getPointY(len-j-1));
        }
        return result;
    }

    Points& previous_level = points.at(len-1);
    Points new_points = Points();
    for (int i = 0; i < previous_level.getCount() - 1; i++)
    {
        double x_pos = (1.0 - t) * double(previous_level.getPointX(i)) + t * double(previous_level.getPointX(i + 1));
        double y_pos = (1.0 - t) * double(previous_level.getPointY(i)) + t * double(previous_level.getPointY(i + 1));
        new_points.addPoint(x_pos, y_pos);
    }
    //points[len] = new_points;
    points.insert(points.end(), new_points);

    return subdivision(points, t);
}

Points GLWidget::intersectLineSegments(Points& curve1, Points& curve2)
{
    Points intersectingPoints = Points();
    //Points segment1, segment2;
    for (int i = 0; i < curve1.getCount() - 1; i++) {
        // work with segment (i), (i+1) of curve 1
        /*segment1 = Points();
        segment1.addPoint(curve1.getPointX(i), curve1.getPointY(i));
        segment1.addPoint(curve1.getPointX(i + 1), curve1.getPointY(i + 1));*/
        for (int j = 0; j < curve2.getCount() - 1; j++) {
            // work with segment (j), (j+1) of curve 2
            /*segment2 = Points();
            segment2.addPoint(curve2.getPointX(j), curve2.getPointY(j));
            segment2.addPoint(curve2.getPointX(j + 1), curve2.getPointY(j + 1));*/

            // intersection of two line segments iff points of one don't lie on the same side of the other
            //if (crossesLineSegment(segment1, segment2) && crossesLineSegment(segment2, segment1)) {
            if (crossesLineSegment(curve1, i, curve2, j) && crossesLineSegment(curve2, j, curve1, i)) {
                // there is intersection -> choose some intersecting point
                intersectingPoints.addPoint((curve1.getPointX(i) + curve1.getPointX(i+1)) / 2,
                    (curve1.getPointY(i) + curve1.getPointY(i+1)) / 2);
            }
        }
    }
    return intersectingPoints;
}

boolean GLWidget::crossesLineSegment(Points& base, int i, Points& tested, int j)
{
    double vectorBaseX = base.getPointX(i+1) - base.getPointX(i);
    double vectorBaseY = base.getPointY(i+1) - base.getPointY(i);

    double normalBaseX = -vectorBaseY;
    double normalBaseY = vectorBaseX;

    double testVector1X = tested.getPointX(j) - base.getPointX(i);
    double testVector1Y = tested.getPointY(j) - base.getPointY(i);

    double testVector2X = tested.getPointX(j+1) - base.getPointX(i);
    double testVector2Y = tested.getPointY(j+1) - base.getPointY(i);

    return dotProduct(normalBaseX, normalBaseY, testVector1X, testVector1Y)
        * dotProduct(normalBaseX, normalBaseY, testVector2X, testVector2Y) <= 0;
}

boolean GLWidget::crossesLineSegment(Points& base, Points& tested)
{
    if (base.getCount() != 2 || tested.getCount() != 2) return false;

    double vectorBaseX = base.getPointX(1) - base.getPointX(0);
    double vectorBaseY = base.getPointY(1) - base.getPointY(0);

    double testVector1X = tested.getPointX(0) - base.getPointX(0);
    double testVector1Y = tested.getPointY(0) - base.getPointY(0);

    double testVector2X = tested.getPointX(1) - base.getPointX(0);
    double testVector2Y = tested.getPointY(1) - base.getPointY(0);

    return dotProduct(vectorBaseX, vectorBaseY, testVector1X, testVector1Y)
        * dotProduct(vectorBaseX, vectorBaseY, testVector2X, testVector2Y) <= 0;
}

// *******************************************
// * Find self-intersections                 *
// *******************************************

// p.5 s.44-45
Points GLWidget::findSelfIntersections(Points& curve)
{
    if (curve.getCount() <= 2) return Points();

    // compute segments and their intersections
    Points foundIntersections;
    Points selfIntersections = Points();
    std::vector<Points> segments = findSelfIntersectionParts(curve);
    for (int i = 0; i < segments.size(); i++) {
        for (int j = 0; j < segments.size(); j++) {
            if (i == j) continue;
            foundIntersections = findIntersections(segments.at(i), segments.at(j));
            // remove endpoints of segments that might have been marked as self-intersections
            selfIntersections.addAllPoints(removeEndPoints(segments.at(i), segments.at(j), foundIntersections));
        }
    }
    return selfIntersections;
}

Points GLWidget::removeEndPoints(Points& segment1, Points& segment2, Points& intersections)
{
    Points selfIntersections = Points();
    double precision = 0.001;
    for (int i = 0; i < intersections.getCount(); i++) {
        if (pointsEqual(intersections.getPointX(i), intersections.getPointY(i), segment1.getPointX(0), segment1.getPointY(0), precision) ||
            pointsEqual(intersections.getPointX(i), intersections.getPointY(i), segment1.getPointX(segment1.getCount() - 1), segment1.getPointY(segment1.getCount() - 1), precision) ||
            pointsEqual(intersections.getPointX(i), intersections.getPointY(i), segment2.getPointX(0), segment2.getPointY(0), precision) ||
            pointsEqual(intersections.getPointX(i), intersections.getPointY(i), segment2.getPointX(segment2.getCount() - 1), segment2.getPointY(segment2.getCount() - 1), precision)
            ) continue;
        selfIntersections.addPoint(intersections.getPointX(i), intersections.getPointY(i));
    }
    return selfIntersections;
}

// p.5 s.44-45
std::vector<Points> GLWidget::findSelfIntersectionParts(Points& curve)
{
    // compute direction of tangent in each control point
    Points tangents = derivatives(curve);

    // compute angles between all pairs of adjacent tangents and sum them
    double sum = sumOfAngles(tangents);

    // if sum > 180 degrees (= PI = 2*acos(0)), subdivide and repeat; else return this curve
    std::vector<Points> parts;
    if (sum >= 2 * acos(0.0)) {
        // subdivide
        std::vector<Points> matrix;
        matrix.reserve(curve.getCount());
        matrix.insert(matrix.begin(), curve);
        Points curveA = subdivision(matrix, 0.5);
        int m = curve.getCount() - 1;

        // compute first part of the refined curve
        Points curveA1 = Points();
        for (int i = 0; i <= m; i++) {
            curveA1.addPoint(curveA.getPointX(i), curveA.getPointY(i));
        }
        std::vector<Points> parts1 = findSelfIntersectionParts(curveA1);
        
        // compute second part of the refined curve
        Points curveA2 = Points();
        for (int i = m; i <= m * 2; i++) {
            curveA2.addPoint(curveA.getPointX(i), curveA.getPointY(i));
        }
        std::vector<Points> parts2 = findSelfIntersectionParts(curveA2);

        parts.insert(parts.end(), parts1.begin(), parts1.end());
        parts.insert(parts.end(), parts2.begin(), parts2.end());
    }
    else {
        parts.push_back(curve);
    }

    return parts;
}

double GLWidget::sumOfAngles(Points& tangents)
{
    double sum = 0;
    for (int i = 0; i < tangents.getCount() - 1; i++) {
        sum += acos(dotProduct(tangents.getPointX(i), tangents.getPointY(i), tangents.getPointX(i + 1), tangents.getPointY(i + 1))
            / (size(tangents.getPointX(i), tangents.getPointY(i)) * size(tangents.getPointX(i + 1), tangents.getPointY(i + 1))));
    }
    return sum;
}

Points GLWidget::derivatives(Points& points)
{
    if (points.getCount() <= 1) return Points();

    Points result = Points();
    int n = points.getCount() - 1;
    for (double t = 0; t <= 1; t += epsilon_draw)
    {
        Points derivativeCurve = derivativePoints(points, t);
        result.addPoint(n * derivativeCurve.getPointX(0), n * derivativeCurve.getPointY(0));
    }
    return result;
}

Points GLWidget::derivativePoints(Points& points, double t)
{
    if (points.getCount() <= 1) return points;

    if (points.getCount() == 2) {
        Points derivative = Points();
        derivative.addPoint(points.getPointX(1) - points.getPointX(0), points.getPointY(1) - points.getPointY(0));
        return derivative;
    }

    double x_pos, y_pos;
    Points new_points = Points();
    for (int j = 0; j < points.getCount() - 1; j++)
    {
        x_pos = (1.0 - t) * double(points.getPointX(j)) + t * double(points.getPointX(j + 1));
        y_pos = (1.0 - t) * double(points.getPointY(j)) + t * double(points.getPointY(j + 1));
        new_points.addPoint(x_pos, y_pos);
    }
    return derivativePoints(new_points, t);
}

// *******************************************
// * Drawing functions                       *
// *******************************************

void GLWidget::drawPoints(Points& pointsToDraw)
{
    glBegin(GL_POINTS);
    for (int i = 0; i < pointsToDraw.getCount(); i++) {
        glVertex2f(pointsToDraw.getPointX(i), pointsToDraw.getPointY(i));
    }
    glEnd();
}

void GLWidget::drawCurve(Points points)
{
    glBegin(GL_LINE_STRIP);
    for (int i = 0; i < points.getCount(); i++)
    {
        glVertex2f(points.getPointX(i), points.getPointY(i));
    }
    glEnd();
}

// *******************************************
// * Helper functions                        *
// *******************************************

Points GLWidget::findMin(Points& points)
{
    if (points.getCount() <= 1) return points;

    Points minCoords = Points();
    minCoords.addPoint(points.getPointX(0), points.getPointY(0));
    for (int idx = 1; idx < points.getCount(); idx++) {
        if (points.getPointX(idx) < minCoords.getPointX(0)) minCoords.setPointX(0, points.getPointX(idx));
        if (points.getPointY(idx) < minCoords.getPointY(0)) minCoords.setPointY(0, points.getPointY(idx));
    }
    return minCoords;
}

Points GLWidget::findMax(Points& points)
{
    if (points.getCount() <= 1) return points;

    Points maxCoords = Points();
    maxCoords.addPoint(points.getPointX(0), points.getPointY(0));
    for (int idx = 1; idx < points.getCount(); idx++) {
        if (points.getPointX(idx) > maxCoords.getPointX(0)) maxCoords.setPointX(0, points.getPointX(idx));
        if (points.getPointY(idx) > maxCoords.getPointY(0)) maxCoords.setPointY(0, points.getPointY(idx));
    }
    return maxCoords;
}

boolean GLWidget::intervalsIntersect(double min1, double max1, double min2, double max2)
{
    // overlap: min min max max
    return (min1 <= min2 && min2 <= max1) || (min2 <= min1 && min1 <= max2);
}

double GLWidget::dotProduct(double vector1X, double vector1Y, double vector2X, double vector2Y)
{
    return vector1X * vector2X + vector1Y * vector2Y;
}

double GLWidget::size(double vectorX, double vectorY)
{
    return sqrt(vectorX * vectorX + vectorY * vectorY);
}

double GLWidget::pointsEqual(double vector1X, double vector1Y, double vector2X, double vector2Y, double precision)
{
    return abs(vector1X - vector2X) < precision && abs(vector1Y - vector2Y) < precision;
}

// *******************************************
// * GL + drawing                            *
// *******************************************

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
        points.setPointX(clickedPoint,posF.x());
        points.setPointY(clickedPoint,posF.y());
        update();
    }
}

void GLWidget::mousePressEvent(QMouseEvent *event)
{
    if (event->buttons() & Qt::LeftButton) {
        QPoint pos = event->pos();
        QPointF posF = transformPosition(pos);
        clickedPoint = points.getClosestPoint(posF.x(),posF.y());
        points.setPointX(clickedPoint,posF.x());
        points.setPointY(clickedPoint,posF.y());
        update();
    }
}

void GLWidget::mouseDoubleClickEvent(QMouseEvent *)
{
}

void GLWidget::setIntersection(int state)
{
    doIntersection = (state == Qt::Checked);
    update();
}

void GLWidget::setSelfIntersection(int state)
{
    doSelfIntersection = (state == Qt::Checked);
    update();
}

void GLWidget::setEpsilonDraw(double value)
{
    epsilon_draw = value;
}

void GLWidget::setEpsilonIntersection(double value)
{
    epsilon_intersection = value;
}
