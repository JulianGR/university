package fp.spotify.test;

import fp.spotify.SpotifyAPI;
import fp.spotify.SpotifyAPIImpl;
import fp.spotify.pojo.SpotifyAlbum;
import fp.spotify.pojo.SpotifyArtist;
import fp.spotify.pojo.SpotifyArtistRelatedArtists;
import fp.spotify.pojo.SpotifyArtistTopTracks;
import fp.spotify.pojo.SpotifyTrack;

public class TestSpotifyAPI {

	public static void main(String[] args) {
		// SpotifyAPI api = new SpotifyAPIImpl();
		SpotifyAPI api = new SpotifyAPIImpl();
		testGetArtist(api, "0TnOYISbd1XYRBk9myaseg");
		// testGetArtist(api, "ABC"); // Artista no encontrado

		testGetAlbum(api, "0sNOF9WDwhWunNAHPD3Baj");

		testGetTrack(api, "3n3Ppam7vgaVa1iaRUc9Lp");

		testGetTopTracks(api, "2BTZIqw0ntH9MvilQ3ewNY", "ES");

		testGetArtistRelatedArtists(api, "2BTZIqw0ntH9MvilQ3ewNY");

	}

	private static void testGetArtist(SpotifyAPI api, String idArtist) {
		System.out.println("\nSolicitando el artista con id " + idArtist);
		SpotifyArtist artist = api.getArtist(idArtist);
		System.out.println(artist);
		System.out.println("================================================");
	}

	private static void testGetTrack(SpotifyAPI api, String idCancion) {
		System.out.println("\nSolicitando la canción con id " + idCancion);
		SpotifyTrack track = api.getTrack(idCancion);
		System.out.println(track);
		System.out.println("================================================");
	}

	private static void testGetAlbum(SpotifyAPI api, String idAlbum) {
		System.out.println("\nSolicitando el album con id " + idAlbum);
		SpotifyAlbum album = api.getAlbum(idAlbum);
		System.out.println(album);
		System.out.println("================================================");
	}

	private static void testGetTopTracks(SpotifyAPI api, String id, String country) {
		System.out.println("\nSolicitando los top tracks con id " + id);
		SpotifyArtistTopTracks topTracks = api.getArtistTopTracksByCountry(id, country);
		System.out.println(topTracks);
		System.out.println(":::::::::::::::::::::::::::::::::::::::");
		topTracks.getTracks().stream().forEach(x -> System.out.println(x.getName()));
		System.out.println("================================================");

	}

	private static void testGetArtistRelatedArtists(SpotifyAPI api, String id) {
		System.out.println("\nSolicitando los artistas relacionados al artista con id " + id);
		SpotifyArtistRelatedArtists relArtists = api.getArtistRelatedArtists(id);
		System.out.println(relArtists);
		System.out.println(":::::::::::::::::::::::::::::::::::::::");
		relArtists.getArtists().stream().forEach(x -> System.out.println(x.getName()));
		System.out.println("================================================");
	}
}
