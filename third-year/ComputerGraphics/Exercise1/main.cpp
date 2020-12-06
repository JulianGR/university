#include <cmath>
#include "vec.h"
#include "mat.h"
#include "Uebung2/Uebung2/glut.h"
#include "glut.h"
#include <iostream>
using namespace std;

/////////////////////////////////////////////////////////////////////////////////////////
//
// Hier wird einiges initialisiert. Sie dürfen sich gerne den Code anschauen und
// versuchen ihn zu verstehen. Das sollte Ihnen nicht allzu schwer fallen. Es werden
// jedoch einige Techniken benutzt (Texturen, ...) die wir in der Vorlesung noch
// nicht besprochen haben, die wir aber aus techn. Gründen hier benötigen.
//
// Weiter unten finden Sie einen Vermerk, ab dem Sie den Code jedoch vollständig
// verstehen sollten!!!


// Auflösungen der gesamten Textur
// !!!ACHTUNG!!! nicht alle Texturauflösungen funktionieren!
// Stichwort ungefähr: POT2 Problematik
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

// Auflösung des Hauptfensters (kann sich durch User ändern)
int g_WinWidth = 800;
int g_WinHeight = 800;

// Funktion organisiert die Textur.
// Kümmern Sie sich nicht weiter um diese Funktion, da
// sie momentan nur ein notwendiges Übel darstellt!
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

// Callback Funktion um die Fenstergrößen anzupassen.
// Auch diese Funktion ist ein notwendiges Übel! Kümmern
// Sie sich im Moment nicht weiter darum.
void reshape(int w, int h) {

	g_WinWidth = w;
	g_WinHeight = h;

	glViewport(0, 0, w, h);					// Establish viewing area to cover entire window.

	glMatrixMode(GL_PROJECTION);			// Start modifying the projection matrix.
	glLoadIdentity();						// Reset project matrix.
	glOrtho(-w / 2, w / 2, -h / 2, h / 2, 0, 1);	// Map abstract coords directly to window coords.

	glutPostRedisplay();
}




//
//
/////////////////////////////////////////////////////////////////////////////////////////
//
// Hier fängt der für Sie wirklich relevante Teil des Programms an.
//




// Eine überaus primitive Punktklasse
class Point {
public:

	Point(int x = 0, int y = 0) {
		this->x = x;
		this->y = y;
	}

	int x, y;
};

// Eine überaus primitive Farbklasse
class Color {
public:

	Color(float r = 1.0f, float g = 1.0f, float b = 1.0f) {
		this->r = r;
		this->g = g;
		this->b = b;
	}

	float r, g, b;
};

// Funktion löscht den Bildschirm mit der angegebenen Farbe
// Usage z.B.: clearImage (Color (1,1,1))
// löscht den Bildschirm in Weiß.
// Ohne Farbangabe ist der Standard Weiß
void clearImage(Color c = Color()) {
	for (int i = 0; i < TEX_RES; i++) {
		g_Buffer[3 * i] = 255.0 * c.r;
		g_Buffer[3 * i + 1] = 255.0 * c.g;
		g_Buffer[3 * i + 2] = 255.0 * c.b;
	}
}

// Funktion malt einen Punkt an die angegebenen Koordinaten
// Usage z.B.: setPoint (Point(10, 5), Color (1,0,0))
// malt einen Punkt an die Koordinate (10, 5)
// Ohne Farbangabe ist die standard Malfarbe Schwarz
//
// Nutzen Sie diese Funktion ...
void setPoint(Point p, Color c = Color(0, 0, 0)) {
	int x = p.x + TEX_HALF_X;
	int y = p.y + TEX_HALF_Y;
	if (x < 0 || y < 0 || x >= TEX_RES_X || y >= TEX_RES_Y) {
		cerr << "Illegal point co-ordinates (" << p.x << ", " << p.y << ")\n" << flush;
		return;
	}

	g_Buffer[3 * TO_LINEAR(x, y)] = 255.0 * c.r;
	g_Buffer[3 * TO_LINEAR(x, y) + 1] = 255.0 * c.g;
	g_Buffer[3 * TO_LINEAR(x, y) + 2] = 255.0 * c.b;
}

//
// ÜBUNG 1 AUFGABE 1:
//
// Diese Funktion soll eine Gerade zwischen den Punkten
// p1 und p2 in der Farbe c malen. Benutzen Sie die Funktion
// setPoint um die individuellen Punkte zu zeichnen.

void bhamLine(Point p1, Point p2, Color c) {
	//we want to translate the first point to the origin, and the move the second
	int x1 = 0;
	int y1 = 0;

	int x2 = p2.x - p1.x;
	int y2 = p2.y - p1.y;


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
				int tempX = x2;
				x2 = y2;
				y2 = tempX;
				switched = true;
			}
		}
		else {
			cuadrant = 4;
			if (x2 < y2) {
				int tempX = x2;
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
				int tempX = x2;
				x2 = y2;
				y2 = tempX;
				switched = true;
			}
		}
		else {
			if (x2 < y2) {
				int tempX = x2;
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
		int temporalX1, temporalY1;

		//with these, we do in the inverse order the transformations we did before (if the line is in the 2ndCuadrant for instance, we had to put it to the first cuadrant,
		//and now we are creating temporal variables that make back the changes)

		if (switched == false) {
			switch (cuadrant) {
			case 2:
				temporalX1 = x1 - p1.x;
				temporalY1 = y1 + p1.y;
				setPoint(Point(-temporalX1, temporalY1));
				break;
			case 3:
				temporalX1 = x1 - p1.x;
				temporalY1 = y1 - p1.y;
				setPoint(Point(-temporalX1, -temporalY1));
				break;
			case 4:
				temporalX1 = x1 + p1.x;
				temporalY1 = y1 - p1.y;
				setPoint(Point(temporalX1, -temporalY1));
				break;
			default:
				temporalX1 = x1 + p1.x;
				temporalY1 = y1 + p1.y;
				setPoint(Point(temporalX1, temporalY1));
				break;
			}
		}
		else {
			switch (cuadrant) {
			case 2:
				temporalX1 = x1 + p1.y;
				temporalY1 = y1 - p1.x;
				setPoint(Point(-temporalY1, temporalX1));
				break;
			case 3:
				temporalX1 = x1 - p1.y;
				temporalY1 = y1 - p1.x;
				setPoint(Point(-temporalY1, -temporalX1));
				break;
			case 4:
				temporalX1 = x1 - p1.y;
				temporalY1 = y1 + p1.x;
				setPoint(Point(temporalY1, -temporalX1));
				break;
			default:
				temporalX1 = x1 + p1.y;
				temporalY1 = y1 + p1.x;
				setPoint(Point(temporalY1, temporalX1));
				break;

			}

		}

	}
	//to make the points point out =)
	setPoint(p1, c);

	setPoint(p2, c);
}

//
// ÜBUNG 1 AUFGABE 2:
//
// Diese Funktion soll einen Kreis mit Radius r um den Punkt p
// malen. Benutzen Sie die Funktion setPoint um die individuellen
// Punkte zu zeichnen. Vergessen Sie nicht auch den Mittelpunkt zu
// zeichnen!
void bhamCircle(Point p, int r, Color c) {

	int x, y, d, deltaSE, deltaE;
	x = 0;
	y = r;
	d = 5 - 4 * r;


	//axis
	setPoint(Point(p.x + r, p.y));
	setPoint(Point(p.x, p.y + r));
	setPoint(Point(p.x - r, p.y));
	setPoint(Point(p.x, p.y - r));

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

		setPoint(Point(p.x + x, p.y + y));
		setPoint(Point(p.x + x, p.y - y));
		setPoint(Point(p.x - x, p.y + y));
		setPoint(Point(p.x - x, p.y - y));


		setPoint(Point(p.x + y, p.y + x));
		setPoint(Point(p.x + y, p.y - x));
		setPoint(Point(p.x - y, p.y + x));
		setPoint(Point(p.x - y, p.y - x));

	}


	setPoint(p, c);
}

// Die Callback Funktion die für das eigentliche Malen
// zuständig ist. Im Wesentlichen brauchen Sie sich nur
// um den Bereich zwischen den Kommentaren zu kümmern,
// alles andere ist wiederum ein notwendiges Übel!
void display(void) {

	//////////////////////////////////////////////////////////////////
	//
	// Hier sollen Ihre Bresenham-Funktionen
	// eine Gerade und einen Kreis zeichnen.
	//
	// Im Prinzip brauchen Sie hier keine
	// großartigen Änderungen vorzunehmen, es
	// sei denn Sie wollen "spielen" :-D
	//

	clearImage();			// altes Bild löschen

	Point p0(-3, 2);
	Point p1(17, 13);
	Point p2(13, 17);
	Point p3(-13, 17);
	Point p4(-17, 13);
	Point p5(-17, -13);
	Point p6(-13, -17);
	Point p7(13, -17);
	Point p8(17, -13);


	Color cl(1, 0, 0);		// es soll eine rote Gerade sein ...

	bhamLine(p0, p1, cl);
	bhamLine(p0, p2, cl);
	bhamLine(p0, p3, cl);
	bhamLine(p0, p4, cl);
	bhamLine(p0, p5, cl);
	bhamLine(p0, p6, cl);
	bhamLine(p0, p7, cl);
	bhamLine(p0, p8, cl);


	Point p(1, -14);		// Mittelpunkt für Kreis definieren
	int r = 7;			// Radius festlegen
	Color cc(0, 0, 1);		// es soll ein blauer Kreis sein ...


	Point pc(-20, 3);
	int rc = 4;

	bhamCircle(p, r, cc);	// Kreis zeichnen ...
	bhamCircle(pc, rc, cc);



	//origin
	Point oriPoint(0, 0);
	Color oriColor = Color(123, 217, 121);
	setPoint(oriPoint, oriColor);



	Point px(30, 0);
	Point py(0, 30);
	Point pmx(-30, 0);
	Point pmy(0, -30);
	Point pd1(30, 30);
	Point pd2(-30, 30);
	Point pd3(-30, -30);
	Point pd4(30, -30);

	bhamLine(oriPoint, px, oriColor);
	bhamLine(oriPoint, py, oriColor);
	bhamLine(oriPoint, pmx, oriColor);
	bhamLine(oriPoint, pmy, oriColor);
	bhamLine(oriPoint, pd1, oriColor);
	bhamLine(oriPoint, pd2, oriColor);
	bhamLine(oriPoint, pd3, oriColor);
	bhamLine(oriPoint, pd4, oriColor);

	//
	// Ab hier sollten Sie nichts mehr ändern!
	//
	//////////////////////////////////////////////////////////////////

	manageTexture();

	glClear(GL_COLOR_BUFFER_BIT);
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
	glEnd();

	glBindTexture(GL_TEXTURE_2D, 0);
	glDisable(GL_TEXTURE_2D);

	glFlush();
}

// Die Main-Funktion
int main(int argc, char** argv) {

	glutInit(&argc, argv);
	glutInitWindowSize(g_WinWidth, g_WinHeight);
	glutCreateWindow("Übung 1: Bresenham");

	glutReshapeFunc(reshape);	// zuständig für Größenänderungen des Fensters
	glutDisplayFunc(display);	// zuständig für das wiederholte Neuzeichnen des Bildschirms

	glutMainLoop();

	glDeleteTextures(1, &g_TexID); // löscht die oben angelegte Textur

	return 0;
}
