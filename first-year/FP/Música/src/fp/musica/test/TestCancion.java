package fp.musica.test;

import java.time.Duration;
import java.time.temporal.ChronoUnit;

import fp.musica.Artista;
import fp.musica.ArtistaImpl;
import fp.musica.Cancion;
import fp.musica.CancionImpl;

public class TestCancion {

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
		testConstructorC1(11);
		testConstructorC1(12);
		testConstructorC1(13);
		testConstructorC1(14);
		testConstructorC1(15);

		testSetPopularidad(1);
		testSetPopularidad(2);
		testSetPopularidad(3);
		testSetPopularidad(4);
		testSetPopularidad(5);
		testSetPopularidad(6);
		
		testCrearCancion();
	}

	private static final String ID = "1234567890abcdefABCDEF";
	private static final String NOMBRE = "Dire Straits";
	private static final String GENERO = "Rock";
	private static final Integer POPULARIDAD = 60;
	private static final String URLIMAGEN = "http://miUrl.com/foto.jpg";

	static Artista a = new ArtistaImpl(ID, NOMBRE, GENERO, POPULARIDAD, URLIMAGEN);

	private static void testConstructorC1(String id, Artista artista, Duration duration, String nombre,
			Integer numeroPista, Integer popularidad) {
		try {
			Cancion c = new CancionImpl(id, artista, duration, nombre, numeroPista, popularidad);
			mostrarCancion(c);
		} catch (IllegalArgumentException e) {
			System.out.println("**** " + e.getMessage() + "********");
		} catch (Exception e) {
			System.out.println("**** Error inesperado ****");
			System.out.println(e.getMessage());
		}
	}
	
	private static void testCrearCancion() {
		Cancion c1 = new CancionImpl("1234567890abcdefABCDEF", a, Duration.of(647, ChronoUnit.SECONDS), "Sultans of Swing", 6,60);
		Cancion c2 = new CancionImpl("1234567890abcdefABCDEF", a, Duration.of(647, ChronoUnit.SECONDS), "Sultans of Swing", 6,60);

		System.out.println("hashcode de la canción 1: " + c1.hashCode());
		System.out.println("hashcode de la canción 2: " + c2.hashCode());

		if (c1.equals(c2)) {
			System.out.println("Canción 1 es igual a canción 2");
		} else {
			System.out.println("Canción 1 NO es igual a canción 2");
		}
		if (c1 == c2) {
			System.out.println("Canción 1 es idéntico a canción 2");
		}

		else {
			System.out.println("Canción 1 NO es idéntico a canción 2");

		}
	}

	private static void testSetPopularidad(Cancion c, Integer popularidad) {
		try {
			System.out.println("Canción antes del cambio:");
			mostrarCancion(c);
			c.setPopularidad(popularidad);
			mostrarCancion(c);
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
		case 1:testConstructorC1("1234567890abcdefABCDEF", a, Duration.of(647, ChronoUnit.SECONDS), "Sultans of Swing", 6,60); break;
		case 2:testConstructorC1(null, a, Duration.of(647, ChronoUnit.SECONDS), "Sultans of Swing", 6,60); break;
		case 3:testConstructorC1("1234567890abcdefABCDEF", null, Duration.of(647, ChronoUnit.SECONDS), "Sultans of Swing", 6,60); break;
		case 4:testConstructorC1("1234567890abcdefABCDEF", a, null, "Sultans of Swing", 6,60); break;
		case 5:testConstructorC1("1234567890abcdefABCDEF", a, Duration.of(647, ChronoUnit.SECONDS), null, 6,60); break;
		case 6:testConstructorC1("1234567890abcdefABCDEF", a, Duration.of(647, ChronoUnit.SECONDS), "Sultans of Swing", null,60); break;
		case 7:testConstructorC1("1234567890abcdefABCDEF", a, Duration.of(647, ChronoUnit.SECONDS), "Sultans of Swing", 6,null); break;
		case 8:testConstructorC1("1234567890abcdefABCDEFX", a, Duration.of(647, ChronoUnit.SECONDS), "Sultans of Swing", 6,60); break;
		case 9:testConstructorC1("1234567890abcdefABCDE", a, Duration.of(647, ChronoUnit.SECONDS), "Sultans of Swing", 6,60); break;
		case 10:testConstructorC1("1234567890abcdefABCDEF", a, Duration.of(0, ChronoUnit.SECONDS), "Sultans of Swing", 6,60); break;
		case 11:testConstructorC1("1234567890abcdefABCDEF", a, Duration.of(-1, ChronoUnit.SECONDS), "Sultans of Swing", 6,60); break;
		case 12:testConstructorC1("1234567890abcdefABCDEF", a, Duration.of(647, ChronoUnit.SECONDS), "Sultans of Swing", 0,60); break;
		case 13:testConstructorC1("1234567890abcdefABCDEF", a, Duration.of(647, ChronoUnit.SECONDS), "Sultans of Swing", -1,60); break;
		case 14:testConstructorC1("1234567890abcdefABCDEF", a, Duration.of(647, ChronoUnit.SECONDS), "Sultans of Swing", 6,-1); break;
		case 15:testConstructorC1("1234567890abcdefABCDEF", a, Duration.of(647, ChronoUnit.SECONDS), "Sultans of Swing", 6,101); break;	
		default:System.out.println("Caso de prueba " + cp + "no implementado");	break;
		}
	}

	private static void testSetPopularidad(Integer p) {
		System.out.println("===========SetPopularidad: Caso de prueba" + p + "=============");
		Cancion c= new CancionImpl("1234567890abcdefABCDEF", a, Duration.of(647, ChronoUnit.SECONDS), "Sultans of Swing", 6,60);
		switch (p) {
		case 1:
			testSetPopularidad(c, 78);
			break;
		case 2:
			testSetPopularidad(c, 0);
			break;
		case 3:
			testSetPopularidad(c, 100);
			break;
		case 4:
			testSetPopularidad(c, 101);
			break;
		case 5:
			testSetPopularidad(c, -1);
			break;
		case 6:
			testSetPopularidad(c, null);
			break;
		default:
			System.out.println("Caso de prueba " + p + "no implementado");
			break;
		}

	}

	private static void mostrarCancion(Cancion c) {
		System.out.println("ID: " + c.getID());
		System.out.println("Artista: " + c.getArtista());
		System.out.println("Duración: " + c.getDuration());
		System.out.println("Nombre:" + c.getNombre());
		System.out.println("Número de pista:" + c.getNumeroPista());
		System.out.println("Popularidad:" + c.getPopularidad());
	}

}
