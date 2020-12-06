package fp.musica.test;

import java.time.Duration;

import fp.musica.Artista;
import fp.musica.ArtistaImpl;
import fp.musica.CancionDescarga;
import fp.musica.CancionDescargaImpl;
import fp.musica.FormatoCancion;

public class TestCancionDescarga {

	public static void main(String[] args) {
		CancionDescarga c = inicializaCancion();
		System.out.println(c);
		muestraCancion(c);

	}

	private static CancionDescarga inicializaCancion() {
		// Cuidado, si no añadimos la librería fpMusica.jar, en el tipo Artista nos dará
		// error
		Artista a = new ArtistaImpl("23587Z", "Supersubmarina", "Indie", 99,
				"http://images.genius.com/74304ea1ac4b79a8f4f004cbefb13119.953x953x1.jpg");

		CancionDescarga c = new CancionDescargaImpl(a, Duration.ofMinutes(4), "293856X", "En mis venas", 3, 99, 12.7,
				FormatoCancion.MIDI);
		return c;
	}

	private static void muestraCancion(CancionDescarga c) {
		System.out.println("==========Propiedades de CancionDescarga========== ");
		System.out.println("Artista: " + c.getArtista());
		System.out.println("Duración: " + c.getDuration());
		System.out.println("ID: " + c.getID());
		System.out.println("Nombre de la canción: " + c.getNombre());
		System.out.println("Número de pista: " + c.getNumeroPista());
		System.out.println("Popularidad: " + c.getPopularidad());
		System.out.println("Tamaño: " + c.getTamanyo());
		System.out.println("Formato: " + c.getFormato());
	}

}
