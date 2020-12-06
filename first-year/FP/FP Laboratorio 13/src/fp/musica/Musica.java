package fp.musica;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

import fp.utiles.Ficheros;

public class Musica {
	public static Cancion createCancion(String id, String nombre, List<Artista> artistas, Duration duracion,
			Integer numeroPista, Integer popularidad, String urlPreescucha) {
		return new CancionImpl(id, nombre, artistas, duracion, numeroPista, popularidad, urlPreescucha);

	}

	public static Cancion createCancion(String s) {
		return new CancionImpl(s);
	}

	public static Cancion createCancion(Cancion c) {
		return new CancionImpl(c.getId(), c.getNombre(), c.getArtistas(), c.getDuracion(), c.getNumeroPista(),
				c.getPopularidad(), c.getURLPreescucha());
	}

	public static List<Cancion> creaCancion(String path) {
		List<Cancion> res = new ArrayList<>();
		List<String> lineas = Ficheros.leeFichero(path);
		for (String s : lineas) {
			res.add(new CancionImpl(s));
		}
		return res;

	}

	public static ListaReproduccion createListaReproduccion(String nombre) {
		return new ListaReproduccionImpl(nombre);
	}

	public static ListaReproduccion createListareproduccion(ListaReproduccion l) {
		ListaReproduccion res = new ListaReproduccionImpl(l.getNombre());
		res.incorpora((l.getCanciones()));
		return res;

	}

}
