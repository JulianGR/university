package fp.universidades.test;

import fp.universidades.Asignatura;
import fp.universidades.AsignaturaImpl;
import fp.universidades.TipoAsignatura;

public class TestAsignatura {

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
	}

	private static void testConstructorC1(String nombre, String codigo, Double creditos, TipoAsignatura tipo,
			Integer curso) {
		try {
			Asignatura a = new AsignaturaImpl(nombre, codigo, creditos, tipo, curso);
			mostrarAsignatura(a);
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
		case 1:	testConstructorC1("Fundamentos de Programación", "1234567", 7.5, TipoAsignatura.ANUAL, 1);	break;
		case 2:	testConstructorC1(null, "1234567", 7.5, TipoAsignatura.ANUAL, 1);	break;
		case 3:	testConstructorC1("Fundamentos de Programación", null, 7.5, TipoAsignatura.ANUAL, 1);	break;
		case 4:	testConstructorC1("Fundamentos de Programación", "1234567", null, TipoAsignatura.ANUAL, 1);	break;
		case 5:	testConstructorC1("Fundamentos de Programación", "1234567", 7.5, null, 1);	break;
		case 6:	testConstructorC1("Fundamentos de Programación", "1234567", 7.5, TipoAsignatura.ANUAL, null);	break;
		case 7:	testConstructorC1("Fundamentos de Programación", "123456", 7.5, TipoAsignatura.ANUAL, 1);	break;
		case 8:	testConstructorC1("Fundamentos de Programación", "12345678", 7.5, TipoAsignatura.ANUAL, 1);	break;
		case 9:	testConstructorC1("Fundamentos de Programación", "1234567A", 7.5, TipoAsignatura.ANUAL, 1);	break;
		case 10:testConstructorC1("Fundamentos de Programación", "1234567", (double) 0, TipoAsignatura.ANUAL, 1); break;
		default: System.out.println("Caso de prueba " + cp + "no implementado"); break;
		}
	}

	private static void mostrarAsignatura(Asignatura a) {
		System.out.println("Nombre: " + a.getNombre());
		System.out.println("Código: " + a.getCodigo());
		System.out.println("Créditos: " + a.getCreditos());
		System.out.println("Tipo: " + a.getTipo());
		System.out.println("Curso:" + a.getCurso());
	}
}
