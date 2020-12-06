package fp.spotify.pojo;

/**
 * Este tipo sirve para almacenar la informaciï¿½n devuelta cuando se hace una
 * bï¿½squeda de artista.
 * 
 * @author Fermín Cruz
 *
 */

public class SpotifyArtistSearch {

	private SpotifyPage<SpotifyArtist> artists;

	public SpotifyArtistSearch() {

	}

	public SpotifyPage<SpotifyArtist> getArtists() {
		return artists;
	}

	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((artists == null) ? 0 : artists.hashCode());
		return result;
	}

	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		SpotifyArtistSearch other = (SpotifyArtistSearch) obj;
		if (artists == null) {
			if (other.artists != null)
				return false;
		} else if (!artists.equals(other.artists))
			return false;
		return true;
	}

	public String toString() {
		return "SpotifyArtistSearch [getArtists()=" + getArtists() + ", hashCode()=" + hashCode() + "]";
	}

}
