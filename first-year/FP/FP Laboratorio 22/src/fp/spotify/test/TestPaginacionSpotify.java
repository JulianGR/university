package fp.spotify.test;

import java.util.List;

import fp.spotify.SpotifyAPI;
import fp.spotify.SpotifyAPIImpl;
import fp.spotify.pojo.SpotifyPage;
import fp.spotify.pojo.SpotifyTrack;
import fp.spotify.pojo.SpotifyTrackSearch;
import fp.spotify.pojo.SpotifyAlbumSimplified;
import fp.spotify.pojo.SpotifyArtist;

public class TestPaginacionSpotify {

	public static void main(String[] args) {

		SpotifyAPI api = new SpotifyAPIImpl();

		testGetPageOfAlbumsOfArtist(api, "0OdUWJ0sBjDrqHygGUXeCF");

		testGetTracksByName(api, "La bicicleta");
		testGetTracksByName(api, "Slow Wine");

		testGetAllTracksByName(api, "La bicicleta");
		testGetAllTracksByName(api, "Slow Wine");

		testGetAllAlbumsByName(api, "Car");

	}

	private static void testGetPageOfAlbumsOfArtist(SpotifyAPI api, String idArtist) {
		System.out.println("Solicitando el artista con id " + idArtist);
		SpotifyPage<SpotifyAlbumSimplified> artist = api.getAlbumsOfArtist(idArtist);
		System.out.println(artist);
		System.out.println("================================================");

	}

	private static void testGetTracksByName(SpotifyAPI api, String cancion) {
		System.out.println("Solicitando la canción con nombre " + cancion);
		SpotifyTrackSearch track = api.getTracksByName(cancion);
		System.out.println(track);
		System.out.println("================================================");
	}

	private static void testGetAllTracksByName(SpotifyAPI api, String cancion) {
		System.out.println("Solicitando las canciones con nombre " + cancion);
		List<SpotifyTrack> tracks = api.getAllTracksByName(cancion);
		System.out.println("Número de canciones encontradas: " + tracks.size());
		tracks.forEach(x -> System.out.println(x));
		System.out.println("================================================");
	}

	private static void testGetAllAlbumsByName(SpotifyAPI api, String artista) {
		System.out.println("Solicitando los artistas con nombre " + artista);
		List<SpotifyArtist> tracks = api.getAllArtistsByName(artista);
		System.out.println("Número de artistas encontradas: " + tracks.size());
		tracks.forEach(x -> System.out.println(x));
		System.out.println("================================================");
	}

}