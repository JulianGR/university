package fp.spotify.pojo;

/**
 * Este tipo sirve para almacenar la informaci�n devuelta cuando se hace una
 * b�squeda de artista.
 * 
 * @author Ferm�n Cruz
 *
 */

public class SpotifyTrackSearch {
	private SpotifyPage<SpotifyTrack> tracks;

	public SpotifyTrackSearch() {

	}

	public SpotifyPage<SpotifyTrack> getTracks() {
		return tracks;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((tracks == null) ? 0 : tracks.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		SpotifyTrackSearch other = (SpotifyTrackSearch) obj;
		if (tracks == null) {
			if (other.tracks != null)
				return false;
		} else if (!tracks.equals(other.tracks))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "SpotifyTrackSearch [getTracks()=" + getTracks() + ", hashCode()=" + hashCode() + "]";
	}

}
