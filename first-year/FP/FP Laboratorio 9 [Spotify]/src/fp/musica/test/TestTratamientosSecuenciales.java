package fp.musica.test;

import java.time.Duration;

import fp.musica.ClienteSpotify;
import fp.musica.ListaReproduccion;
import fp.musica.ListaReproduccionImpl;
import fp.musica.UtilesMusica;

public class TestTratamientosSecuenciales {
	private static final Duration DUR = Duration.ofMinutes(3);

	public static void main(String[] args) {
		testTratamientosSecuenciales();

	}

	private static void testTratamientosSecuenciales() {

		ClienteSpotify cliente = new ClienteSpotify();

		ListaReproduccion lista = UtilesMusica.generaListaReproduccionTema("Closure in Moscow", 2, cliente);
		lista.setNombre("Varios: Closure in Moscow");

		ListaReproduccion lista2 = UtilesMusica.generaListaReproduccionTema("Daft Punk", 2, cliente);
		lista2.setNombre("Varios: Daft Punk");

		ListaReproduccion lista3 = UtilesMusica.generaListaReproduccionTema("Supersubmarina", 3, cliente);
		lista3.setNombre("Varios: Supersubmarina");

		ListaReproduccion juntas = new ListaReproduccionImpl("Mezcla");
		juntas.incorpora(lista.getCanciones());
		juntas.incorpora(lista2.getCanciones());
		juntas.incorpora(lista3.getCanciones());
		juntas.aleatoriza();

		System.out.println("¿Es una antología? " + juntas.esAntologia("Daft Punk"));
		System.out.println("¿Contiene al artista " + juntas.getArtistas() + "? " + juntas.contieneArtista("Daft Punk"));
		System.out.println("Posición de la canción " + juntas.getPosicionCancion("Get Lucky"));
		System.out.println("Canción más larga: " + juntas.getCancionMasLarga());
		System.out.println("Duración total: " + juntas.getDuracion());
		System.out.println("Artistas: " + juntas.getArtistas());
		System.out.println("¿Tienen todas las canciones una duración inferior a " + juntas.getDuracion() + "? "
				+ juntas.tieneTodasLasCancionesDuracionInferiorA(DUR));
	}
	/*
	 * System.out.println("Sublista con el artista " + juntas.getArtistas() + ":" +
	 * juntas.getSublistaArtista("Daft Punk").toString());
	 * System.out.println("Fotos de los artistas:" +
	 * juntas.show(juntas.getArtistas().getURLImagenes().get(0)));
	 */

}
