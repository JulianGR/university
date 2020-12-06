package fp.universidades.test;

import fp.universidades.Beca;
import fp.universidades.BecaImpl;
import fp.universidades.TipoBeca;

public class TestBeca {

	public static void main(String[] args) {
		testConstructorC1(1);
		testConstructorC1(2);
		testConstructorC1(3);
		testConstructorC1(4);
		testConstructorC1(5);
		testConstructorC1(6);
		testConstructorC1(7);
		testConstructorC1(8);
		testConstructorC1(9);
		testConstructorC1(10);

		testSetCuantiaTotal(1);
		testSetCuantiaTotal(2);

		testSetDuracion(1);
		testSetDuracion(2);

	}

	private static void testConstructorC1(String codigo, Double cuantiaTotal, Integer duracion, TipoBeca tipo) {
		try {
			Beca b = new BecaImpl(codigo, cuantiaTotal, duracion, tipo);
			mostrarBeca(b);
		} catch (IllegalArgumentException e) {
			System.out.println("**** " + e.getMessage() + "********");
		} catch (Exception e) {
			System.out.println("**** Error inesperado ****");
			System.out.println(e.getMessage());
		}
	}

	private static void testSetCuantiaTotal(Beca b, Double cuantiaTotal) {
		try {
			System.out.println("Beca antes del cambio:");
			mostrarBeca(b);
			b.setCuantiaTotal(cuantiaTotal);
			mostrarBeca(b);
		} catch (IllegalArgumentException e) {
			System.out.println("**** " + e.getMessage() + "********");
		} catch (Exception e) {
			System.out.println("**** Error inesperado ****");
			System.out.println(e.getMessage());
		}
	}

	private static void testSetDuracion(Beca b, Integer duracion) {
		try {
			System.out.println("Beca antes del cambio:");
			mostrarBeca(b);
			b.setDuracion(duracion);
			mostrarBeca(b);
		} catch (IllegalArgumentException e) {
			System.out.println("**** " + e.getMessage() + "********");
		} catch (Exception e) {
			System.out.println("****** Error inesperado ****");
			System.out.println(e.getMessage());
		}
	}

	private static void testConstructorC1(Integer cp) {

		System.out.println("========== CostructorC1:Caso de prueba" + cp + "==============");
		switch (cp) {
		case 1:	testConstructorC1("ABB2024", (double) 1500, 1, TipoBeca.MOVILIDAD);	break;
		case 2:	testConstructorC1(null, (double) 1500, 1, TipoBeca.MOVILIDAD);	break;
		case 3:	testConstructorC1("ABB2024", null, 1, TipoBeca.MOVILIDAD);	break;
		case 4:	testConstructorC1("ABB2024", (double) 1500, null, TipoBeca.MOVILIDAD);	break;
		case 5:	testConstructorC1("ABB2024", (double) 1500, 1, null);	break;
		case 6:	testConstructorC1("ABB2024", (double) 1000, 1, TipoBeca.MOVILIDAD);	break;
		case 7:	testConstructorC1("ABB2024", (double) 1500, 0, TipoBeca.MOVILIDAD);	break;
		case 8:	testConstructorC1("2BB2024", (double) 1500, 1, TipoBeca.MOVILIDAD);	break;		
		case 9:	testConstructorC1("ABB202A", (double) 1500, 1, TipoBeca.MOVILIDAD);	break;
		case 10:	testConstructorC1("ABB20244", (double) 1500, 1, TipoBeca.MOVILIDAD);	break;
		default:System.out.println("Caso de prueba " + cp + "no implementado");	break;
		}
	}

	private static void testSetCuantiaTotal(Integer p) {
		System.out.println("===========SetCuantiaTotal: Caso de prueba" + p + "=============");
		Beca b = new BecaImpl("ABB2024", (double) 1500, 1, TipoBeca.MOVILIDAD);
		switch (p) {
		case 1:	testSetCuantiaTotal(b, (double) 2000);	break;
		case 2:	testSetCuantiaTotal(b, (double) 1000);	break;
		default:System.out.println("Caso de prueba " + p + "no implementado");	break;
		}

	}

	private static void testSetDuracion(Integer g) {
		System.out.println("===SetDuracion: Caso de prueba" + g + "==========");
		Beca b = new BecaImpl("ABB2024", (double) 1500, 1, TipoBeca.MOVILIDAD);
		switch (g) {
		case 1:	testSetDuracion(b, 2);	break;
		case 2:	testSetDuracion(b, 0);	break;
		default:System.out.println("Caso de prueba " + g + "no implementado");	break;
		}
	}

	private static void mostrarBeca(Beca b) {
		System.out.println("Código: " + b.getCodigo());
		System.out.println("Tipo: " + b.getTipo());
		System.out.println("Cuantía Mensual: " + b.getCuantiaMensual());
		System.out.println("Cuantía total: " + b.getCuantiaTotal());
		System.out.println("Duración:" + b.getDuracion());
	}
}
