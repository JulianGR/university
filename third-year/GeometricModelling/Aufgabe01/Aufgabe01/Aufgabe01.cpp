////////////////////////////////////////////////////////////////////
//
//	Georg Umlauf, (c) 2012
//
////////////////////////////////////////////////////////////////////

#include "color.h"
#include "AffineMap.h"
#include "Quader.h"
#include "viewSystem.h"

#include <windows.h>
#include <iostream>
#include <chrono>
#include <thread>
using namespace std;

// might be you have to switch to
// #include "glut.h" depending on your GLUT installation
#include "glut.h"

////////////////////////////////////////////////////////////
//
// system relevant global variables
//
const int g_iWidth  = 512; // window width  (choose an appropriate size)
const int g_iHeight = 512; // window height (choose an appropriate size)
//
/////////////////////////////////////////////////////////////

////////////////////////////////////////////////////////////
//
// private, global variables 
viewSystem view;
Quader Q1,Q2,Q3;
const double F_STEP = 5.000;		// step width for zooms
const double R_STEP = 0.050;		// step width for rotations
const double S_STEP = 0.008;		// step width for (S)LERPs
const double T_STEP = 3.000;		// step width for translations

// start and end orientations: positions are vectors to be able to compute with them
Vector startDir = Vector(0, 0, -1, 0);
Vector startUp = Vector(0, 1, 0, 0);
Vector endDir = Vector(-0.5, 1, -1, 0);
Vector endUp = Vector(0.5, 1, 1, 0);

//
/////////////////////////////////////////////////////////////

void init()
{	// function to initialize our own variables
	view.setData(Point(0, 0, 0, 1),	// EyePoint
		Vector(0, 0, -1, 0),	// ViewDir
		Vector(0, 1, 0, 0),	// ViewUp
		299);					// Focal length
	view.setMode(RotationMode::VIEW_MATRIX_MODE);	// Rotation mode (matrix, formula, quaternion)

	// set three boxes
	Q1.setData(Point(-25, -25, -25, 1), Point(25, 25, 25, 1));
	Q2.setData(Point(0, 0, 0, 1), Point(100, 100, 100, 1));
	Q3.setData(Point(-155, -55, -75, 1), Point(-90, 50, 50, 1));
}

void normalizeInit() {
	startDir.normalize();
	endDir.normalize();
	startUp.normalize();
	endUp.normalize();
}



void initGL()
{	// function to initialize the view to ortho-projection
	glViewport(0, 0, g_iWidth, g_iHeight);	// Establish viewing area to cover entire window.

	glMatrixMode(GL_PROJECTION);			   // Start modifying the projection matrix.
	glLoadIdentity();						         // Reset project matrix.
	glOrtho(-g_iWidth / 2, g_iWidth / 2, -g_iHeight / 2, g_iHeight / 2, 0, 1);	// Map abstract coords directly to window coords.

	// tell GL to draw to the back buffer and swap buffers when image is ready to avoid flickering
	glDrawBuffer(GL_BACK);

	// tell which color to use to clear image
	glClearColor(0, 0, 0, 1);
}

void display(void) {

	glClear(GL_COLOR_BUFFER_BIT);

	// draw the three boxes
	Q1.draw(view, Color(1, 1, 1));
	Q2.draw(view, Color(1, 1, 0));
	Q3.draw(view, Color(0, 1, 0));

	glFlush();
	glutSwapBuffers(); // swap front and back buffer
}

void keyboard(unsigned char key, int x, int y)
{
	switch (key) {
		// Fokallnge ndern ------------------------------------------------------------------
	case 'f': view.Zoom(-F_STEP); break;
	case 'F': view.Zoom(F_STEP); break;
		// Rotationen des Sichtsystems um die Achsen des Weltkoordinatensystems----------------
	case 'X': view.RotateX(R_STEP); break;
	case 'x': view.RotateX(-R_STEP); break;
	case 'Y': view.RotateY(R_STEP); break;
	case 'y': view.RotateY(-R_STEP); break;
	case 'Z': view.RotateZ(R_STEP); break;
	case 'z': view.RotateZ(-R_STEP); break;
		// Rotationen des Sichtsystems um die Achsen des Sichtkoordinatensystems----------------
	case 'A': view.RotateDir(R_STEP); break;
	case 'a': view.RotateDir(-R_STEP); break;
	case 'B': view.RotateUp(R_STEP); break;
	case 'b': view.RotateUp(-R_STEP); break;
	case 'C': view.RotateHor(R_STEP); break;
	case 'c': view.RotateHor(-R_STEP); break;
		// Verschiebung des Sichtsystems entlang der Achsen des Weltkoordinatensystems----------
	case 'U': view.Translate(Vector(T_STEP, 0, 0, 0)); break;
	case 'u': view.Translate(Vector(-T_STEP, 0, 0, 0)); break;
	case 'V': view.Translate(Vector(0, T_STEP, 0, 0)); break;
	case 'v': view.Translate(Vector(0, -T_STEP, 0, 0)); break;
	case 'W': view.Translate(Vector(0, 0, T_STEP, 0)); break;
	case 'w': view.Translate(Vector(0, 0, -T_STEP, 0)); break;
		// (S)LERPs-----------------------------------------------------------------------------
	case 'l':
	case 'L': // LERP
	{
		normalizeInit();

		for (double t = 0; t <= 1; t += S_STEP)
		{
			// compute interpolated orientation
			Vector newDir = startDir * (1.0 - t) + endDir * t;
			Vector newUp = startUp * (1.0 - t) + endUp * t;
			
			view.setData(Point(0,0,0, 1), newDir, newUp, 299);
			
			// display changes
			display();

			// wait 0.01 second
			this_thread::sleep_for(chrono::nanoseconds(10000000));
		}
		break;
	}
	case 'n':
	case 'N': // NLERP
	{
		//normalizeInit();

		Quaternion dirQ1 = Quaternion(startDir).normalized();
		Quaternion dirQ2 = Quaternion(endDir).normalized();
		Quaternion dirTmp = (dirQ1.inverse() * dirQ2);

		Quaternion upQ1 = Quaternion(startUp).normalized();
		Quaternion upQ2 = Quaternion(endUp).normalized();
		Quaternion upTmp = (upQ1.inverse() * upQ2);

		for (double t = 0; t <= 1; t += S_STEP)
		{
			// compute interpolated orientation
			Quaternion newDir = dirQ1 * dirTmp.power(t);
			Quaternion newUp = upQ1 * upTmp.power(t);

			view.setData(Point(0, 0, 0, 1), newDir.normalized().getIm(), newUp.normalized().getIm(), 299);

			// display changes
			display();

			// wait 0.01 second
			this_thread::sleep_for(chrono::nanoseconds(10000000));
		}
		break;
	}
	case 's':
	case 'S': // SLERP
	{
		normalizeInit();

		Quaternion dirQ1 = Quaternion(startDir);
		Quaternion dirQ2 = Quaternion(endDir);
		Quaternion dirTmp = (dirQ1.inverse() * dirQ2);

		Quaternion upQ1 = Quaternion(startUp);
		Quaternion upQ2 = Quaternion(endUp);
		Quaternion upTmp = (upQ1.inverse() * upQ2);

		for (double t = 0; t <= 1; t += S_STEP)
		{
			// compute interpolated orientation
			Quaternion newDir = dirQ1 * dirTmp.power(t);
			Quaternion newUp = upQ1 * upTmp.power(t);

			view.setData(Point(0, 0, 0, 1), newDir.getIm(), newUp.getIm(), 299);

			// display changes
			display();

			// wait 0.01 second
			this_thread::sleep_for(chrono::nanoseconds(10000000));
		}
		break;
	}
	// Help--------------------------------------------------------------------------------
	case 'h': case 'H':
		cout << "Tastenbelegungen:" << endl << endl;
		cout << "a, A: Rotation des Sichtsystems um ViewDir." << endl;
		cout << "b, B: Rotation des Sichtsystems um ViewUp. " << endl;
		cout << "c, C: Rotation des Sichtsystems um ViewHor." << endl << endl;
		cout << "x, X: Rotation des Sichtsystems um die x-Weltachse." << endl;
		cout << "y, Y: Rotation des Sichtsystems um die y-Weltachse." << endl;
		cout << "z, Z: Rotation des Sichtsystems um die z-Weltachse." << endl << endl;
		cout << "u, U: Translation des Sichtsystems in Richtung der x-Weltachse." << endl;
		cout << "v, V: Translation des Sichtsystems in Richtung der y-Weltachse." << endl;
		cout << "w, W: Translation des Sichtsystems in Richtung der z-Weltachse." << endl << endl;
		cout << "f, F: Fokallaenge verkleinern/vergroessern." << endl << endl;
		cout << "l, L:  LERPs aus Startposition." << endl;
		cout << "n, N: NLERPs aus Startposition." << endl;
		cout << "s, S: SLERPs aus Startposition." << endl << endl;
		cout << "m, M: Rotationsmodus umschalten: Matrix-/Formel-/Quaternion-Mode." << endl << endl;
		cout << "r, R: Reset." << endl << endl;
		cout << "q, Q: Quit." << endl << endl;
		break;
		// Matrix-Mode durchschalten------------------------------------------------------------

      case 'm': case 'M': view.stepMode();	cout << "Rotation-Mode: " << view << endl; break;
      // Reset--------------------------------------------------------------------------------
      case 'r': case 'R': init( ); break;
		// Quit---------------------------------------------------------------------------------
		case 'q': case 'Q': exit(0); break;
      // -------------------------------------------------------------------------------------
		default:    			        break;
	}

	glutPostRedisplay ();
}


int main (int argc, char **argv) {
	glutInit (&argc, argv);
	glutInitDisplayMode(GLUT_DOUBLE|GLUT_RGB);
	glutCreateWindow ("GM Aufgabe 1");

	init  ();	// init my variables first
	initGL();	// init the GL (i.e. view settings, ...)

	// assign callbacks
	glutKeyboardFunc(keyboard);
	glutDisplayFunc (display);

	// start main loop
	glutMainLoop ();

	return 0;
}
