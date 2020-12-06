package fp.spotify.pojo;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class SpotifyAlbum {

	private String id;
	private String name;
	private String type;
	private List<String> genres;
	private List<SpotifyArtistSimplified> artists;
	private Integer popularity;

	@JsonProperty("release_date")
	private String releaseDate;

	private List<SpotifyImage> images;

	public SpotifyAlbum() {

	}

	public String getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public String getType() {
		return type;
	}

	public List<String> getGenres() {
		return genres;
	}

	public List<SpotifyArtistSimplified> getArtists() {
		return artists;
	}

	public Integer getPopularity() {
		return popularity;
	}

	public String getReleaseDate() {
		return releaseDate;
	}

	public List<SpotifyImage> getImages() {
		return images;
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
		SpotifyAlbum other = (SpotifyAlbum) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	public String toString() {
		return "SpotifyAlbum [getId()=" + getId() + ", getName()=" + getName() + ", getType()=" + getType()
				+ ", getGenres()=" + getGenres() + ", getArtists()=" + getArtists() + ", getPopularity()="
				+ getPopularity() + ", getReleaseDate()=" + getReleaseDate() + ", getImages()=" + getImages()
				+ ", hashCode()=" + hashCode() + "]";
	}

}
