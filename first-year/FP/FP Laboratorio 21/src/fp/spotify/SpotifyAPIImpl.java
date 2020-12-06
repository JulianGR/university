package fp.spotify;

import es.us.oauthclient.OAuth2Config;
import es.us.oauthclient.apis.SpotifyConfig;
import fp.rest.ClienteREST;
import fp.rest.ClienteRESTOAuthImpl;
import fp.spotify.pojo.SpotifyAlbum;
import fp.spotify.pojo.SpotifyArtist;
import fp.spotify.pojo.SpotifyArtistRelatedArtists;
import fp.spotify.pojo.SpotifyArtistTopTracks;
import fp.spotify.pojo.SpotifyTrack;

/* 
 * 
 * Esta clase permite acceder a los recursos de la API REST de Spotify. 
 * Trabaja directamente con los POJOS que respresentan los tipos de Spotify.
 * */
public class SpotifyAPIImpl implements SpotifyAPI {

	private static String URL_BASE = "https://api.spotify.com/v1/";
	private static String URL_ARTIST = URL_BASE + "artists/";
	private static String URL_TRACK = URL_BASE + "tracks/";
	private static String URL_ALBUM = URL_BASE + "albums/";

	private ClienteREST cliente;

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

	public SpotifyArtistTopTracks getArtistTopTracksByCountry(String id, String country) {
		String[] parametros = { "country" };
		String[] valores = { "ES" };

		return cliente.get(URL_ARTIST + id + "/top-tracks/", parametros, valores, SpotifyArtistTopTracks.class);

	}

	public SpotifyArtistRelatedArtists getArtistRelatedArtists(String id) {
		return cliente.get(URL_ARTIST + id + "/related-artists", SpotifyArtistRelatedArtists.class);
	}

}
