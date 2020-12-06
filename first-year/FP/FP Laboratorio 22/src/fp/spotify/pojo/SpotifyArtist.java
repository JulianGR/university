package fp.spotify.pojo;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class SpotifyArtist {

	private String id;
	private String name;
	private List<String> genres;
	private Integer popularity;
	private List<SpotifyImage> images;

	public SpotifyArtist() {
	}

	public String getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public List<String> getGenres() {
		return genres;
	}

	public Integer getPopularity() {
		return popularity;
	}

	public List<SpotifyImage> getImages() {
		return images;
	}

	public String toString() {
		return "SpotifyArtist [getId()=" + getId() + ", getNombre()=" + getName() + ", getGenres()=" + getGenres()
				+ ", getPopularity()=" + getPopularity() + ", getImages()=" + getImages() + "]";
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
		SpotifyArtist other = (SpotifyArtist) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

}