package fp.universidades.test;

import fp.universidades.Asignatura;
import fp.universidades.AsignaturaImpl;
import fp.universidades.Convocatoria;
import fp.universidades.Nota;
import fp.universidades.NotaImpl;
import fp.universidades.TipoAsignatura;

public class TestNota {

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

		testCrearNota();
	}

	private static final String CODIGO = "Fundamentos de Programación";
	private static final String NOMBRE = "1234567";
	private static final Double CREDITOS = 7.5;
	private static final TipoAsignatura TIPO = TipoAsignatura.ANUAL;
	private static final Integer CURSO = 1;
	static Asignatura a = new AsignaturaImpl(NOMBRE, CODIGO, CREDITOS, TIPO, CURSO);

	private static void testCrearNota() {
		Nota nt1 = new NotaImpl(a, 1, Convocatoria.PRIMERA, 7.5, false);
		Nota nt2 = new NotaImpl(a, 1, Convocatoria.PRIMERA, 7.5, false);

		System.out.println("hashcode de la nota 1: " + nt1.hashCode());
		System.out.println("hashcode de la nota 2: " + nt2.hashCode());

		if (nt1.equals(nt2)) {
			System.out.println("Nota 1 es igual a nota 2");
		} else {
			System.out.println("Nota 1 NO es igual a nota 2");
		}
		if (nt1 == nt2) {
			System.out.println("Nota 1 es idéntica a nota 2");
		}

		else {
			System.out.println("Nota 1 NO es idéntica a nota 2");

		}
	}

	private static void testConstructorC1(Asignatura asignatura, Integer curso, Convocatoria convocatoria, Double valor,
			Boolean mencionHonor) {
		try {
			Nota n = new NotaImpl(asignatura, curso, convocatoria, valor, mencionHonor);
			mostrarNota(n);
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
			testConstructorC1(a, 1, Convocatoria.PRIMERA, 7.5, false);
			break;
		case 2:
			testConstructorC1(null, 1, Convocatoria.PRIMERA, 7.5, false);
			break;
		case 3:
			testConstructorC1(a, null, Convocatoria.PRIMERA, 7.5, false);
			break;
		case 4:
			testConstructorC1(a, 1, null, 7.5, false);
			break;
		case 5:
			testConstructorC1(a, 1, Convocatoria.PRIMERA, null, false);
			break;
		case 6:
			testConstructorC1(a, 1, Convocatoria.PRIMERA, 7.5, null);
			break;
		case 7:
			testConstructorC1(a, 1, Convocatoria.PRIMERA, 7.5, true);
			break;
		case 8:
			testConstructorC1(a, 1, Convocatoria.PRIMERA, -1.0, false);
			break;
		case 9:
			testConstructorC1(a, 1, Convocatoria.PRIMERA, 10.1, false);
			break;
		default:
			System.out.println("Caso de prueba " + cp + "no implementado");
			break;
		}
	}

	private static void mostrarNota(Nota n) {
		System.out.println("Asignatura: " + n.getAsignatura());
		System.out.println("Curso: " + n.getCursoAcademico());
		System.out.println("Convocatoria: " + n.getConvocatoria());
		System.out.println("Valor: " + n.getValor());
		System.out.println("¿Tiene mención de honor?:" + n.getMencionHonor());
	}
}
