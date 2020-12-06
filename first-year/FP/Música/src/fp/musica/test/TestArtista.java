package fp.musica.test;

import fp.musica.Artista;
import fp.musica.ArtistaImpl;

public class TestArtista {

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

		testSetPopularidad(1);
		testSetPopularidad(2);
		testSetPopularidad(3);
		testSetPopularidad(4);
		testSetPopularidad(5);
		testSetPopularidad(6);

		testSetURLImagen(1);
		testSetURLImagen(2);
		testSetURLImagen(3);
	}

	private static void testConstructorC1(String id, String nombre, String genero, Integer popularidad,
			String urlImagen) {
		try {
			Artista a = new ArtistaImpl(id, nombre, genero, popularidad, urlImagen);
			mostrarArtista(a);
		} catch (IllegalArgumentException e) {
			System.out.println("**** " + e.getMessage() + "********");
		} catch (Exception e) {
			System.out.println("**** Error inesperado ****");
			System.out.println(e.getMessage());
		}
	}

	private static void testSetPopularidad(Artista a, Integer popularidad) {
		try {
			System.out.println("Artista antes del cambio:");
			mostrarArtista(a);
			a.setPopularidad(popularidad);
			mostrarArtista(a);
		} catch (IllegalArgumentException e) {
			System.out.println("**** " + e.getMessage() + "********");
		} catch (Exception e) {
			System.out.println("**** Error inesperado ****");
			System.out.println(e.getMessage());
		}
	}

	private static void testSetURLImagen(Artista a, String urlImagen) {
		try {
			System.out.println("Artista antes del cambio:");
			mostrarArtista(a);
			a.setURLImagen(urlImagen);
			mostrarArtista(a);
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
			testConstructorC1("1234567890abcdefABCDEF", "Dire Straits", "Rock", 60, "http://miUrl.com/foto.jpg");
			break;
		case 2:
			testConstructorC1(null, "Dire Straits", "Rock", 60, "http://miUrl.com/foto.jpg");
			break;
		case 3:
			testConstructorC1("1234567890abcdefABCDEF", null, "Rock", 60, "http://miUrl.com/foto.jpg");
			break;
		case 4:
			testConstructorC1("1234567890abcdefABCDEF", "Dire Straits", null, 60, "http://miUrl.com/foto.jpg");
			break;
		case 5:
			testConstructorC1("1234567890abcdefABCDEF", "Dire Straits", "Rock", null, "http://miUrl.com/foto.jpg");
			break;
		case 6:
			testConstructorC1("1234567890abcdefABCDEF", "Dire Straits", "Rock", 60, null);
			break;
		case 7:
			testConstructorC1("1234567890abcdefABCDEFX", "Dire Straits", "Rock", 60, "http://miUrl.com/foto.jpg");
			break;
		case 8:
			testConstructorC1("1234567890abcdefABCDE", "Dire Straits", "Rock", 60, "http://miUrl.com/foto.jpg");
			break;
		case 9:
			testConstructorC1("1234567890abcdefABCDEF", "Dire Straits", "Rock", -1, "http://miUrl.com/foto.jpg");
			break;
		case 10:
			testConstructorC1("1234567890abcdefABCDEF", "Dire Straits", "Rock", 101, "http://miUrl.com/foto.jpg");
			break;
		case 11:
			testConstructorC1("1234567890abcdefABCDEF", "Dire Straits", "Rock", 60, "ftp://miUrl.com/foto.jpg");
			break;

		default:
			System.out.println("Caso de prueba " + cp + "no implementado");
			break;
		}
	}

	private static void testSetPopularidad(Integer p) {
		System.out.println("===========SetPopularidad: Caso de prueba" + p + "=============");
		Artista a = new ArtistaImpl("1234567890abcdefABCDEF", "Dire Straits", "Rock", 60, "http://miUrl.com/foto.jpg");
		switch (p) {
		case 1:
			testSetPopularidad(a, 78);
			break;
		case 2:
			testSetPopularidad(a, 0);
			break;
		case 3:
			testSetPopularidad(a, 100);
			break;
		case 4:
			testSetPopularidad(a, 101);
			break;
		case 5:
			testSetPopularidad(a, -1);
			break;
		case 6:
			testSetPopularidad(a, null);
			break;
		default:
			System.out.println("Caso de prueba " + p + "no implementado");
			break;
		}

	}

	private static void testSetURLImagen(Integer g) {
		System.out.println("===SetURLImagen: Caso de prueba" + g + "==========");
		Artista a = new ArtistaImpl("1234567890abcdefABCDEF", "Dire Straits", "Rock", 60, "http://miUrl.com/foto.jpg");
		switch (g) {
		case 1:
			testSetURLImagen(a, "http://miUrl.es/foto.jpg");
			break;
		case 2:
			testSetURLImagen(a, "ftp://miUrl.es/foto.jpg");
			break;
		case 3:
			testSetURLImagen(a, null);
			break;
		default:
			System.out.println("Caso de prueba " + g + "no implementado");
			break;
		}
	}

	private static void mostrarArtista(Artista a) {
		System.out.println("Artista: " + a);
		System.out.println("ID: " + a.getId());
		System.out.println("Nombre: " + a.getNombre());
		System.out.println("Género:" + a.getGenero());
		System.out.println("Popularidad:" + a.getPopularidad());
		System.out.println("URL Imagen:" + a.getURLImagen());
	}
}
