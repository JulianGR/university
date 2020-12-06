package fp.geometria;

import fp.geometria.tipos.Punto;

public class UtilesGeometria {
	public static Cuadrante obtenerCuadrante(Punto p) {
		Cuadrante res;
		if (p.getX() > 0.0 && p.getY() > 0.0) {
			res = Cuadrante.PRIMER_CUADRANTE;
		} else if (p.getX() < 0.0 && p.getY() > 0.0) {
			res = Cuadrante.SEGUNDO_CUADRANTE;
		} else if (p.getX() < 0.0 && p.getY() < 0.0) {
			res = Cuadrante.TERCER_CUADRANTE;
		} else if (p.getX() > 0.0 && p.getY() < 0.0) {
			res = Cuadrante.CUARTO_CUADRANTE;
		} else {
			res = Cuadrante.EJE;
		}
		return res;
	}

	public static String abreviaturaCuadrante(Cuadrante c) {
		String res = null;
		switch (c) {
		case PRIMER_CUADRANTE:
			res = "1er Cradrante";
			break;
		case SEGUNDO_CUADRANTE:
			res = "2º Cradrante";
			break;
		case TERCER_CUADRANTE:
			res = "3er Cradrante";
			break;
		case CUARTO_CUADRANTE:
			res = "4º Cradrante";
			break;
		case EJE:
			res = "eje";
			break;

		}
		return res;
	}
}
