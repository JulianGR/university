package fp.spotify;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.core.type.TypeReference;

import es.us.oauthclient.OAuth2Config;
import es.us.oauthclient.apis.SpotifyConfig;
import fp.rest.ClienteREST;
import fp.rest.ClienteRESTOAuthImpl;
import fp.spotify.pojo.SpotifyAlbum;
import fp.spotify.pojo.SpotifyAlbumSearch;
import fp.spotify.pojo.SpotifyAlbumSimplified;
import fp.spotify.pojo.SpotifyArtist;
import fp.spotify.pojo.SpotifyArtistRelatedArtists;
import fp.spotify.pojo.SpotifyArtistSearch;
import fp.spotify.pojo.SpotifyArtistTopTracks;
import fp.spotify.pojo.SpotifyPage;
import fp.spotify.pojo.SpotifyTrack;
import fp.spotify.pojo.SpotifyTrackSearch;

/* 
 * 
 * Esta clase permite acceder a los recursos de la API REST de Spotify. 
 * Trabaja directamente con los POJOS que representan los tipos de Spotify.
 * */
public class SpotifyAPIImpl implements SpotifyAPI {

	/* Constantes con las URL base para los endpoints de la API */
	private static String URL_BASE = "https://api.spotify.com/v1/";
	private static String URL_ARTIST = URL_BASE + "artists/";
	private static String URL_TRACK = URL_BASE + "tracks/";
	private static String URL_ALBUM = URL_BASE + "albums/";

	private ClienteREST cliente;

	private static final Integer RESULTADOS_POR_PAGINA = 50; // Es el m�ximo
																// permitido

	public SpotifyAPIImpl() {

		// El objeto de tipo SpotifyConfig busca el archivo
		// keys.properties en el directorio home del usuario
		// Como lo tenemos en un sitio distinto, cambiamos
		// la variable home del usuario para que apunte al directorio
		// ra�z del proyecto.

		System.setProperty("user.home", ".");
		OAuth2Config config = new SpotifyConfig();
		this.cliente = new ClienteRESTOAuthImpl(config);
	}

	public SpotifyArtist getArtist(String idArtista) {
		return cliente.get(URL_ARTIST + idArtista, SpotifyArtist.class);
	}

	public SpotifyTrack getTrack(String idCancion) {
		return cliente.get(URL_TRACK + idCancion, SpotifyTrack.class);
	}

	public SpotifyAlbum getAlbum(String idAlbum) {
		return cliente.get(URL_ALBUM + idAlbum, SpotifyAlbum.class);
	}

	public SpotifyArtistTopTracks getArtistTopTracks(String idArtista, String country) {
		String[] parametros = { "country" };
		String[] valores = { "ES" };

		return cliente.get(URL_ARTIST + idArtista + "/top-tracks/", parametros, valores, SpotifyArtistTopTracks.class);

	}

	public SpotifyArtistRelatedArtists getArtistRelatedArtists(String id) {
		return cliente.get(URL_ARTIST + id + "/related-artists", SpotifyArtistRelatedArtists.class);
	}

	// TODO: Clases de Teoría - Paginación Spotify

	public SpotifyArtistSearch getArtistsByName(String cadenaBusqueda) {
		String[] params = { "q", "type", "limit" };
		String[] vals = { cadenaBusqueda, "artist", RESULTADOS_POR_PAGINA.toString() };

		SpotifyArtistSearch sas = cliente.get(URL_ARTIST, params, vals, SpotifyArtistSearch.class);
		return sas;

	}

	private SpotifyArtistSearch getNextPage(SpotifyArtistSearch currentSearch) {

		SpotifyArtistSearch res = null;
		if (currentSearch.getArtists().getNext() != null) {
			res = cliente.get(currentSearch.getArtists().getNext(), SpotifyArtistSearch.class);
		}
		return res;
	}

	public List<SpotifyArtist> getAllArtistsByName(String cadenaBusqueda) {
		List<SpotifyArtist> res = new ArrayList<SpotifyArtist>();
		SpotifyArtistSearch currentSearch = getArtistsByName(cadenaBusqueda);

		while (currentSearch != null) {
			res.addAll(currentSearch.getArtists().getItems());
			currentSearch = getNextPage(currentSearch);
		}

		return res;
	}

	// Estos 2 es SIN contenedor

	public SpotifyPage<SpotifyAlbumSimplified> getAlbumsOfArtist(String idArtista) {
		String url = URL_ARTIST + idArtista + "/albums";
		String[] params = { "limit" };
		String[] vals = { RESULTADOS_POR_PAGINA.toString() };

		TypeReference<SpotifyPage<SpotifyAlbumSimplified>> ref = new TypeReference<SpotifyPage<SpotifyAlbumSimplified>>() {
		};

		SpotifyPage<SpotifyAlbumSimplified> page = cliente.get(url, params, vals, ref);
		return page;
	}

	private <T> SpotifyPage<T> getNextPage(SpotifyPage<T> currentPage) {
		SpotifyPage<T> res = null;
		String urlNext = currentPage.getNext();

		if (urlNext != null) {
			TypeReference<SpotifyPage<T>> ref = new TypeReference<SpotifyPage<T>>() {
			};
			res = cliente.get(urlNext, ref);
		}
		return res;
	}

	public List<SpotifyAlbumSimplified> getAllAlbumsOfArtist(String idArtista) {

		List<SpotifyAlbumSimplified> res = new ArrayList<SpotifyAlbumSimplified>();
		SpotifyPage<SpotifyAlbumSimplified> currentPage = getAlbumsOfArtist(idArtista);

		while (currentPage != null) {
			res.addAll(currentPage.getItems());
			currentPage = getNextPage(currentPage);
		}
		return res;
	}

	// TODO: Laboratorio 22 - Paginación Spotify

	public SpotifyTrackSearch getTracksByName(String busqueda) {
		String[] params = { "q", "type", "limit" };
		String[] vals = { "busqueda", "track", RESULTADOS_POR_PAGINA.toString() };

		SpotifyTrackSearch sts = cliente.get(URL_TRACK, params, vals, SpotifyTrackSearch.class);
		return sts;
	}

	private SpotifyTrackSearch getNextPage(SpotifyTrackSearch currentSearch) {

		SpotifyTrackSearch res = null;
		if (currentSearch.getTracks().getNext() != null) {
			res = cliente.get(currentSearch.getTracks().getNext(), SpotifyTrackSearch.class);
		}
		return res;
	}

	public List<SpotifyTrack> getAllTracksByName(String cadenaBusqueda) {

		List<SpotifyTrack> res = new ArrayList<SpotifyTrack>();
		SpotifyTrackSearch currentSearch = getTracksByName(cadenaBusqueda);

		while (currentSearch != null) {
			res.addAll(currentSearch.getTracks().getItems());
			currentSearch = getNextPage(currentSearch);
		}

		return res;
	}

	// TODO: Para casa

	public SpotifyAlbumSearch getAlbumsByName(String busqueda) {

		String[] params = { "q", "type", "limit" };
		String[] vals = { "busqueda", "album", RESULTADOS_POR_PAGINA.toString() };

		SpotifyAlbumSearch sals = cliente.get(URL_ALBUM, params, vals, SpotifyAlbumSearch.class);
		return sals;
	}

	private SpotifyAlbumSearch getNextPage(SpotifyAlbumSearch currentSearch) {

		SpotifyAlbumSearch res = null;
		if (currentSearch.getAlbums().getNext() != null) {
			res = cliente.get(currentSearch.getAlbums().getNext(), SpotifyAlbumSearch.class);
		}
		return res;
	}

	public List<SpotifyAlbumSimplified> getAllAlbumsByName(String cadenaBusqueda) {

		List<SpotifyAlbumSimplified> res = new ArrayList<SpotifyAlbumSimplified>();
		SpotifyAlbumSearch currentSearch = getAlbumsByName(cadenaBusqueda);

		while (currentSearch != null) {
			res.addAll(currentSearch.getAlbums().getItems());
			currentSearch = getNextPage(currentSearch);
		}

		return res;
	}
}
