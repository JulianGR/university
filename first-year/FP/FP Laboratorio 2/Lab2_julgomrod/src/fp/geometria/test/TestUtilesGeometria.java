package fp.geometria.test;

import fp.geometria.Cuadrante;
import fp.geometria.UtilesGeometria;
import fp.geometria.tipos.Punto;
import fp.geometria.tipos.PuntoImpl;

public class TestUtilesGeometria {
	public static void main(String[] args) {
		Punto p = new PuntoImpl();
		testObtenerCuadrante(p);
		Punto p1 = new PuntoImpl(2.0, 2.0);
		testObtenerCuadrante(p1);
		Punto p2 = new PuntoImpl(-2.0, 2.0);
		testObtenerCuadrante(p2);
		Punto p3 = new PuntoImpl(-2.0, -2.0);
		testObtenerCuadrante(p3);
		Punto p4 = new PuntoImpl(2.0, -2.0);
		testObtenerCuadrante(p4);
		Punto p5 = new PuntoImpl(2.0, 0.0);
		testObtenerCuadrante(p5);
		Punto p6 = new PuntoImpl(0.0, -2.0);
		testObtenerCuadrante(p6);

	}

	private static void testObtenerCuadrante(Punto p) {
		Cuadrante c = UtilesGeometria.obtenerCuadrante(p);
		System.out.println("El punto " + p + " pertenece al " + UtilesGeometria.abreviaturaCuadrante(c));
	}
}
