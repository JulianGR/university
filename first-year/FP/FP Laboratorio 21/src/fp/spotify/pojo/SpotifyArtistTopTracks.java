package fp.spotify.pojo;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class SpotifyArtistTopTracks {
	private List<SpotifyTrack> tracks;

	public SpotifyArtistTopTracks() {

	}

	public List<SpotifyTrack> getTracks() {
		return tracks;
	}

	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((tracks == null) ? 0 : tracks.hashCode());
		return result;
	}

	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		SpotifyArtistTopTracks other = (SpotifyArtistTopTracks) obj;
		if (tracks == null) {
			if (other.tracks != null)
				return false;
		} else if (!tracks.equals(other.tracks))
			return false;
		return true;
	}

	public String toString() {
		return "SpotifyArtistTopTracks [getTracks()=" + getTracks() + ", hashCode()=" + hashCode() + "]";
	}

}
