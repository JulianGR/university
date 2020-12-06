package fp.spotify.pojo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class SpotifyArtistSimplified {
	private String id;
	private String name;

	public SpotifyArtistSimplified() {

	}

	public String getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		SpotifyArtistSimplified other = (SpotifyArtistSimplified) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	public String toString() {
		return "SpotifyArtistSimplified [getId()=" + getId() + ", getName()=" + getName() + ", hashCode()=" + hashCode()
				+ "]";
	}

}