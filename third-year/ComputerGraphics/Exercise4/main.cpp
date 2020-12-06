#include <cmath>
#include "vec.h"
#include "mat.h"
#include "Uebung2/Uebung2/glut.h"
#include "glut.h"
#include <iostream>
using namespace std;
//#pragma comment(lib, "MSCOREE.lib")


#define TEX_RES_X 400
#define TEX_RES_Y 400
#define TEX_RES TEX_RES_X*TEX_RES_Y
#define TO_LINEAR(x, y) (((x)) + TEX_RES_X*((y)))
#define PI 3.1415926535

#define max(a ,b) (((a) > (b)) ? (a) : (b))
#define min(a ,b) (((a) < (b)) ? (a) : (b))



char g_Buffer[3 * TEX_RES];
GLuint g_TexID = 0;

int g_WinWidth = 400;
int g_WinHeight = 400;



//////////////////////////////////////////////////////////////////

// Kugel

CVec3f M;
int R;
float rK, gK, bK;

// Licht
float xL, yL, zL;
float angleYZ, angleXZ;
float radL;

// Augpunkt
CVec3f A;



void display();
void keyboard(unsigned char key, int x, int y);
void reshape(int w, int h);


void reset() {

	float MCoords[3] = { 0.0f, 0.0f, 100.0f };
	M = CVec3f(MCoords);



	::R = 50;
	::rK = 0.9;
	::gK = 0.42;
	::bK = 0.82;


	::xL = 100;
	::yL = 1000;
	::zL = 0;
	::angleXZ = 1.5 * PI;
	::angleYZ = 0;
	::radL = 1000;


	float ACoords[3] = { 0.0f, 0.0f, -1.0f };
	A = CVec3f(ACoords);

}

//////////////////////////////////////////////////////////////////


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

	void print(char * name) {
		cout << name << " = (" << r << ", " << g << ", " << b << ")" << endl;
	}
};

void manageTexture() {

	glEnable(GL_TEXTURE_2D);

	if (g_TexID == 0)
		glGenTextures(1, &g_TexID);

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

void mapTexture() {
	glBindTexture(GL_TEXTURE_2D, g_TexID);

	glEnable(GL_TEXTURE_2D);
	glBegin(GL_QUADS);

	glColor3f(1, 0, 0);

	glTexCoord2f(0, 0);
	glVertex2f(-g_WinWidth / 2, -g_WinHeight / 2);
	glTexCoord2f(1, 0);
	glVertex2f(g_WinWidth / 2, -g_WinHeight / 2);
	glTexCoord2f(1, 1);
	glVertex2f(g_WinWidth / 2, g_WinHeight / 2);
	glTexCoord2f(0, 1);
	glVertex2f(-g_WinWidth / 2, g_WinHeight / 2);




	glClearColor(1, 0, 0, 1);
	glClear(GL_COLOR_BUFFER_BIT);


	glEnd();
	glBindTexture(GL_TEXTURE_2D, 0);
	glDisable(GL_TEXTURE_2D);
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

void clearTexture(Color c = Color()) {
	for (int i = 0; i < TEX_RES; i++) {
		g_Buffer[3 * i] = 255.0*c.r;
		g_Buffer[3 * i + 1] = 255.0*c.g;
		g_Buffer[3 * i + 2] = 255.0*c.b;
	}
}


void setPoint(Point p, Color c = Color(0, 0, 0)) {
	if (p.x < 0 || p.y < 0 || p.x > TEX_RES_X - 1 || p.y > TEX_RES_Y - 1) {
		cerr << "Illegal point co-ordinates (" << p.x << ", " << p.y << ")\n" << flush;
		return;
	}

	g_Buffer[3 * TO_LINEAR(p.x, p.y)] = 255.0*c.r;
	g_Buffer[3 * TO_LINEAR(p.x, p.y) + 1] = 255.0*c.g;
	g_Buffer[3 * TO_LINEAR(p.x, p.y) + 2] = 255.0*c.b;
}

void setQuad(Point p, Color c) {
	for (int x = p.x; x < p.x + 10; x++)
		for (int y = p.y; y < p.y + 10; y++)
			setPoint(Point(x, y), c);
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

float scalarProduct(CVec4f first, CVec4f second) {
	return first(0) * second(0) + first(1) * second(1) + first(2) *second(2) + first(3) * second(3);
}

float scalarProduct(CVec3f first, CVec3f second) {
	return first(0) * second(0) + first(1) * second(1) + first(2) *second(2);
}

Point parsePoint(CVec3f vec) {

	float res[3];
	vec.getData(res);

	return Point(res[0], res[1], res[2]);
}


/////////////////////////////////////////////////////////////////////////////////////////
/////////////////////////////////////////////////////////////////////////////////////////




CVec3f intersect(CVec3f EyePos, CVec3f ViewDir) {
	CVec3f hit;
	float t1;
	float t2;

	float b = 2 * scalarProduct(ViewDir, EyePos - M); 
	float a = scalarProduct(ViewDir, ViewDir);
	float c = scalarProduct(EyePos - M, EyePos - M) - R * R;


	float tmp = pow(b, 2) - 4 * a * c;
	if (tmp < 0) {
		 t1= -1;
	}
	else {
		float x = (-b + sqrt(pow(b, 2) - 4 * a * c)) / 2 * a;
		t1 = x;
	}	

	float tmp2 = pow(b, 2) - 4 * a * c;
	if (tmp2 < 0) {
		t2= -1;
	}
	else {
		float x = (-b - sqrt(pow(b, 2) - 4 * a * c)) / 2 * a;
		t2 = x;
	}

	float t;
	if (t1 < 0 && t2 >= 0) {
		t = t2;
	}
	else if (t1 >= 0 && t2 < 0) {
		t = t1;
	}
	else if (t1 >= 0 && t2 >= 0) {
		t = min(t1, t2);
	}
	else {
		return EyePos;
	}
	hit = EyePos + ViewDir * t;

	return hit;
}

Color phong(CVec3f HitPos, CVec3f ViewDir) {

	// Phong-Parameter


	CVec3f Ka;		// ambient
	Ka(0) = rK;
	Ka(1) = gK;
	Ka(2) = bK;

	CVec3f Kd;		// diffuse
	Kd(0) = rK;
	Kd(1) = gK;
	Kd(2) = bK;

	CVec3f Ks;		// specular
	Ks(0) = 1;
	Ks(1) = 1;
	Ks(2) = 1;



	float total0 = Ka(0) + Kd(0) + Ks(0);
	Ka(0) = Ka(0) / total0;
	Kd(0) = Kd(0) / total0;
	Ks(0) = Ks(0) / total0;

	float total1 = Ka(1) + Kd(1) + Ks(1);
	Ka(1) = Ka(1) / total1;
	Kd(1) = Kd(1) / total1;
	Ks(1) = Ks(1) / total1;

	float total2 = Ka(2) + Kd(2) + Ks(2);
	Ka(2) = Ka(2) / total2;
	Kd(2) = Kd(2) / total2;
	Ks(2) = Ks(2) / total2;


	int exp = 28;	       // shininess


	CVec3f Ia;		// ambient
	Ia(0) = 1;
	Ia(1) = 1;
	Ia(2) = 1;

	CVec3f Id;		// diffuse
	Id(0) = 1.0;
	Id(1) = 1.0;
	Id(2) = 1.0;

	CVec3f Is;		// specular
	Is(0) = 0.1;
	Is(1) = 0.1;
	Is(2) = 0.1;


	float lCoords[3] = { xL, yL, zL };
	CVec3f lVec(lCoords);
	CVec3f lightDir = toNorm(lVec - HitPos);

	ViewDir = toNorm(ViewDir *-1);

	CVec3f normalDir = toNorm(HitPos - M);

	CVec3f  reflexDir = normalDir * 2 * scalarProduct(lightDir, normalDir) - lightDir;
	
	reflexDir = toNorm(reflexDir);
	
	float i0 = Kd(0)*  max(0, scalarProduct(lightDir, normalDir)) + Ks(0) *  pow(max(0, scalarProduct(reflexDir, ViewDir)), exp) + Ka(0) * Ia(0);
	float i1 = Kd(1)*  max(0, scalarProduct(lightDir, normalDir)) + Ks(1) *  pow(max(0, scalarProduct(reflexDir, ViewDir)), exp) + Ka(1) * Ia(1);
	float i2 = Kd(2)*  max(0, scalarProduct(lightDir, normalDir)) + Ks(2) *  pow(max(0, scalarProduct(reflexDir, ViewDir)), exp) + Ka(2) * Ia(2);
	
	return Color( i0, i1,i2);
}


void rayCast() {
	clearTexture();

	CVec3f e, v;

	e(0) = 0;
	e(1) = 0;
	e(2) = A(2);

	v(2) = -A(2);

	for (int x = 0; x < TEX_RES_X; x++) {
		for (int y = 0; y < TEX_RES_Y; y++) {
			v(0) = -1 + 2 * x / static_cast<float>(TEX_RES_X - 1);
			v(1) = -1 + 2 * y / static_cast<float>(TEX_RES_Y - 1);

			CVec3f hit = intersect(e, v);

			Color c = Color(0.8, 0.8, 0.8);
			if (hit(2) != -1) {
				c = phong(hit, v);
			}

			setPoint(Point(x, y), c);
		}
	}
	cout << "raycast done" << endl;
}


void display(void) {

	manageTexture();

	mapTexture();


	glFlush();
	glutSwapBuffers();
}

void init() {

	reset();
	rayCast();

	cout << "===INSTRUCTIONS===" << endl;
	cout << "Press [x], [X], [y] or [Y] for moving the light source" << endl;
	cout << "Press [r], [R], [g], [G], [b], [B] for changing the color (red, green, blue) of the geometry " << endl;
	cout << "Press [d] or [D] to change the radius of the sphere" << endl;
	cout << "Press [t] or [T] to reset the scene" << endl;
	cout << "Press [q] or [Q] to exit" << endl;

}

void calcL() {
	xL = radL * cos(angleYZ) * cos(angleXZ);
	zL = radL * sin(angleYZ) * sin(angleXZ);
	yL = radL * cos(angleYZ);
	cout << "L= (" << xL << ", " << yL << ", " << zL << ")" << endl;
}

void keyboard(unsigned char c, int x, int y) {
	switch (c) {

	case 'x': {
		if (angleXZ > PI) {
			angleXZ = angleXZ - 0.15f;
			calcL();
		}
		else {
			cout << "angleXZ < PI " << endl;

		}
		break;
	}
	case 'X': {
		if (angleXZ < 2 * PI) {
			angleXZ = angleXZ + 0.15f;
			calcL();
		}
		else {

			cout << "angleXZ > 2*PI " << endl;
		}
		break;
	}
	case 'y': {
		if (angleYZ > 0) {
			angleYZ = angleYZ - 0.1f;
			calcL();
		}
		else {
			cout << "angleYZ < 0 " << endl;
		}
		break;
	}
	case 'Y': {
		if (angleYZ < PI) {
			angleYZ = angleYZ + 0.1;
			calcL();
		}
		else {
			cout << "angleYZ > PI " << endl;
		}
		break;
	}
	case 'r': {
		if (rK > 0) {
			rK = rK - 0.05f;
		}
		break;
	}
	case 'R': {
		if (rK < 1) {
			rK = rK + 0.05f;
		}
		break;
	}
	case 'g': {
		if (gK > 0) {
			gK = gK - 0.05f;
		}
		break;
	}
	case 'G': {
		if (gK < 1) {
			gK = gK + 0.05f;
		}
		break;
	}
	case 'b': {
		if (bK > 0) {
			bK = bK - 0.05f;
		}
		break;
	}
	case 'B': {
		if (bK < 1) {
			bK = bK + 0.05f;
		}
		break;
	}
	case 'd': {
		if (radL > 0) {
			R = R - 1.0f;
		}
		break;
	}
	case 'D': {
		R = R + 1.0f;
		break;
	}
	case 't':
	case 'T': {
		reset();
		break;
	}
	case 'q':
	case 'Q': {
		exit(0);
		break;
	}
	default:
		cout << "===INSTRUCTIONS===" << endl;
		cout << "Press [x], [X], [y] or [Y] for moving the light source" << endl;
		cout << "Press [r], [R], [g], [G], [b], [B] for changing the color (red, green, blue) of the geometry " << endl;
		cout << "Press [d] or [D] to change the radius of the sphere" << endl;
		cout << "Press [t] or [T] to reset the scene" << endl;
		cout << "Press [q] or [Q] to exit" << endl;
		break;
	}
	rayCast();
	glutPostRedisplay();
}

int main(int argc, char **argv) {
	glutInit(&argc, argv);
	glutInitDisplayMode(GLUT_DOUBLE | GLUT_RGB);
	glutInitWindowSize(g_WinWidth, g_WinHeight);

	glutCreateWindow("Übung 4: Phong");

	init();

	glutKeyboardFunc(keyboard);
	glutReshapeFunc(reshape);
	glutDisplayFunc(display);


	glutMainLoop();

	glDeleteTextures(1, &g_TexID);

	return 0;
}