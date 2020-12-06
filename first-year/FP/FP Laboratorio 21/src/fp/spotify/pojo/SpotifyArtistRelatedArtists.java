package fp.spotify.pojo;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class SpotifyArtistRelatedArtists {

	private List<SpotifyArtistSimplified> artists;

	public SpotifyArtistRelatedArtists() {

	}

	public List<SpotifyArtistSimplified> getArtists() {
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
		SpotifyArtistRelatedArtists other = (SpotifyArtistRelatedArtists) obj;
		if (artists == null) {
			if (other.artists != null)
				return false;
		} else if (!artists.equals(other.artists))
			return false;
		return true;
	}

	public String toString() {
		return "SpotifyArtistRelatedArtists [getArtists()=" + getArtists() + ", hashCode()=" + hashCode() + "]";
	}

}
