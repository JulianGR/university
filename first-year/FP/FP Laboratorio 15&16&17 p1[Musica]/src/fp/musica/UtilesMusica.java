package fp.musica;

import java.util.Arrays;
import java.util.List;

import fp.musica.Album;
import fp.musica.Artista;
import fp.musica.ClienteSpotify;

public class UtilesMusica {

	public static ListaReproduccion generaListaReproduccion(String artista, int maxCanciones, ClienteSpotify cliente) {
		List<Artista> artistas = cliente.getArtistas(artista);
		ListaReproduccion lista = new ListaReproduccionImpl2(artista);

		if (!artistas.isEmpty()) {
			List<Album> albumes = cliente.getAlbumesArtista(artistas.get(0).getId());
			for (Album a : albumes) {
				lista.incorpora(a);
			}
			lista.aleatoriza();
			if (lista.getNumeroCanciones() > maxCanciones) {
				lista.eliminaTrozo(maxCanciones, lista.getNumeroCanciones() - 1);
			}
		}
		return lista;
	}

	public static ListaReproduccion generaListaDiscografia(String artista, ClienteSpotify cliente) {
		ListaReproduccion res = new ListaReproduccionImpl2("Discografía de " + artista);
		List<Artista> artistas = cliente.getArtistas(artista);
		if (!artistas.isEmpty()) {
			for (Album a : cliente.getAlbumesArtista(artistas.get(0).getId())) {
				res.incorpora(a);
			}
		}
		return res;
	}

	public static ListaReproduccion generaListaReproduccion(String[] artistas, int maxCancionesPorArtista,
			ClienteSpotify cliente) {
		ListaReproduccion res = new ListaReproduccionImpl2(
				"Varios de " + Arrays.toString(artistas).replace("[", "").replace("]", ""));
		for (String artista : artistas) {
			ListaReproduccion l = generaListaReproduccion(artista, maxCancionesPorArtista, cliente);
			res.incorpora(l.getCanciones());
		}
		res.aleatoriza();
		return res;
	}
}
