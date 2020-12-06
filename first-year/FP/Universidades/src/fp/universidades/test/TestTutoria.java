package fp.universidades.test;

import java.time.DayOfWeek;
import java.time.LocalTime;

import fp.universidades.Tutoria;
import fp.universidades.TutoriaImpl;

public class TestTutoria {
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

		testCrearTutoria();
	}

	private static void testCrearTutoria() {
		Tutoria t1 = new TutoriaImpl(DayOfWeek.TUESDAY, LocalTime.of(9, 20), LocalTime.of(10, 0));
		Tutoria t2 = new TutoriaImpl(DayOfWeek.TUESDAY, LocalTime.of(9, 20), LocalTime.of(10, 0));

		System.out.println("hashcode de la tutoría 1: " + t1.hashCode());
		System.out.println("hashcode de la tutoría2: " + t2.hashCode());

		if (t1.equals(t2)) {
			System.out.println("Tutoría 1 es igual a tutoría 2");
		} else {
			System.out.println("Tutoría 1 NO es igual a tutoría 2");
		}
		if (t1 == t2) {
			System.out.println("Tutoría 1 es idéntico a tutoría 2");
		}

		else {
			System.out.println("Tutoría 1 NO es idéntico a tutoría 2");

		}
	}

	private static void testConstructorC1(DayOfWeek dia, LocalTime horaInicio, LocalTime horaFin) {
		try {
			Tutoria t = new TutoriaImpl(dia, horaInicio, horaFin);
			mostrarTutoria(t);
		} catch (IllegalArgumentException e) {
			System.out.println("**** " + e.getMessage() + "********");
		} catch (Exception e) {
			System.out.println("**** Error inesperado ****");
			System.out.println(e.getMessage());
		}
	}

	private static void testConstructorC1(Integer cp) {

		System.out.println("========== CostructorC1:Caso de prueba" + cp + "==============");
		switch (cp) {
		case 1:
			testConstructorC1(DayOfWeek.MONDAY, LocalTime.of(8, 40), LocalTime.of(9, 30));
			break;
		case 2:
			testConstructorC1(null, LocalTime.of(8, 40), LocalTime.of(9, 30));
			break;
		case 3:
			testConstructorC1(DayOfWeek.MONDAY, null, LocalTime.of(9, 30));
			break;
		case 4:
			testConstructorC1(DayOfWeek.MONDAY, LocalTime.of(8, 40), null);
			break;
		case 5:
			testConstructorC1(DayOfWeek.SATURDAY, LocalTime.of(8, 40), LocalTime.of(9, 30));
			break;
		case 6:
			testConstructorC1(DayOfWeek.SUNDAY, LocalTime.of(8, 40), LocalTime.of(9, 30));
			break;
		case 7:
			testConstructorC1(DayOfWeek.MONDAY, LocalTime.of(8, 20), LocalTime.of(9, 30));
			break;
		case 8:
			testConstructorC1(DayOfWeek.MONDAY, LocalTime.of(8, 40), LocalTime.of(21, 40));
			break;
		case 9:
			testConstructorC1(DayOfWeek.MONDAY, LocalTime.of(8, 40), LocalTime.of(8, 30));
			break;
		default:
			System.out.println("Caso de prueba " + cp + "no implementado");
			break;
		}
	}

	private static void mostrarTutoria(Tutoria t) {
		System.out.println("Día: " + t.getDiaSemana());
		System.out.println("Duración: " + t.getDuracion());
		System.out.println("Hora de inicio: " + t.getHoraComienzo());
		System.out.println("Hora de fin: " + t.getHoraFin());
	}
}
