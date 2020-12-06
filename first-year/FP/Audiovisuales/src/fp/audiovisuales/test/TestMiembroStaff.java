package fp.audiovisuales.test;

import java.time.LocalDate;
import java.time.Month;

import fp.audiovisuales.MiembroStaff;
import fp.audiovisuales.MiembroStaffImpl;

public class TestMiembroStaff {
	public static void main(String[] args) {
		testConstructorC1(1);
		testConstructorC1(2);
		testConstructorC1(3);
		testConstructorC1(4);

		testConstructorC2(1);
		testConstructorC2(2);
		testConstructorC2(3);

		testConstructorC3(1);
		testConstructorC3(2);
		testConstructorC3(3);

		testSetFechaDefuncion(1);
		testSetFechaDefuncion(2);

		testSetFechaNacimiento(1);
		testSetFechaNacimiento(2);

		testCrearMiembro();
	}

	private static void testCrearMiembro() {
		MiembroStaff m1 = new MiembroStaffImpl(1234, "Charles Chaplin", LocalDate.of(1889, Month.APRIL, 16),
				LocalDate.of(1977, Month.DECEMBER, 25), "Walworth, London, England, UK", "Charlot");

		MiembroStaff m2 = new MiembroStaffImpl(1234, "Charles Chaplin", LocalDate.of(1889, Month.APRIL, 16),
				LocalDate.of(1977, Month.DECEMBER, 25), "Walworth, London, England, UK", "Charlot");

		System.out.println("hashcode del miembro 1: " + m1.hashCode());
		System.out.println("hashcode del miembro 2: " + m2.hashCode());

		if (m1.equals(m2)) {
			System.out.println("Miembro 1 es igual a miembro 2");
		} else {
			System.out.println("Miembro 1 no es igual a miembro 2");
		}
		if (m1 == m2) {
			System.out.println("Miembro 1 es idéntico a miembro2");
		}

		else {
			System.out.println("Miembro 1 NO es idéntico a miembro 2");

		}
	}

	private static void testConstructorC1(Integer id, String nombre, LocalDate nacimiento, LocalDate defuncion,
			String lugar, String alias) {
		try {
			MiembroStaff m = new MiembroStaffImpl(id, nombre, nacimiento, defuncion, lugar, alias);
			mostrarMiembro(m);
		} catch (IllegalArgumentException e) {
			System.out.println("****** Illegal argument exception ******");
			System.out.println(e.getMessage());
		} catch (Exception e) {
			System.out.println("****** Error inesperado ******");
			System.out.println(e.getMessage());
		}
		System.out.println();
	}

	private static void testConstructorC2(Integer id, String nombre, LocalDate nacimiento, String lugar, String alias) {
		try {
			MiembroStaff m = new MiembroStaffImpl(id, nombre, nacimiento, lugar, alias);
			mostrarMiembro(m);
		} catch (IllegalArgumentException e) {
			System.out.println("****** Illegal argument exception ******");
			System.out.println(e.getMessage());
		} catch (Exception e) {
			System.out.println("****** Error inesperado ******");
			System.out.println(e.getMessage());
		}
		System.out.println();
	}

	private static void testConstructorC3(Integer id, String nombre) {
		try {
			MiembroStaff m = new MiembroStaffImpl(id, nombre);
			mostrarMiembro(m);
		} catch (IllegalArgumentException e) {
			System.out.println("******Illegal argument exception ******");
			System.out.println(e.getMessage());
		} catch (Exception e) {
			System.out.println("******Error inesperado ******");
			System.out.println(e.getMessage());
		}
		System.out.println();
	}

	private static void testConstructorC1(Integer n) {
		System.out.println("=========== Caso de prueba del constructor1 " + n + "===========");
		switch (n) {
		case 1:
			testConstructorC1(1234, "Charles Chaplin", LocalDate.of(1889, Month.APRIL, 16),
					LocalDate.of(1977, Month.DECEMBER, 25), "Walworth, London, England, UK", "Charlot");
			break;
		case 2:
			testConstructorC1(null, "Charles Chaplin", LocalDate.of(1889, Month.APRIL, 16),
					LocalDate.of(1977, Month.DECEMBER, 25), "Walworth, London, England, UK", "Charlot");
			break;
		case 3:
			testConstructorC1(1234, null, LocalDate.of(1889, Month.APRIL, 16), LocalDate.of(1977, Month.DECEMBER, 25),
					"Walworth, London, England, UK", "Charlot");
			break;
		case 4:
			testConstructorC1(1234, "Charles Chaplin", LocalDate.of(1989, Month.APRIL, 16),
					LocalDate.of(1977, Month.DECEMBER, 25), "Walworth, London, England, UK", "Charlot");
			break;
		default:
			System.out.println("Caso de prueba " + n + " no implementado");
		}
	}

	private static void testConstructorC2(Integer n) {
		System.out.println("=========== Caso de prueba del constructor2 " + n + " ===========");
		switch (n) {
		case 1:
			testConstructorC2(1234, "Charles Chaplin", LocalDate.of(1889, Month.APRIL, 16),
					"Walworth, London, England, UK", "Charlot");
			break;
		case 2:
			testConstructorC2(null, "Charles Chaplin", LocalDate.of(1889, Month.APRIL, 16),
					"Walworth, London, England, UK", "Charlot");
			break;
		case 3:
			testConstructorC2(1234, null, LocalDate.of(1889, Month.APRIL, 16), "Walworth, London, England, UK",
					"Charlot");
			break;
		default:
			System.out.println("Caso de prueba " + n + " no implementado");
		}
	}

	private static void testConstructorC3(Integer n) {
		System.out.println("=========== Caso de prueba del constructor3 " + n + " ===========");
		switch (n) {
		case 1:
			testConstructorC3(1234, "Charles Chaplin");
			break;
		case 2:
			testConstructorC3(null, "Charles Chaplin");
			break;
		case 3:
			testConstructorC3(1234, null);
			break;
		default:
			System.out.println("Caso de prueba " + n + " no implementado");
		}
	}

	private static void testSetFechaDefuncion(MiembroStaff m, LocalDate l) {
		try {
			System.out.println("Fecha antes del cambio: ");
			mostrarMiembro(m);
			System.out.println("Fecha despues del cambio: ");
			m.setFechaDefuncion(l);
			mostrarMiembro(m);
		} catch (IllegalArgumentException e) {
			System.out.println("****** Illegal argument exception ******");
			System.out.println(e.getMessage());
		} catch (Exception e) {
			System.out.println("****** Error inesperado ******");
			System.out.println(e.getMessage());
		}
		System.out.println();

	}

	private static void testSetFechaDefuncion(Integer cp) {
		System.out.println("=========== Set fecha defuncion caso " + cp + "===========");
		MiembroStaff m = new MiembroStaffImpl(1234, "Charles Chaplin", LocalDate.of(1889, Month.APRIL, 16),
				LocalDate.of(1977, Month.DECEMBER, 25), "Walworth, London, England, UK", "Charlot");
		switch (cp) {
		case 1:
			testSetFechaDefuncion(m, LocalDate.of(1989, Month.APRIL, 17));
			break;
		case 2:
			testSetFechaDefuncion(m, LocalDate.of(1888, Month.APRIL, 16));
			break;
		default:
			System.out.println("Caso de prueba " + cp + " no implementado");
		}
	}

	private static void testSetFechaNacimiento(MiembroStaff m, LocalDate l) {
		try {
			System.out.println("Fecha antes del cambio: ");
			mostrarMiembro(m);
			System.out.println("Fecha despues del cambio: ");
			m.setFechaNacimiento(l);
			mostrarMiembro(m);
		} catch (IllegalArgumentException e) {
			System.out.println("****** Illegal argument exception******");
			System.out.println(e.getMessage());
		} catch (Exception e) {
			System.out.println("****** Error inesperado******");
			System.out.println(e.getMessage());
		}
		System.out.println();

	}

	private static void testSetFechaNacimiento(Integer cp) {
		System.out.println("=========== Set fecha nacimiento caso " + cp + "===========");
		MiembroStaff m = new MiembroStaffImpl(1234, "Charles Chaplin", LocalDate.of(1889, Month.APRIL, 16),
				LocalDate.of(1977, Month.DECEMBER, 25), "Walworth, London, England, UK", "Charlot");
		switch (cp) {
		case 1:
			testSetFechaNacimiento(m, LocalDate.of(1889, Month.APRIL, 17));
			break;
		case 2:
			testSetFechaNacimiento(m, LocalDate.of(1989, Month.APRIL, 16));
			break;
		default:
			System.out.println("Caso de prueba " + cp + " no implementado");
		}
	}

	private static void mostrarMiembro(MiembroStaff m) {
		System.out.println("Nombre: " + m.getNombre());
		System.out.println("Alias: " + m.getAlias());
		System.out.println("Id: " + m.getId());
		System.out.println("Fecha nacimiento: " + m.getFechaNacimiento());
		System.out.println("Fecha defuncion: " + m.getFechaDefuncion());
		System.out.println("Lugar nacimiento: " + m.getLugarNacimiento());
		System.out.println("Edad: " + m.getEdad());

	}

}
