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
		// Cuidado, si no a�adimos la librer�a fpMusica.jar, en el tipo Artista nos dar�
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
		System.out.println("Duraci�n: " + c.getDuration());
		System.out.println("ID: " + c.getID());
		System.out.println("Nombre de la canci�n: " + c.getNombre());
		System.out.println("N�mero de pista: " + c.getNumeroPista());
		System.out.println("Popularidad: " + c.getPopularidad());
		System.out.println("Tama�o: " + c.getTamanyo());
		System.out.println("Formato: " + c.getFormato());
	}

}
