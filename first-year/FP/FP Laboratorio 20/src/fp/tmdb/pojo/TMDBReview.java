package fp.tmdb.pojo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class TMDBReview {

	private String id;
	private String author;
	private String content;

	@JsonProperty("media_title")
	private String mediaTitle;

	@JsonProperty("media_type")
	private String mediaType;

	private String url;

	public TMDBReview() {

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
		TMDBReview other = (TMDBReview) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	public String getId() {
		return id;
	}

	public String getAuthor() {
		return author;
	}

	public String getContent() {
		return content;
	}

	public String getMediaTitle() {
		return mediaTitle;
	}

	public String getMediaType() {
		return mediaType;
	}

	public String getUrl() {
		return url;
	}

	public String toString() {
		return "TMDBReview [hashCode()=" + hashCode() + ", getId()=" + getId() + ", getAuthor()=" + getAuthor()
				+ ", getContent()=" + getContent() + ", getMediaTitle()=" + getMediaTitle() + ", getMediaType()="
				+ getMediaType() + ", getUrl()=" + getUrl() + "]";
	}

}
