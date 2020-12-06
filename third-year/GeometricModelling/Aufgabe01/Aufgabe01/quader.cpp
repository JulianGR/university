////////////////////////////////////////////////////////////////////
//
//	Georg Umlauf, (c) 2012
//
////////////////////////////////////////////////////////////////////

#include "Quader.h"
#include "glut.h"

// constructors
Quader::Quader()
{
}

Quader::Quader(const Point& l, const Point& u)
{
	for (int i=0, j=0; i<4; i++, j=(i>1)?2:0) {
		q[2*i  ][0] = l[0]; q[i+j  ][1] = l[1]; q[i  ][2] = l[2];  q[i  ][3] = 1;
		q[2*i+1][0] = u[0]; q[i+j+2][1] = u[1]; q[i+4][2] = u[2];  q[i+4][3] = 1;
	}
}

// destructor
Quader::~Quader()
{
}

// setter
void Quader::setData(const Point& l, const Point& u)
{
	Quader buf(l,u);
	*this = buf;
}

inline void glColor3f (const Color& c                  ) { glColor3f(c.r, c.g, c.b); }
inline void glVertex2f(const Point& Q                  ) { glVertex2f(Q[0], Q[1]); }
inline void glLine2f  (const Point& Q1, const Point& Q2) { glVertex2f(Q1); glVertex2f(Q2); }

// output
void Quader::draw(const viewSystem &view, Color col) const 
{
   AffineMap mat = view.getWorldToView();

	Point Q[8];
	for (int i=0; i<8; i++) Q[i] = view.Project(mat*q[i]);	// transform and project corners

	// draw all 12 edges
   glColor3f(col);
   glBegin  (GL_LINES);
   glLine2f (Q[0],Q[1]);
   glLine2f (Q[0],Q[2]);
   glLine2f (Q[0],Q[4]);
   glLine2f (Q[1],Q[5]);
   glLine2f (Q[1],Q[3]);
   glLine2f (Q[2],Q[3]);
   glLine2f (Q[2],Q[6]);
   glLine2f (Q[3],Q[7]);
   glLine2f (Q[4],Q[5]);
   glLine2f (Q[4],Q[6]);
   glLine2f (Q[5],Q[7]);
   glLine2f (Q[6],Q[7]);
   glEnd    ();
}
