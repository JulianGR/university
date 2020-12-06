package fp.geometria.test;

import java.util.Arrays;

import fp.geometria.Cuadrante;
import fp.geometria.NubePuntos;
import fp.geometria.NubePuntosImpl2;
import fp.geometria.PuntoImpl;

public class TestNubePuntos {

	public static void main(String[] args) {
		NubePuntos nube = creaNubePuntos();
		muestraEstadisticas(nube);

	}

	private static void muestraEstadisticas(NubePuntos nube) {
		System.out.println("Media de coordenadas X: " + nube.calculaMediaX());
		System.out.println("Media de coordenadas Y: " + nube.calculaMediaY());
		System.out.println("Número de puntos con X mayor a 3.0: " + nube.cuentaPuntosConXMayorA(3.0));
		System.out.println("Número de puntos por cuadrante: " + Arrays.toString(nube.cuentaPuntosPorCuadrante()));
		System.out.println("¿Están todos los puntos a una distancia menor de 15.0 del origen?: "
				+ nube.estanTodosADistanciaMenorQue(15.0));
		System.out.println("Índice del primer punto que está en el segundo cuadrante: "
				+ nube.getIndicePunto(Cuadrante.SEGUNDO_CUADRANTE));
		System.out.println("Punto más cercano a (5.0,5.0): " + nube.getPuntoMasCercano(new PuntoImpl(5.0, 5.0)));
		System.out.println("Punto más distante al origen: " + nube.getPuntoMasDistante());
		System.out.println("Puntos en la bisectriz: " + nube.getPuntosBisectriz());
		System.out.println("¿Hay algún punto en el eje X?: " + nube.hayAlgunPuntoEnEjeX());
		System.out.println("Producto de la suma de coordenadas X e Y de cada punto: "
				+ nube.multiplicaSumaCoordenadasXeY(Cuadrante.PRIMER_CUADRANTE));
		System.out.println("Puntos con una distancia menor a 5.0 del origen: " + nube.seleccionaPuntos(5.0));
		System.out.println(
				"Suma de coordenadas X de puntos del primer cuadrante: " + nube.sumaCoordenadasXPrimerCuadrante());
	}

	private static NubePuntos creaNubePuntos() {
		NubePuntos res = new NubePuntosImpl2();
		res.incorporaPunto(new PuntoImpl(-5.3, 3.0));
		res.incorporaPunto(new PuntoImpl(-5.3, 1.0));
		res.incorporaPunto(new PuntoImpl(-3.2, 0.0));
		res.incorporaPunto(new PuntoImpl(-3.1, -9.6));
		res.incorporaPunto(new PuntoImpl(-2.8, -4.3));
		res.incorporaPunto(new PuntoImpl(-2.0, -1.1));
		res.incorporaPunto(new PuntoImpl(-0.9, 0.0));
		res.incorporaPunto(new PuntoImpl(0.0, 0.0));
		res.incorporaPunto(new PuntoImpl(0.0, 17.2));
		res.incorporaPunto(new PuntoImpl(1.4, -6.3));
		res.incorporaPunto(new PuntoImpl(1.8, 13.1));
		res.incorporaPunto(new PuntoImpl(1.8, 10.9));
		res.incorporaPunto(new PuntoImpl(2.3, 2.3));
		res.incorporaPunto(new PuntoImpl(2.4, 2.4));
		res.incorporaPunto(new PuntoImpl(8.9, 8.3));
		res.incorporaPunto(new PuntoImpl(10.0, 10.0));
		return res;
	}

}
