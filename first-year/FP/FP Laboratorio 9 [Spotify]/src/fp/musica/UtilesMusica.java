package fp.musica;

import java.util.List;

import fp.utiles.Musica;

public class UtilesMusica {

	public static ListaReproduccion generaListaReproduccion(String artista, int maxCanciones, ClienteSpotify cliente) {
		List<Artista> artistas = cliente.getArtistas(artista);
		ListaReproduccion lista = new ListaReproduccionImpl(artista);
		if (!artistas.isEmpty()) {
			List<Album> albumes = cliente.getAlbumes(artistas.get(0).getId());
			for (Album a : albumes) {
				lista.incorpora(a);
			}
			lista.aleatoriza();
			if (lista.getNumeroCanciones() > maxCanciones) {
				lista.eliminaTrozo(maxCanciones, lista.getNumeroCanciones());
			}
		}

		return lista;
	}

	public static ListaReproduccion generaListaReproduccionTema(String tema, int maxCanciones, ClienteSpotify cliente) {
		ListaReproduccion lista = new ListaReproduccionImpl(tema);
		if (!tema.isEmpty()) {
			List<Album> albumes = cliente.getAlbumes(tema, maxCanciones);
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

	public static void reproduceListaReproduccion(ListaReproduccion lista) {

		for (Cancion c : lista.getCanciones()) {
			System.out.println("Reproduciendo: " + c.getNombre());
			Musica.reproduceMP3(c.getURLPreescucha(), 5);

		}
	}

	public static ListaReproduccion generaListaDiscografia(String artista, ClienteSpotify cliente) {
		ListaReproduccion res = new ListaReproduccionImpl("Discografía de " + artista);
		List<Artista> artistas = cliente.getArtistas(artista);
		if (!artistas.isEmpty()) {
			for (Album a : cliente.getAlbumesArtista(artistas.get(0).getId())) {
				res.incorpora(a);
			}
		}
		return res;
	}

}