package fp.universidades.test;

import fp.universidades.Espacio;
import fp.universidades.EspacioImpl;
import fp.universidades.TipoEspacio;

public class TestEspacio {
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

		testSetCapacidad(1);
		testSetCapacidad(2);
		testSetCapacidad(3);

		testSetNombre(1);
		testSetNombre(2);

		testSetTipo(1);
		testSetTipo(2);

		testCrearEspacio();

	}

	private static void testCrearEspacio() {
		Espacio e1 = new EspacioImpl(TipoEspacio.TEORIA, "A2.10", 200, 3);
		Espacio e2 = new EspacioImpl(TipoEspacio.TEORIA, "A2.10", 200, 3);

		System.out.println("hashcode del espacio 1: " + e1.hashCode());
		System.out.println("hashcode del espacio 2: " + e2.hashCode());

		if (e1.equals(e2)) {
			System.out.println("Espacio 1 es igual a espacio 2");
		} else {
			System.out.println("Espacio 1 NO es igual a espacio 2");
		}
		if (e1 == e2) {
			System.out.println("Espacio 1 es idéntico a espacio 2");
		}

		else {
			System.out.println("Espacio 1 NO es idéntico a espacio 2");

		}
	}

	private static void testConstructorC1(TipoEspacio tipo, String nombre, Integer capacidad, Integer planta) {
		try {
			Espacio x = new EspacioImpl(tipo, nombre, capacidad, planta);
			mostrarEspacio(x);
		} catch (IllegalArgumentException e) {
			System.out.println("**** " + e.getMessage() + "********");
		} catch (Exception e) {
			System.out.println("**** Error inesperado ****");
			System.out.println(e.getMessage());
		}
	}

	private static void testSetCapacidad(Espacio x, Integer capacidad) {
		try {
			System.out.println("Espacio antes del cambio:");
			mostrarEspacio(x);
			x.setCapacidad(capacidad);
			mostrarEspacio(x);
		} catch (IllegalArgumentException e) {
			System.out.println("**** " + e.getMessage() + "********");
		} catch (Exception e) {
			System.out.println("**** Error inesperado ****");
			System.out.println(e.getMessage());
		}
	}

	private static void testSetNombre(Espacio x, String nombre) {
		try {
			System.out.println("Espacio antes del cambio:");
			mostrarEspacio(x);
			x.setNombre(nombre);
			mostrarEspacio(x);
		} catch (IllegalArgumentException e) {
			System.out.println("**** " + e.getMessage() + "********");
		} catch (Exception e) {
			System.out.println("**** Error inesperado ****");
			System.out.println(e.getMessage());
		}
	}

	private static void testSetTipo(Espacio x, TipoEspacio tipo) {
		try {
			System.out.println("Espacio antes del cambio:");
			mostrarEspacio(x);
			x.setTipo(tipo);
			mostrarEspacio(x);
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
		case 1:
			testConstructorC1(TipoEspacio.TEORIA, "A2.10", 3, 200);
			break;
		case 2:
			testConstructorC1(null, "A2.10", 3, 200);
			break;
		case 3:
			testConstructorC1(TipoEspacio.TEORIA, null, 3, 200);
			break;
		case 4:
			testConstructorC1(TipoEspacio.TEORIA, "A2.10", null, 200);
			break;
		case 5:
			testConstructorC1(TipoEspacio.TEORIA, "A2.10", 3, null);
			break;
		case 6:
			testConstructorC1(TipoEspacio.TEORIA, "A2.10", 3, 0);
			break;
		default:
			System.out.println("Caso de prueba " + cp + "no implementado");
			break;
		}
	}

	private static void testSetCapacidad(Integer p) {
		System.out.println("===========SetCapacidad: Caso de prueba" + p + "=============");
		Espacio x = new EspacioImpl(TipoEspacio.TEORIA, "A2.10", 3, 200);
		switch (p) {
		case 1:
			testSetCapacidad(x, 2000);
			break;
		case 2:
			testSetCapacidad(x, 150);
			break;
		case 3:
			testSetCapacidad(x, null);
			break;
		default:
			System.out.println("Caso de prueba " + p + "no implementado");
			break;
		}

	}

	private static void testSetNombre(Integer g) {
		System.out.println("===SetNombre: Caso de prueba" + g + "==========");
		Espacio x = new EspacioImpl(TipoEspacio.TEORIA, "A2.10", 3, 200);
		switch (g) {
		case 1:
			testSetNombre(x, "A.3.11");
			break;
		case 2:
			testSetNombre(x, null);
			break;
		default:
			System.out.println("Caso de prueba " + g + "no implementado");
			break;
		}
	}

	private static void testSetTipo(Integer p) {
		System.out.println("===========SetTipo: Caso de prueba" + p + "=============");
		Espacio x = new EspacioImpl(TipoEspacio.TEORIA, "A2.10", 3, 200);
		switch (p) {
		case 1:
			testSetTipo(x, TipoEspacio.EXAMEN);
			break;
		case 2:
			testSetTipo(x, null);
			break;
		default:
			System.out.println("Caso de prueba " + p + "no implementado");
			break;
		}

	}

	private static void mostrarEspacio(Espacio x) {
		System.out.println("Tipo: " + x.getTipo());
		System.out.println("Nombre: " + x.getNombre());
		System.out.println("Capacidad: " + x.getCapacidad());
		System.out.println("Planta: " + x.getPlanta());

	}
}
