// Sample code for Übung 2
#include <cmath>
#include "vec.h"
#include "mat.h"

// might be you have to swith to
// #include "glut.h" depending on your GLUT installation
#include "Uebung2/Uebung2/glut.h"
#include "glut.h"

// window width and height (choose an appropriate size)
const int g_iWidth = 400;
const int g_iHeight = 400;
int g_WinWidth = 400;
int g_WinHeight = 400;

// global variable to tune the timer interval
int g_iTimerMSecs;


float g_iAngleEarth;
float g_iAngleEarthIncr;

float g_iAngleMoon;
float g_iAngleMoonIncr;


CVec2i g_vecPos;		// same as above but in vector form ...
CVec2i g_vecPosIncr;	// (used in display2)


#define TEX_RES_X 60
#define TEX_RES_Y 60
// Anzahl der Pixel der Textur
#define TEX_RES TEX_RES_X*TEX_RES_Y
// Achsenlänge der Textur (Achsen sind asymmetrisch von -TexRes#/2 bis TesRes#/2-1)
#define TEX_HALF_X TEX_RES_X/2
#define TEX_HALF_Y TEX_RES_Y/2
// Konvertiert Indices von (x,y) Koordinaten in ein lineares Array
#define TO_LINEAR(x, y) (((x)) + TEX_RES_X*((y)))

// globaler Speicher für Texturdaten
char g_Buffer[3 * TEX_RES];
// Textur ID Name
GLuint g_TexID = 0;


//http://www-home.htwg-konstanz.de/~drachen/sypr/sypr.html
void manageTexture() {

	glEnable(GL_TEXTURE_2D);

	if (g_TexID == 0)	glGenTextures(1, &g_TexID);

	glBindTexture(GL_TEXTURE_2D, g_TexID);

	glTexEnvf(GL_TEXTURE_ENV, GL_TEXTURE_ENV_MODE, GL_REPLACE);
	glTexParameterf(GL_TEXTURE_2D, GL_TEXTURE_MIN_FILTER, GL_NEAREST);
	glTexParameterf(GL_TEXTURE_2D, GL_TEXTURE_MAG_FILTER, GL_NEAREST);

	glTexParameterf(GL_TEXTURE_2D, GL_TEXTURE_WRAP_S, GL_CLAMP);
	glTexParameterf(GL_TEXTURE_2D, GL_TEXTURE_WRAP_T, GL_CLAMP);

	glTexImage2D(GL_TEXTURE_2D, 0, GL_RGB, TEX_RES_X, TEX_RES_Y, 0, GL_RGB, GL_UNSIGNED_BYTE, g_Buffer);

	glBindTexture(GL_TEXTURE_2D, 0);

	glDisable(GL_TEXTURE_2D);
}


class Point {
public:

	Point(int x = 0, int y = 0) {
		this->x = x;
		this->y = y;
	}

	int x, y;
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

Point pointAfterRotation(Point center, Point p, float angle) {

	p.x = p.x - center.x;
	p.y = p.y - center.y;

	float x = (p.x * cos(angle)) + (p.y * -sin(angle));
	float y = (p.x * sin(angle)) + (p.y * cos(angle));

	p.x = x + center.x;
	p.y = y + center.y;

	return p;
}

Point pointAfterRotationExcercise2(Point center, Point p, float angle) {

	float a[3][3] = {
		{ 1.0f, 0.0f, center.x },
		{ 0.0f, 1.0f, center.y },
		{ 0.0f, 0.0f, 1.0f } };
	CMat3f matA(a);

	float b[3][3] = {
		{ cos(angle),-sin(angle), 0.0f },
		{ sin(angle), cos(angle), 0.0f },
		{ 0.0f, 0.0f, 1.0f } };
	CMat3f matB(b);

	float c[3][3] = {
		{ 1.0f, 0.0f, -center.x },
		{ 0.0f, 1.0f, -center.y },
		{ 0.0f, 0.0f, 1.0f } };
	CMat3f matC(c);

	float d[3] = { p.x, p.y, 1.0f };
	CVec3f vectorD(d);


	CVec3f resRotationTemporal = matA * matB * matC * vectorD;

	float resTemp[3];
	resRotationTemporal.getData(resTemp);

	Point res = Point(resTemp[0], resTemp[1]);

	return res;
}



void bhamCircleRotation(int coordx, int coordy, int r, Color c, float angle) {

	int x, y, d, deltaSE, deltaE;
	x = 0;
	y = r;
	d = 5 - 4 * r;


	Point temP = pointAfterRotation(Point(0, 0), Point(coordx, coordy), angle);
	coordx = temP.x;
	coordy = temP.y;



	glBegin(GL_POINTS);

	//axis
	glColor3f(c.r, c.g, c.b);
	glVertex2i(coordx + r, coordy);
	glVertex2i(coordx, coordy + r);
	glVertex2i(coordx - r, coordy);
	glVertex2i(coordx, coordy - r);

	while (y > x) {
		x++;

		if (d >= 0) {
			deltaSE = 4 * (2 * (x - y) + 5);
			d = d + deltaSE;
			y--;
		}
		else {
			deltaE = 4 * (2 * x + 3);
			d = d + deltaE;
		}

		glVertex2i(coordx + x, coordy + y);
		glVertex2i(coordx + x, coordy - y);
		glVertex2i(coordx - x, coordy + y);
		glVertex2i(coordx - x, coordy - y);
		glVertex2i(coordx + y, coordy + x);
		glVertex2i(coordx + y, coordy - x);
		glVertex2i(coordx - y, coordy + x);
		glVertex2i(coordx - y, coordy - x);

	}

	glEnd();
}


void bhamCircleRotationMoon(int xCenter, int yCenter, int coordx, int coordy, int r, Color c, float angleCenter, float angleMoon) {

	int x, y, d, deltaSE, deltaE;
	x = 0;
	y = r;
	d = 5 - 4 * r;

	Point tempCenter = pointAfterRotation(Point(0, 0), Point(xCenter, yCenter), angleCenter);
	Point rotatingCenter = Point(tempCenter.x, tempCenter.y);

	Point temP = Point(coordx, coordy);
	coordx = coordx + tempCenter.x - xCenter;
	coordy = coordy + tempCenter.y - yCenter;

	temP = Point(coordx, coordy);


	temP = pointAfterRotation(rotatingCenter, temP, angleMoon);
	//temP = pointAfterRotation(Point(300,0), temP, angleMoon);
	coordx = temP.x;
	coordy = temP.y;



	glBegin(GL_POINTS);

	//axis
	glColor3f(c.r, c.g, c.b);
	glVertex2i(coordx + r, coordy);
	glVertex2i(coordx, coordy + r);
	glVertex2i(coordx - r, coordy);
	glVertex2i(coordx, coordy - r);

	while (y > x) {
		x++;

		if (d >= 0) {
			deltaSE = 4 * (2 * (x - y) + 5);
			d = d + deltaSE;
			y--;
		}
		else {
			deltaE = 4 * (2 * x + 3);
			d = d + deltaE;
		}

		glVertex2i(coordx + x, coordy + y);
		glVertex2i(coordx + x, coordy - y);
		glVertex2i(coordx - x, coordy + y);
		glVertex2i(coordx - x, coordy - y);
		glVertex2i(coordx + y, coordy + x);
		glVertex2i(coordx + y, coordy - x);
		glVertex2i(coordx - y, coordy + x);
		glVertex2i(coordx - y, coordy - x);

	}

	glEnd();
}


void bhamCircleRotationExercise2(int coordx, int coordy, int r, Color c, float angle) {

	int x, y, d, deltaSE, deltaE;
	x = 0;
	y = r;
	d = 5 - 4 * r;


	Point temP = pointAfterRotationExcercise2(Point(0, 0), Point(coordx, coordy), angle);
	coordx = temP.x;
	coordy = temP.y;



	glBegin(GL_POINTS);

	//axis
	glColor3f(c.r, c.g, c.b);
	glVertex2i(coordx + r, coordy);
	glVertex2i(coordx, coordy + r);
	glVertex2i(coordx - r, coordy);
	glVertex2i(coordx, coordy - r);

	while (y > x) {
		x++;

		if (d >= 0) {
			deltaSE = 4 * (2 * (x - y) + 5);
			d = d + deltaSE;
			y--;
		}
		else {
			deltaE = 4 * (2 * x + 3);
			d = d + deltaE;
		}

		glVertex2i(coordx + x, coordy + y);
		glVertex2i(coordx + x, coordy - y);
		glVertex2i(coordx - x, coordy + y);
		glVertex2i(coordx - x, coordy - y);
		glVertex2i(coordx + y, coordy + x);
		glVertex2i(coordx + y, coordy - x);
		glVertex2i(coordx - y, coordy + x);
		glVertex2i(coordx - y, coordy - x);

	}

	glEnd();
}


void bhamCircleRotationMoonExercise2(int xCenter, int yCenter, int coordx, int coordy, int r, Color c, float angleCenter, float angleMoon) {

	int x, y, d, deltaSE, deltaE;
	x = 0;
	y = r;
	d = 5 - 4 * r;


	Point tempCenter = pointAfterRotationExcercise2(Point(0, 0), Point(xCenter, yCenter), angleCenter);
	Point rotatingCenter = Point(tempCenter.x, tempCenter.y);

	Point temP = Point(coordx, coordy);
	coordx = coordx + tempCenter.x - xCenter;
	coordy = coordy + tempCenter.y - yCenter;
	temP = Point(coordx, coordy);

	temP = pointAfterRotationExcercise2(rotatingCenter, temP, angleMoon);
	//temP = pointAfterRotationExcercise2(Point(300,0), temP, angleMoon);

	coordx = temP.x;
	coordy = temP.y;



	glBegin(GL_POINTS);

	//axis
	glColor3f(c.r, c.g, c.b);
	glVertex2i(coordx + r, coordy);
	glVertex2i(coordx, coordy + r);
	glVertex2i(coordx - r, coordy);
	glVertex2i(coordx, coordy - r);

	while (y > x) {
		x++;

		if (d >= 0) {
			deltaSE = 4 * (2 * (x - y) + 5);
			d = d + deltaSE;
			y--;
		}
		else {
			deltaE = 4 * (2 * x + 3);
			d = d + deltaE;
		}

		glVertex2i(coordx + x, coordy + y);
		glVertex2i(coordx + x, coordy - y);
		glVertex2i(coordx - x, coordy + y);
		glVertex2i(coordx - x, coordy - y);
		glVertex2i(coordx + y, coordy + x);
		glVertex2i(coordx + y, coordy - x);
		glVertex2i(coordx - y, coordy + x);
		glVertex2i(coordx - y, coordy - x);

	}

	glEnd();
}

void reshape(int w, int h) {

	g_WinWidth = w;
	g_WinHeight = h;

	glViewport(0, 0, w, h);					// Establish viewing area to cover entire window.

	glMatrixMode(GL_PROJECTION);			// Start modifying the projection matrix.
	glLoadIdentity();						// Reset project matrix.
	glOrtho(-w / 2, w / 2, -h / 2, h / 2, 0, 1);	// Map abstract coords directly to window coords.

	glutPostRedisplay();
}

// function to initialize our own variables
void init()
{
	// init timer interval
	g_iTimerMSecs = 10;

	g_iAngleEarth = 0;
	g_iAngleEarthIncr = 0.0349066;

	g_iAngleMoon = 0;
	g_iAngleMoonIncr = 0.0698132;


	// init variables for display2
	int aiPos[2] = { 0, 0 };
	int aiPosIncr[2] = { 2, 2 };
	g_vecPos.setData(aiPos);
	g_vecPosIncr.setData(aiPosIncr);
}

// function to initialize the view to ortho-projection
void initGL()
{
	glViewport(0, 0, g_iWidth, g_iHeight);	// Establish viewing area to cover entire window.

	glMatrixMode(GL_PROJECTION);			// Start modifying the projection matrix.
	glLoadIdentity();						// Reset project matrix.
	glOrtho(-g_iWidth / 2, g_iWidth / 2, -g_iHeight / 2, g_iHeight / 2, 0, 1);	// Map abstract coords directly to window coords.

	// tell GL that we draw to the back buffer and
	// swap buffers when image is ready to avoid flickering
	glDrawBuffer(GL_BACK);

	// tell which color to use to clear image
	glClearColor(0, 0, 0, 1);
}


int min(int a, int b) { return a > b ? a : b; }
// timer callback function
void timer(int value)
{

	if (g_iAngleEarth >= 6.17) g_iAngleEarth = 0;
	g_iAngleEarth += g_iAngleEarthIncr;

	if (g_iAngleMoon >= 6.17) g_iAngleMoon = 0;
	g_iAngleMoon += g_iAngleMoonIncr;


	// the last two lines should always be
	glutPostRedisplay();
	glutTimerFunc(g_iTimerMSecs, timer, 0);	// call timer for next iteration
}


// display callback function
void display1(void)
{

	glClear(GL_COLOR_BUFFER_BIT);

	Color c1(1, 1, 0);
	Color c2(0, 0, 1);
	Color c3(1, 1, 1);

	glBegin(GL_POINTS);


	Point origin = Point(0, 0);
	Point earth = Point(300, 0);
	Point moon = Point(350, 0);


	bhamCircleRotation(origin.x, origin.y, 30, c1, 0);
	bhamCircleRotation(earth.x, earth.y, 15, c2, g_iAngleEarth);
	bhamCircleRotationMoon(earth.x, earth.y, moon.x, moon.y, 9, c3, g_iAngleEarth, g_iAngleMoon);
	glEnd();


	// In double buffer mode the last
	// two lines should alsways be
	glFlush();
	glutSwapBuffers(); // swap front and back buffer
}


// display callback function
void display2(void)
{
	glClear(GL_COLOR_BUFFER_BIT);

	Color c1(1, 1, 1);
	Color c2(1, 1, 1);
	Color c3(1, 1, 1);

	glBegin(GL_POINTS);


	Point origin = Point(0, 0);
	Point earth = Point(300, 0);
	Point moon = Point(350, 0);


	bhamCircleRotationExercise2(origin.x, origin.y, 30, c1, 0);
	bhamCircleRotationExercise2(earth.x, earth.y, 15, c2, g_iAngleEarth);
	bhamCircleRotationMoonExercise2(earth.x, earth.y, moon.x, moon.y, 9, c3, g_iAngleEarth, g_iAngleMoon);

	glEnd();


	// In double buffer mode the last
	// two lines should alsways be
	glFlush();
	glutSwapBuffers(); // swap front and back buffer
}


void keyboard(unsigned char key, int x, int y)
{
	switch (key) {
	case 'q':
	case 'Q':
		exit(0); // quit program
		break;
	case '1':
		glutDisplayFunc(display1);
		//glutPostRedisplay ();	// not needed since timer triggers redisplay
		break;
	case '2':
		glutDisplayFunc(display2);
		//glutPostRedisplay ();	// not needed since timer triggers redisplay
		break;
	default:
		// do nothing ...
		break;
	};
}

int main(int argc, char **argv)
{
	glutInit(&argc, argv);
	// we have to use double buffer to avoid flickering
	// TODO: lookup "double buffer", what is it for, how is it used ...
	glutInitDisplayMode(GLUT_DOUBLE | GLUT_RGB);

	glutCreateWindow("Übung 2");
	glutReshapeWindow(800, 800);

	glutReshapeFunc(reshape);	// zuständig für Größenänderungen des Fensters

	init();	// init my variables first
	initGL();	// init the GL (i.e. view settings, ...)

	// assign callbacks
	glutTimerFunc(10, timer, 0);
	glutKeyboardFunc(keyboard);
	glutDisplayFunc(display1);
	// you might want to add a resize function analog to
	// Übung1 using code similar to the initGL function ...

	// start main loop
	glutMainLoop();

	return 0;
}