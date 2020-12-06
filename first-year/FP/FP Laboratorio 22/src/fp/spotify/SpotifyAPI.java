package fp.spotify;

import java.util.List;

import fp.spotify.pojo.SpotifyAlbum;
import fp.spotify.pojo.SpotifyAlbumSearch;
import fp.spotify.pojo.SpotifyAlbumSimplified;
import fp.spotify.pojo.SpotifyArtist;
import fp.spotify.pojo.SpotifyArtistSearch;
import fp.spotify.pojo.SpotifyArtistTopTracks;
import fp.spotify.pojo.SpotifyPage;
import fp.spotify.pojo.SpotifyTrack;
import fp.spotify.pojo.SpotifyTrackSearch;

public interface SpotifyAPI {

	/**
	 * @param idArtista
	 *            Id del artista consultado. El id tiene que ser un id v�lido de
	 *            Spotify.
	 * @return Un objeto de tipo SpotifyArtist con los datos del artista
	 *         consultado. 
	 */
	SpotifyArtist getArtist(String idArtista);

	/**
	 * @param idCancion
	 *            Id de la canci�n consultada. El id tiene que ser un id v�lido
	 *            de Spotify.
	 * @return Un objeto de tipo SpotifyTrack con los datos de la canci�n
	 *         consultada.
	 */
	SpotifyTrack getTrack(String idCancion);

	/**
	 * @param idAlbum
	 *            Id del �lbum consultado. El id tiene que ser un id v�lido de
	 *            Spotify
	 * @return Un objeto de tipo SpotifyAlbum con los datos del album
	 *         consultado.
	 */
	SpotifyAlbum getAlbum(String idAlbum);

	/**
	 * @param idArtista
	 *            Id del artista consultado. El id tiene que ser un id v�lido de
	 *            Spotify.
	 * @param country
	 *            El c�digo de un pa�s en formato
	 *            <a href="https://en.wikipedia.org/wiki/ISO_3166-1_alpha-2">ISO
	 *            3166-1 alpha-2</a>
	 * @return Un objeto de tipo SpotifyArtistTopTracks que es un contenedor de
	 *         las 10 canciones top del artista consultado
	 */
	SpotifyArtistTopTracks getArtistTopTracks(String idArtista, String country);

	/**
	 * @param idArtista id del artista consultado. Debe ser un id v�lido de Spotify.
	 * @return La primera p�gina de resultados con los �lbumes del artista cuyo id es idArtista.
	 */
	SpotifyPage<SpotifyAlbumSimplified> getAlbumsOfArtist(String idArtista);

	/**
	 * @param idArtista idArtista id del artista consultado. Debe ser un id v�lido de Spotify.
	 * @return Una lista con todos los �lbumes del artista que devuelve Spotify
	 */
	List<SpotifyAlbumSimplified> getAllAlbumsOfArtist(String idArtista);

	/**
	 * @param cadenaBusqueda Cadena con el nombre (o parte del nombre) del �lbum buscado
	 * @return Un objeto contenedor con la primera p�gina de resultados de b�squeda.
	 */
	SpotifyAlbumSearch getAlbumsByName(String cadenaBusqueda);

	/**
	 * @param cadenaBusqueda Cadena con el nombre (o parte del nombre) del �lbum buscado
	 * @return Una lista con todos los objetos de tipo SpotifyAlbumSimplified encontrados en la b�squeda
	 */
	List<SpotifyAlbumSimplified> getAllAlbumsByName(String cadenaBusqueda); 
	/**
	 * @param cadenaBusqueda Cadena con el nombre (o parte del nombre) del �lbum buscado
	 * @return Una objeto contenedor con la primera p�gina de resultados de b�squeda
	 */
	SpotifyTrackSearch getTracksByName(String cadenaBusqueda);

	List<SpotifyTrack> getAllTracksByName(String cadenaBusqueda);

	SpotifyArtistSearch getArtistsByName(String cadenaBusqueda);

	List<SpotifyArtist> getAllArtistsByName(String cadenaBusqueda);
	

}