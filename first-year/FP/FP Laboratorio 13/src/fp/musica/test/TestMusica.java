package fp.musica.test;

import java.util.List;

import fp.musica.Cancion;
import fp.musica.Musica;

public class TestMusica {
	public static void main(String[] args) {

		List<Cancion> canciones = Musica.creaCancion("resources/canciones.txt");

		for (Cancion c : canciones) {
			System.out.println(c);
		}

	}
}
