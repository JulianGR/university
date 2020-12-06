package fp.spotify;

import fp.spotify.pojo.SpotifyAlbum;
import fp.spotify.pojo.SpotifyArtist;
import fp.spotify.pojo.SpotifyArtistRelatedArtists;
import fp.spotify.pojo.SpotifyArtistTopTracks;
import fp.spotify.pojo.SpotifyTrack;

public interface SpotifyAPI {

	SpotifyArtist getArtist(String idArtista);

	SpotifyTrack getTrack(String idTrack);

	SpotifyAlbum getAlbum(String idAlbum);

	SpotifyArtistTopTracks getArtistTopTracksByCountry(String id, String country);

	SpotifyArtistRelatedArtists getArtistRelatedArtists(String id);
}