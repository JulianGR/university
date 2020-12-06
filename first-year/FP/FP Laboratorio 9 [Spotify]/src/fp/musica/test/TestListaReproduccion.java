package fp.musica.test;

import java.time.Duration;

import fp.musica.Cancion;
import fp.musica.ClienteSpotify;
import fp.musica.ListaReproduccion;
import fp.musica.ListaReproduccionImpl;
import fp.musica.UtilesMusica;

public class TestListaReproduccion {

	public static void main(String[] args) {
		ClienteSpotify cliente = new ClienteSpotify();

		ListaReproduccion lista = UtilesMusica.generaListaReproduccionTema("En mis venas", 2, cliente);
		lista.setNombre("Varios: En mis venas");

		ListaReproduccion lista2 = UtilesMusica.generaListaReproduccionTema("Daft Punk", 2, cliente);
		lista2.setNombre("Varios: Daft Punk");

		ListaReproduccion lista3 = UtilesMusica.generaListaReproduccionTema("Arctic Monkeys", 3, cliente);
		lista3.setNombre("Varios: Arctic Monkeys");

		ListaReproduccion juntas = new ListaReproduccionImpl("Mezcla");
		juntas.incorpora(lista.getCanciones());
		juntas.incorpora(lista2.getCanciones());
		juntas.incorpora(lista3.getCanciones());
		juntas.aleatoriza();
		muestra(juntas);
		UtilesMusica.reproduceListaReproduccion(juntas);

	}

	private static void muestra(ListaReproduccion l) {
		System.out.println("Lista de reproducción: " + l);
		System.out
				.println("===========================================================================================");
		for (Cancion c : l.getCanciones()) {
			System.out.println(artistasSeparadosPorComas(c) + " - " + c.getNombre() + " ("
					+ duracionEnMinutosSegundos(c.getDuracion()) + ")");
		}
	}

	private static String duracionEnMinutosSegundos(Duration duracion) {
		String res = duracion.getSeconds() / 60 + ":";
		long segundos = duracion.getSeconds() % 60;
		if (segundos < 10) {
			res += "0";
		}
		return res + segundos;
	}

	private static String artistasSeparadosPorComas(Cancion c) {
		String s = c.getArtistas().get(0).getNombre();
		for (int i = 1; i < c.getArtistas().size(); i++) {
			s += ", " + c.getArtistas().get(i).getNombre();
		}
		return s;
	}

}
