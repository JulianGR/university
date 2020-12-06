package fp.audiovisuales.test;

import java.time.Duration;
import java.time.LocalDate;
import java.time.Month;

import fp.audiovisuales.Pelicula;
import fp.audiovisuales.PeliculaImpl;

public class TestPelicula {
	public static void main(String[] args) {

		testConstructorC1(1);
		testConstructorC1(2);
		testConstructorC1(3);
		testConstructorC1(4);
		testConstructorC1(5);

		testConstructorC2(1);
		testConstructorC2(2);
		testConstructorC2(3);

		testSetDuracion(1);
		testSetDuracion(2);
		testSetDuracion(3);

		testCrearPelicula();
	}

	private static void testCrearPelicula() {

		Pelicula p1 = new PeliculaImpl(1222, "Tiempos modernos", "Modern times", "Inglés",
				LocalDate.of(1936, Month.FEBRUARY, 5), Duration.ofMinutes(87), "Drama", "United Artist", "USA");

		Pelicula p2 = new PeliculaImpl(1222, "Tiempos modernos", "Modern times", "Inglés",
				LocalDate.of(1936, Month.FEBRUARY, 5), Duration.ofMinutes(87), "Drama", "United Artist", "USA");

		System.out.println("hashcode de la película 1: " + p1.hashCode());
		System.out.println("hashcode de la película 2: " + p2.hashCode());

		if (p1.equals(p2)) {
			System.out.println("Película 1 es igual a película 2");
		} else {
			System.out.println("Película 1 NO es igual a película 2");
		}
		if (p1 == p2) {
			System.out.println("Película 1 es idéntica a película 2");
		}

		else {
			System.out.println("Película 1 NO es idéntica a película 2");

		}
	}

	private static void testConstructorC1(Integer id, String titulo, String tituloOriginal, String idiomaOriginal,
			LocalDate fechaEstreno, Duration duracion, String genero, String productora, String pais) {
		try {
			Pelicula p = new PeliculaImpl(id, titulo, tituloOriginal, idiomaOriginal, fechaEstreno, duracion, genero,
					productora, pais);
			mostrarPelicula(p);
		} catch (IllegalArgumentException e) {
			System.out.println("**** " + e.getMessage() + "********");
		} catch (Exception e) {
			System.out.println("**** Error inesperado ****");
			System.out.println(e.getMessage());
		}
	}

	private static void testConstructorC2(Integer id, String titulo) {
		try {
			Pelicula p = new PeliculaImpl(id, titulo);
			mostrarPelicula(p);
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
			testConstructorC1(1222, "Tiempos modernos", "Modern times", "Inglés", LocalDate.of(1936, Month.FEBRUARY, 5),
					Duration.ofMinutes(87), "Drama", "United Artist", "USA");
			break;
		case 2:
			testConstructorC1(null, "Tiempos modernos", "Modern times", "Inglés", LocalDate.of(1936, Month.FEBRUARY, 5),
					Duration.ofMinutes(87), "Drama", "United Artist", "USA");
			break;
		case 3:
			testConstructorC1(1222, null, "Modern times", "Inglés", LocalDate.of(1936, Month.FEBRUARY, 5),
					Duration.ofMinutes(87), "Drama", "United Artist", "USA");
			break;
		case 4:
			testConstructorC1(1222, "Tiempos modernos", "Modern times", "Inglés", LocalDate.of(1936, Month.FEBRUARY, 5),
					null, "Drama", "United Artist", "USA");
			break;
		case 5:
			testConstructorC1(1222, "Tiempos modernos", "Modern times", "Inglés", LocalDate.of(1936, Month.FEBRUARY, 5),
					Duration.ofMinutes(0), "Drama", "United Artist", "USA");
			break;

		default:
			System.out.println("Caso de prueba " + cp + "no implementado");
			break;
		}
	}

	private static void testConstructorC2(Integer cpp) {

		System.out.println("========== CostructorC2:Caso de prueba" + cpp + "==============");
		switch (cpp) {
		case 1:
			testConstructorC2(1222, "Tiempos modernos");
			break;
		case 2:
			testConstructorC2(null, "Tiempos modernos");
			break;
		case 3:
			testConstructorC2(1222, null);
			break;

		default:
			System.out.println("Caso de prueba " + cpp + "no implementado");
			break;
		}
	}

	private static void testSetDuracion(Pelicula p, Duration duracion) {
		try {
			System.out.println("Película antes del cambio:");
			mostrarPelicula(p);
			p.setDuracion(duracion);
			mostrarPelicula(p);
		} catch (IllegalArgumentException e) {
			System.out.println("**** " + e.getMessage() + "********");
		} catch (Exception e) {
			System.out.println("**** Error inesperado ****");
			System.out.println(e.getMessage());
		}
	}

	private static void testSetDuracion(Integer pp) {
		System.out.println("===========SetCuantiaTotal: Caso de prueba" + pp + "=============");
		Pelicula p = new PeliculaImpl(1222, "Tiempos modernos", "Modern times", "Inglés",
				LocalDate.of(1936, Month.FEBRUARY, 5), Duration.ofMinutes(87), "Drama", "United Artist", "USA");
		switch (pp) {
		case 1:
			testSetDuracion(p, Duration.ofMinutes(87));
			break;
		case 2:
			testSetDuracion(p, Duration.ofMinutes(0));
			break;
		case 3:
			testSetDuracion(p, Duration.ofMinutes(-1));
			break;
		default:
			System.out.println("Caso de prueba " + pp + "no implementado");
			break;
		}

	}

	private static void mostrarPelicula(Pelicula p) {
		System.out.println("ID: " + p.getId());
		System.out.println("Título" + p.getTitulo());
		System.out.println("Título original" + p.getTituloOriginal());
		System.out.println("Idioma original" + p.getIdiomaOriginal());
		System.out.println("Fecha de estreno" + p.getFechaEstreno());
		System.out.println("Duración" + p.getDuracion());
		System.out.println("Tipo de metraje" + p.getTipoDeMetraje());
		System.out.println("Género" + p.getGenero());
		System.out.println("Productora" + p.getProductora());
		System.out.println("País" + p.getPais());
	}

}
