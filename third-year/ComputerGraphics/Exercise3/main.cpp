#include <cmath>
#include "vec.h"
#include "mat.h"
#include "Uebung2/Uebung2/glut.h"
#include "glut.h"
#include <iostream>
using namespace std;

const int g_iWidth = 400;
const int g_iHeight = 400;
int g_WinWidth = g_iWidth;
int g_WinHeight = g_iHeight;

int g_iTimerMSecs = 10;

float cubeSize;
float fFocus;

float angleX, angleY, angleZ;
float angleA, angleB, angleC;

CVec4f viewOriginVector;
CVec4f viewUpVector;
CVec4f viewDirVector;

float displacement;
float incrAngleWorld;
float incrAngleView;
float incrFocus;
float incrSize;
float incrTranslation;



void display();
void keyboard(unsigned char key, int x, int y);
void reshape(int w, int h);


void reset() {

	::cubeSize = 70.0f;
	::fFocus = -250.0f;

	::displacement = 100.0f;
	::incrAngleWorld = 0.1f;
	::incrAngleView = 0.01f;
	::incrFocus = 1.0f;
	::incrSize = 2;
	::incrTranslation = 1.0f;


	::angleX = 0.0f;
	::angleY = 0.0f;
	::angleZ = 0.0f;

	::angleA = 0.0f;
	::angleB = 0.0f;
	::angleC = 0.0f;


	float viewOrigin[4] = { 0.0f, 0.0f, 70.0f , 1.0f };
	viewOriginVector = CVec4f(viewOrigin);

	float viewUp[4] = { 0.0f , -1.0f , 0.0f , 1.0f };
	viewUpVector = CVec4f(viewUp);

	float viewDir[4] = { 0.0f , 0.0f, 1.0f , 1.0f };
	viewDirVector = CVec4f(viewDir);
}

/*

//RESET OF DEMO
void reset() {

	::cubeSize = 70.0f;
	::fFocus = -250.0f;

	::displacement = 100.0f;
	::incrAngleWorld = 0.1f;
	::incrAngleView = 0.01f;
	::incrFocus = 1.0f;
	::incrSize = 1.5f;
	::incrTranslation = 1.0f;


	::angleX = 0.0f;
	::angleY = 0.0f;
	::angleZ = 0.0f;

	::angleA = 0.0f;
	::angleB = 0.0f;
	::angleC = 0.0f;


	float viewOrigin[4] = { 0.0f, 0.0f, 70.0f , 1.0f };
	viewOriginVector = CVec4f(viewOrigin);

	float viewUp[4] = { 0.0f , 1.0f , 0.0f , 1.0f };
	viewUpVector = CVec4f(viewUp);

	float viewDir[4] = { 0.0f , 0.0f, -1.0f , 1.0f };
	viewDirVector = CVec4f(viewDir);
}
*/
void reshape(int w, int h) {

	g_WinWidth = w;
	g_WinHeight = h;

	glViewport(0, 0, w, h);					// Establish viewing area to cover entire window.

	glMatrixMode(GL_PROJECTION);			// Start modifying the projection matrix.
	glLoadIdentity();						// Reset project matrix.
	glOrtho(-w / 2, w / 2, -h / 2, h / 2, 0, 1);	// Map abstract coords directly to window coords.

	glutPostRedisplay();
}


void initGL()
{
	glViewport(0, 0, g_iWidth, g_iHeight);	// Establish viewing area to cover entire window.

	glMatrixMode(GL_PROJECTION);			// Start modifying the projection matrix.
	glLoadIdentity();						// Reset project matrix.
	glOrtho(-g_iWidth / 2, g_iWidth / 2, -g_iHeight / 2, g_iHeight / 2, 0, 1);	// Map abstract coords directly to window coords.

	// tell GL that we draw to the back buffer and
	// swap buffers when image is ready to avoid flickering
	glDrawBuffer(GL_BACK);

	glutDisplayFunc(display);
	glutKeyboardFunc(keyboard);
	glutReshapeFunc(reshape);

	// tell which color to use to clear image
	glClearColor(0, 0, 0, 1);
}


void timer(int value)
{
	glutPostRedisplay();
	glutTimerFunc(g_iTimerMSecs, timer, 0);
}

class Point {
public:
	Point(int x = 0, int y = 0, int z = 0) {
		this->x = x;
		this->y = y;
		this->z = z;
	}
	int x, y, z;
};

class Color {
public:
	Color(float r = 1.0f, float g = 1.0f, float b = 1.0f) {
		this->r = r;
		this->g = g;
		this->b = b;
	}
	float r, g, b;
};



void bhamLine(Point p1, Point p2, Color c) {
	//we want to translate the first point to the origin, and the move the second
	float x1 = 0;
	float y1 = 0;

	float x2 = p2.x - p1.x;
	float y2 = p2.y - p1.y;


	//switched is used when y > x, so the line will be on one from  2nd, 3rd, 6th or 7th octant
	bool switched = false;
	int cuadrant = 1;

	//for deciding in which octant the line is
	if (y2 <= 0) {
		y2 = -y2;
		if (x2 <= 0) {
			x2 = -x2;
			cuadrant = 3;
			if (x2 < y2) {
				float tempX = x2;
				x2 = y2;
				y2 = tempX;
				switched = true;
			}
		}
		else {
			cuadrant = 4;
			if (x2 < y2) {
				float tempX = x2;
				x2 = y2;
				y2 = tempX;
				switched = true;
			}
		}
	}
	else {
		if (x2 <= 0) {
			x2 = -x2;
			cuadrant = 2;
			if (x2 < y2) {
				float tempX = x2;
				x2 = y2;
				y2 = tempX;
				switched = true;
			}
		}
		else {
			if (x2 < y2) {
				float tempX = x2;
				x2 = y2;
				y2 = tempX;
				switched = true;
			}
		}
	}

	//bresenham alg from the slides
	int deltaX = x2;
	int deltaY = y2;
	int deltaNE = 2 * (deltaY - deltaX);
	int deltaE = 2 * (deltaY);
	int d = (2 * deltaY) - deltaX;

	glBegin(GL_POINTS);
	glColor3f(c.r, c.g, c.b);

	while (x1 < x2) {
		if (d >= 0) {
			d = d + deltaNE;
			x1++;
			y1++;
		}
		else {
			d = d + deltaE;
			x1++;
		}



		//first we calculate the translation, not to start in the origin
		float temporalX1, temporalY1;

		//with these, we do in the inverse order the transformations we did before (if the line is in the 2ndCuadrant for instance, we had to put it to the first cuadrant,
		//and now we are creating temporal variables that make back the changes)

		if (switched == false) {
			switch (cuadrant) {
			case 2:
				temporalX1 = x1 - p1.x;
				temporalY1 = y1 + p1.y;
				glVertex2i(-temporalX1, temporalY1);

				break;
			case 3:
				temporalX1 = x1 - p1.x;
				temporalY1 = y1 - p1.y;
				glVertex2i(-temporalX1, -temporalY1);

				break;
			case 4:
				temporalX1 = x1 + p1.x;
				temporalY1 = y1 - p1.y;
				glVertex2i(temporalX1, -temporalY1);

				break;
			default:
				temporalX1 = x1 + p1.x;
				temporalY1 = y1 + p1.y;
				glVertex2i(temporalX1, temporalY1);

				break;
			}
		}
		else {
			switch (cuadrant) {
			case 2:
				temporalX1 = x1 + p1.y;
				temporalY1 = y1 - p1.x;
				glVertex2i(-temporalY1, temporalX1);

				break;
			case 3:
				temporalX1 = x1 - p1.y;
				temporalY1 = y1 - p1.x;
				glVertex2i(-temporalY1, -temporalX1);

				break;
			case 4:
				temporalX1 = x1 - p1.y;
				temporalY1 = y1 + p1.x;
				glVertex2i(temporalY1, -temporalX1);

				break;
			default:
				temporalX1 = x1 + p1.y;
				temporalY1 = y1 + p1.x;
				glVertex2i(temporalY1, temporalX1);

				break;

			}

		}

	}
	//to make the points point out =)
	glVertex2i(p1.x, p1.y);


	glVertex2i(p2.x, p2.y);


	glEnd();
}


CVec4f toNorm(CVec4f vec) {
	float vecLength = sqrt(pow(vec(0), 2) + pow(vec(1), 2) + pow(vec(2), 2) + pow(vec(3), 2));
	vec(0) = vec(0) / vecLength;
	vec(1) = vec(1) / vecLength;
	vec(2) = vec(2) / vecLength;
	vec(3) = 1.0f;
	return vec;
}

CVec3f toNorm(CVec3f vec) {
	float vecLength = sqrt(pow(vec(0), 2) + pow(vec(1), 2) + pow(vec(2), 2));
	vec(0) = vec(0) / vecLength;
	vec(1) = vec(1) / vecLength;
	vec(2) = vec(2) / vecLength;
	return vec;
}


CVec4f crossProduct(CVec4f first, CVec4f second) {
	float firstV[4];
	first.getData(firstV);
	float secondV[4];
	second.getData(secondV);

	float resX = firstV[1] * secondV[2] - firstV[2] * secondV[1];
	float resY = firstV[2] * secondV[0] - firstV[0] * secondV[2];
	float resZ = firstV[0] * secondV[1] - firstV[1] * secondV[0];

	float res[4] = { resX, resY, resZ, 1.0f };
	CVec4f resV(res);
	return resV;
}

CVec3f crossProduct(CVec3f first, CVec3f second) {
	float firstV[3];
	first.getData(firstV);
	float secondV[3];
	second.getData(secondV);

	float resX = firstV[1] * secondV[2] - firstV[2] * secondV[1];
	float resY = firstV[2] * secondV[0] - firstV[0] * secondV[2];
	float resZ = firstV[0] * secondV[1] - firstV[1] * secondV[0];

	float res[3] = { resX, resY, resZ };
	CVec3f resV(res);
	return resV;
}

Point parsePoint(CVec3f vec) {

	float res[3];
	vec.getData(res);

	return Point(res[0], res[1], res[2]);
}
//////////////////////////////////////////////////////////////////////////////////
//////////////////////////////////////////////////////////////////////////////////


CVec3f projectZ(float fFocus, CVec3f pSicht) {

	float ps[3];
	pSicht.getData(ps);

	float x = ps[0] * (fFocus / (fFocus - ps[2]));
	float y = ps[1] * (fFocus / (fFocus - ps[2]));

	float res[3] = { x, y, 1.0f };
	CVec3f vectorRes(res);
	return vectorRes;
}


/*

b - - - c
| \     | \
|   a - - - d
|   |   |   |
g - | - h   |
  \ |     \ |
	f - - - e


*/


void drawProjectedZ(CVec3f Points[8]) {

	Point a = parsePoint(Points[0]);
	Point b = parsePoint(Points[1]);
	Point c = parsePoint(Points[2]);
	Point d = parsePoint(Points[3]);
	Point e = parsePoint(Points[4]);
	Point f = parsePoint(Points[5]);
	Point g = parsePoint(Points[6]);
	Point h = parsePoint(Points[7]);

	Color color(1, 1, 1);
	Color color2(0, 1, 1);

	bhamLine(a, b, color);
	bhamLine(b, c, color);
	bhamLine(c, d, color);
	//bhamLine(d, a, color2);
	bhamLine(d, a, color);

	//bhamLine(a, f, color2);
	bhamLine(a, f, color);
	bhamLine(b, g, color);
	bhamLine(c, h, color);
	//bhamLine(d, e, color2);
	bhamLine(d, e, color);

	bhamLine(f, g, color);
	bhamLine(g, h, color);
	bhamLine(h, e, color);
	//bhamLine(e, f, color2);
	bhamLine(e, f, color);
}

CMat4f getTransform(CVec4f viewOrigin, CVec4f viewDir, CVec4f viewUp) {


	float viewUp3coordsTemp[3] = { viewUp(0), viewUp(1), viewUp(2) };
	float viewDir3coordsTemp[3] = { viewDir(0), viewDir(1), viewDir(2) };
	float viewOrigin3coordsTemp[3] = { viewOrigin(0), viewOrigin(1), viewOrigin(2) };


	CVec3f viewUp3coords(viewUp3coordsTemp);
	viewUp3coords = toNorm(viewUp3coords);
	CVec3f viewDir3coords(viewDir3coordsTemp);
	viewDir3coords = toNorm(viewDir3coords);
	CVec3f viewOrigin3coords(viewOrigin3coordsTemp);



	CVec3f row1 = crossProduct(viewUp3coords, viewDir3coords);	
	CVec3f row3 = viewDir3coords;
	CVec3f row2 = crossProduct(row3, row1);
	

	float matTransTemp[3][3] = {
		{row1(0), row1(1), row1(2)},
		{row2(0), row2(1), row2(2)},
		{row3(0), row3(1), row3(2)}
	};

	CMat3f matTrans(matTransTemp);

	float transformation[4][4] = {
	{matTransTemp[0][0], matTransTemp[0][1], matTransTemp[0][2], -viewOrigin3coords(0)},
	{matTransTemp[1][0] ,matTransTemp[1][1], matTransTemp[1][2],-viewOrigin3coords(1)},
	{matTransTemp[2][0], matTransTemp[2][1],matTransTemp[2][2], viewOrigin3coords(2)},
	{0.0f, 0.0f, 0.0f, 1.0f} };

	CMat4f matTransformation(transformation);
	return matTransformation;

}


CMat4f getTransformDemo(CVec4f viewOrigin, CVec4f viewDir, CVec4f viewUp) {


	float viewUp3coordsTemp[3] = { viewUp(0), viewUp(1), viewUp(2) };
	float viewDir3coordsTemp[3] = { viewDir(0), viewDir(1), viewDir(2) };
	float viewOrigin3coordsTemp[3] = { viewOrigin(0), viewOrigin(1), viewOrigin(2) };


	CVec3f viewUp3coords(viewUp3coordsTemp);
	CVec3f viewDir3coords(viewDir3coordsTemp);
	CVec3f viewOrigin3coords(viewOrigin3coordsTemp);


	CVec3f zaxis = toNorm(viewUp3coords - viewDir3coords);

	CVec3f xaxis = toNorm(crossProduct(viewUp3coords, zaxis));

	CVec3f yaxis = crossProduct(zaxis, xaxis);



	float matTransTemp[3][3] = {
		{xaxis(0), yaxis(0), zaxis(0)},
		{xaxis(1), yaxis(1), zaxis(1)},
		{xaxis(2), yaxis(2), zaxis(2)}
	};

	CMat3f matTrans(matTransTemp);


	CVec3f vecTemp = viewOrigin3coords;

	float orientation[4][4] = {
	{matTransTemp[0][0], matTransTemp[0][1], matTransTemp[0][2], -viewOrigin3coords(0)},
	{matTransTemp[1][0] ,matTransTemp[1][1], matTransTemp[1][2], -viewOrigin3coords(1)},
	{matTransTemp[2][0], matTransTemp[2][1],matTransTemp[2][2], viewOrigin3coords(2)},
	{0.0f, 0.0f, 0.0f, 1.0f} };

	CMat4f matOrientation(orientation);


	return matOrientation;

}

CMat4f getTransformInverse(CVec4f viewOrigin, CVec4f viewDir, CVec4f viewUp) {


	float viewUp3coordsTemp[3] = { viewUp(0), viewUp(1), viewUp(2) };
	float viewDir3coordsTemp[3] = { viewDir(0), viewDir(1), viewDir(2) };
	float viewOrigin3coordsTemp[3] = { viewOrigin(0), viewOrigin(1), viewOrigin(2) };


	CVec3f viewUp3coords(viewUp3coordsTemp);
	viewUp3coords = toNorm(viewUp3coords);
	CVec3f viewDir3coords(viewDir3coordsTemp);
	viewDir3coords = toNorm(viewDir3coords);
	CVec3f viewOrigin3coords(viewOrigin3coordsTemp);


	CVec3f row1 = crossProduct(viewUp3coords, viewDir3coords);
	CVec3f row3 = viewDir3coords;
	CVec3f row2 = crossProduct(row3, row1);


	float matTransTemp[3][3] = {
		{row1(0), row2(0), row3(0)},
		{row1(1), row2(1), row3(1)},
		{row1(2), row2(2), row3(2)}
	};


	CMat3f matTrans(matTransTemp);


	CVec3f vecTemp = matTrans * viewOrigin3coords;

	float transformation[4][4] = {
	{matTransTemp[0][0], matTransTemp[0][1], matTransTemp[0][2], vecTemp(0)},
	{matTransTemp[1][0] ,matTransTemp[1][1], matTransTemp[1][2], vecTemp(1)},
	{matTransTemp[2][0], matTransTemp[2][1],matTransTemp[2][2], vecTemp(2)},
	{0.0f, 0.0f, 0.0f, 1.0f} };

	CMat4f matTransformation(transformation);
	return matTransformation;

}


CMat4f worldRotation(float angX, float angY, float angZ) {

	float rotationXTemp[4][4] = {
	{1, 0, 0, 0},
	{0 , cos(angX), -sin(angX), 0},
	{0, sin(angX), cos(angX), 0},
	{0.0f, 0.0f, 0.0f, 1.0f} };
	CMat4f rotationX(rotationXTemp);

	float rotationYTemp[4][4] = {
	{cos(angY), 0, sin(angY), 0},
	{0 , 1, 0, 0},
	{-sin(angY), 0,cos(angY), 0},
	{0.0f, 0.0f, 0.0f, 1.0f} };
	CMat4f rotationY(rotationYTemp);

	float rotationZTemp[4][4] = {
	{cos(angZ), -sin(angZ), 0, 0},
	{sin(angZ) , cos(angZ), 0, 0},
	{0, 0, 1, 0},
	{0.0f, 0.0f, 0.0f, 1.0f} };
	CMat4f rotationZ(rotationZTemp);


	return 	rotationZ * rotationY * rotationX;
}


//b is the "arbitrary axis" described in the slide: the refenrence axis in which the rotation is going to happen
CVec4f viewRotation(CVec4f axis, float angle, CVec4f point) {

	float c = cos(angle);
	float s = sin(angle);	
	float cNeg = 1 - c;
	float rotation[4][4] = { { axis(0) * axis(0) * cNeg + c, axis(0) * axis(1) * cNeg - axis(2) * s, axis(0) * axis(2) * cNeg + axis(1) * s, 0 },
								{ axis(0) * axis(1) * cNeg + axis(2) * s, axis(1) * axis(1) * cNeg + c, axis(1) * axis(2) * cNeg - axis(0) * s, 0 },
								{ axis(0) * axis(2) * cNeg - axis(1) * s, axis(1) * axis(2) * cNeg + axis(0) * s, axis(2) * axis(2) * cNeg + c, 0 },
								{                           0,                           0,                           0, 1 } };
	CMat4f rotationVec(rotation);
	
	return rotationVec * point;
}

CVec4f projectZallg(CMat4f matTransf, float  fFocus, CVec4f pWorld) {
		
	CVec4f res = matTransf * pWorld;

	CVec4f viewRight = crossProduct(toNorm(viewUpVector), toNorm(viewDirVector));

//meh, the key C is bugged, i suppose its has to be with the order  of the rotations (enchained)
	CVec4f viewDirPoint = viewRotation(viewDirVector, angleA, res);
	CVec4f viewUpPoint = viewRotation(viewUpVector, angleB, viewDirPoint);	
	CVec4f finalPoint = viewRotation(viewRight, angleC, viewUpPoint);	
	
	float temp[3] = { finalPoint(0), finalPoint(1), finalPoint(2) };
	CVec3f tempVec(temp);

	CVec3f projectedZ = projectZ(fFocus, tempVec);

	float projectedZVec[4] = { projectedZ(0), projectedZ(1), projectedZ(2), 1.0 };
	return CVec4f(projectedZVec);
}



void drawQuader(CVec3f cuboid[8], float fFocus, CVec4f viewOrigin, CVec4f viewDir, CVec4f viewUp, float angX, float angY, float angZ, float angA, float angB, float angC) {

	CMat4f totalTransform;

	//CMat4f transform = getTransformInverse(viewOrigin, viewDir, viewUp);
	CMat4f transform = getTransform(viewOrigin, viewDir, viewUp);	
	//CMat4f transform = getTransformDemo(viewOrigin, viewDir, viewUp);

	CMat4f rotation = worldRotation(angX, angY, angZ);

	totalTransform = transform * rotation;
	

	CVec3f result[8] = {};
	for (int i = 0; i <= 7; i++) {

		CVec3f tmpCuboid = cuboid[i];
		float tmpCuboidWith4coords[4] = { tmpCuboid(0), tmpCuboid(1), tmpCuboid(2), 1.0f };
		CVec4f tmpCuboidWith4coordsVec(tmpCuboidWith4coords);

		CVec4f res = projectZallg(totalTransform, fFocus, tmpCuboidWith4coordsVec);

		float resWith3coords[3] = { res(0), res(1), res(2) };
		CVec3f resWith3coordsVec(resWith3coords);

		result[i] = resWith3coordsVec;
	}
	drawProjectedZ(result);
}




void display(void)
{
	glClear(GL_COLOR_BUFFER_BIT);

	float a[3] = { -cubeSize + displacement, cubeSize + displacement, 0 };
	float b[3] = { -cubeSize + displacement, cubeSize + displacement, -cubeSize };
	float c[3] = { cubeSize + displacement, cubeSize + displacement, -cubeSize };
	float d[3] = { cubeSize + displacement, cubeSize + displacement, 0 };
	float e[3] = { cubeSize + displacement, -cubeSize + displacement, 0 };
	float f[3] = { -cubeSize + displacement, -cubeSize + displacement, 0 };
	float g[3] = { -cubeSize + displacement, -cubeSize + displacement, -cubeSize };
	float h[3] = { cubeSize + displacement, -cubeSize + displacement, -cubeSize };

	CVec3f vectorA(a);
	CVec3f vectorB(b);
	CVec3f vectorC(c);
	CVec3f vectorD(d);
	CVec3f vectorE(e);
	CVec3f vectorF(f);
	CVec3f vectorG(g);
	CVec3f vectorH(h);

	CVec3f cuboid[8] = { vectorA, vectorB, vectorC, vectorD , vectorE, vectorF, vectorG, vectorH };

	drawQuader(cuboid, fFocus, viewOriginVector, viewUpVector, viewDirVector, angleX, angleY, angleZ, angleA, angleB, angleC);

	float a2[3] = { -cubeSize - displacement, cubeSize - displacement, 0 };
	float b2[3] = { -cubeSize - displacement, cubeSize - displacement, -cubeSize };
	float c2[3] = { cubeSize - displacement, cubeSize - displacement, -cubeSize };
	float d2[3] = { cubeSize - displacement, cubeSize - displacement, 0 };
	float e2[3] = { cubeSize - displacement, -cubeSize - displacement, 0 };
	float f2[3] = { -cubeSize - displacement, -cubeSize - displacement, 0 };
	float g2[3] = { -cubeSize - displacement, -cubeSize - displacement, -cubeSize };
	float h2[3] = { cubeSize - displacement, -cubeSize - displacement, -cubeSize };
	

	CVec3f vectorA2(a2);
	CVec3f vectorB2(b2);
	CVec3f vectorC2(c2);
	CVec3f vectorD2(d2);
	CVec3f vectorE2(e2);
	CVec3f vectorF2(f2);
	CVec3f vectorG2(g2);
	CVec3f vectorH2(h2);

	CVec3f cuboid2[8] = { vectorA2, vectorB2, vectorC2, vectorD2 , vectorE2, vectorF2, vectorG2, vectorH2 };

	drawQuader(cuboid2, fFocus, viewOriginVector, viewUpVector, viewDirVector, angleX, angleY, angleZ, angleA, angleB, angleC);


	float a3[3] = { -cubeSize - displacement, cubeSize + displacement, 0 - displacement };
	float b3[3] = { -cubeSize - displacement, cubeSize + displacement, -cubeSize - displacement };
	float c3[3] = { cubeSize - displacement, cubeSize + displacement, -cubeSize - displacement };
	float d3[3] = { cubeSize - displacement, cubeSize + displacement, 0 - displacement };
	float e3[3] = { cubeSize - displacement, -cubeSize + displacement, 0 - displacement };
	float f3[3] = { -cubeSize - displacement, -cubeSize + displacement, 0 - displacement };
	float g3[3] = { -cubeSize - displacement, -cubeSize + displacement, -cubeSize - displacement };
	float h3[3] = { cubeSize - displacement, -cubeSize + displacement, -cubeSize - displacement };


	CVec3f vectorA3(a3);
	CVec3f vectorB3(b3);
	CVec3f vectorC3(c3);
	CVec3f vectorD3(d3);
	CVec3f vectorE3(e3);
	CVec3f vectorF3(f3);
	CVec3f vectorG3(g3);
	CVec3f vectorH3(h3);

	CVec3f cuboid3[8] = { vectorA3, vectorB3, vectorC3, vectorD3 , vectorE3, vectorF3, vectorG3, vectorH3 };

	drawQuader(cuboid3, fFocus, viewOriginVector, viewUpVector, viewDirVector, angleX, angleY, angleZ, angleA, angleB, angleC);

	float a4[3] = { -cubeSize + displacement, cubeSize - displacement, 0 + displacement };
	float b4[3] = { -cubeSize + displacement, cubeSize - displacement, -cubeSize + displacement };
	float c4[3] = { cubeSize + displacement, cubeSize - displacement, -cubeSize + displacement };
	float d4[3] = { cubeSize + displacement, cubeSize - displacement, 0 + displacement };
	float e4[3] = { cubeSize + displacement, -cubeSize - displacement, 0 + displacement };
	float f4[3] = { -cubeSize + displacement, -cubeSize - displacement, 0 + displacement };
	float g4[3] = { -cubeSize + displacement, -cubeSize - displacement, -cubeSize + displacement };
	float h4[3] = { cubeSize + displacement, -cubeSize - displacement, -cubeSize + displacement };

	CVec3f vectorA4(a4);
	CVec3f vectorB4(b4);
	CVec3f vectorC4(c4);
	CVec3f vectorD4(d4);
	CVec3f vectorE4(e4);
	CVec3f vectorF4(f4);
	CVec3f vectorG4(g4);
	CVec3f vectorH4(h4);

	CVec3f cuboid4[8] = { vectorA4, vectorB4, vectorC4, vectorD4, vectorE4, vectorF4, vectorG4, vectorH4 };

	drawQuader(cuboid4, fFocus, viewOriginVector, viewUpVector, viewDirVector, angleX, angleY, angleZ, angleA, angleB, angleC);

	/*
	float a5[3] = { 0, 100, 0 };
	float b5[3] = { 0,100,-100 };
	float c5[3] = { 100,100,-100 };
	float d5[3] = { 100,100,0 };
	float e5[3] = { 100,0,0};
	float f5[3] = { 0,0,0 };
	float g5[3] = { 0,0,-100 };
	float h5[3] = { 100,0,-100 };

	CVec3f vectorA5(a5);
	CVec3f vectorB5(b5);
	CVec3f vectorC5(c5);
	CVec3f vectorD5(d5);
	CVec3f vectorE5(e5);
	CVec3f vectorF5(f5);
	CVec3f vectorG5(g5);
	CVec3f vectorH5(h5);

	CVec3f cuboid5[8] = { vectorA5, vectorB5, vectorC5, vectorD5, vectorE5, vectorF5, vectorG5, vectorH5 };

	drawQuader(cuboid5, fFocus, viewOriginVector, viewUpVector, viewDirVector, angleX, angleY, angleZ, angleA, angleB, angleC);
	*/
	glFlush();
	glutSwapBuffers();
}


void keyboard(unsigned char key, int x, int y) {

	switch (key) {
	case 'q':
	case 'Q': {
		exit(0);
		break;
	}
	case '+': {
		cubeSize = cubeSize + incrSize;
		glutDisplayFunc(display);
		break;
	}
	case '-': {
		cubeSize = cubeSize - incrSize;
		glutDisplayFunc(display);
		break; }
	case 'f': {
		fFocus = fFocus - incrFocus;
		break; }
	case 'F': {
		fFocus = fFocus + incrFocus;
		break;
	}
	case 'x': {
		angleX = angleX - incrAngleWorld;
		break;
	}
	case 'X': {
		angleX = angleX + incrAngleWorld;
		break;
	}
	case 'y': {
		angleY = angleY - incrAngleWorld;
		break;
	}
	case 'Y': {
		angleY = angleY + incrAngleWorld;
		break;
	}
	case 'z': {
		angleZ = angleZ - incrAngleWorld;
		break;
	}
	case 'Z': {
		angleZ = angleZ + incrAngleWorld;
		break;
	}
	case 'a': {
		angleA = angleA - incrAngleView;
		break;
	}
	case 'A': {
		angleA = angleA + incrAngleView;
		break;
	}
	case 'b': {
		angleB = angleB - incrAngleView;
		break;
	}
	case 'B': {
		angleB = angleB + incrAngleView;
		break;
	}
	case 'c': {
		angleC = angleC - incrAngleView;
		break;
	}
	case 'C': {
		angleC = angleC + incrAngleView;
		break;
	}
	case 'u': {
		viewOriginVector(0) = viewOriginVector(0) - incrTranslation;
		break;
	}
	case 'U': {
		viewOriginVector(0) = viewOriginVector(0) + incrTranslation;
		break;
	}
	case 'v': {
		viewOriginVector(1) = viewOriginVector(1) - incrTranslation;
		break;
	}
	case 'V': {
		viewOriginVector(1) = viewOriginVector(1) + incrTranslation;
		break;
	}
	case 'w': {
		viewOriginVector(2) = viewOriginVector(2) - incrTranslation;
		break;
	}
	case 'W': {
		viewOriginVector(2) = viewOriginVector(2) + incrTranslation;
		break;
	}
	case 'r':
	case 'R':
		reset();
		break;
	default:
		break;
	};
}

int main(int argc, char** argv)
{
	glutInit(&argc, argv);
	glutInitDisplayMode(GLUT_DOUBLE | GLUT_RGB);
	glutCreateWindow("Übung 3");

	glutReshapeWindow(800, 800);
	glutReshapeFunc(reshape);

	reset();
	initGL();

	glutTimerFunc(10, timer, 0);
	glutKeyboardFunc(keyboard);
	glutDisplayFunc(display);

	glutMainLoop();

	return 0;
}