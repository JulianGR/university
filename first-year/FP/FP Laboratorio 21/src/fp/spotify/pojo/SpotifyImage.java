package fp.spotify.pojo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class SpotifyImage {
	private Integer height;
	private Integer width;
	private String url;

	public SpotifyImage() {

	}

	public Integer getHeight() {
		return height;
	}

	public Integer getWidth() {
		return width;
	}

	public String getUrl() {
		return url;
	}

	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((url == null) ? 0 : url.hashCode());
		return result;
	}

	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		SpotifyImage other = (SpotifyImage) obj;
		if (url == null) {
			if (other.url != null)
				return false;
		} else if (!url.equals(other.url))
			return false;
		return true;
	}

	public String toString() {
		return "SpotifyImage [getHeight()=" + getHeight() + ", getWidth()=" + getWidth() + ", getUrl()=" + getUrl()
				+ ", hashCode()=" + hashCode() + "]";
	}

}
