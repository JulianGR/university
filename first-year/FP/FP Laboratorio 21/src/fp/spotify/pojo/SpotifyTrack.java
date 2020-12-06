package fp.spotify.pojo;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class SpotifyTrack {

	private String id;
	private String name;

	@JsonProperty("track_number")
	private Integer trackNumber;

	@JsonProperty("duration_ms")
	private Integer duration;

	@JsonProperty("preview_url")
	private String previewUrl;

	private Integer popularity;

	private List<SpotifyArtistSimplified> artists;

	public SpotifyTrack() {

	}

	public List<SpotifyArtistSimplified> getArtists() {
		return artists;
	}

	public String getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public Integer getTrackNumber() {
		return trackNumber;
	}

	public Integer getDuration() {
		return duration;
	}

	public String getPreviewUrl() {
		return previewUrl;
	}

	public Integer getPopularity() {
		return popularity;
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
		SpotifyTrack other = (SpotifyTrack) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	public String toString() {
		return "SpotifyTrack [getId()=" + getId() + ", getName()=" + getName() + ", getTrackNumber()="
				+ getTrackNumber() + ", getDuration()=" + getDuration() + ", getPreviewUrl()=" + getPreviewUrl()
				+ ", getPopularity()=" + getPopularity() + ", getArtists()=" + getArtists() + ", hashCode()="
				+ hashCode() + "]";
	}
}
