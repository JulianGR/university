package fp.tmdb.pojo;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class TMDBPerson {
	private Integer id;
	private String name;
	private String birthday;
	private String deathday;

	@JsonProperty("place_of_brith")
	private String placeOfBirth;

	@JsonProperty("also_known_as")
	private List<String> alsoKnownAs;

	@JsonProperty("profile_path")
	private String profilePath;

	public TMDBPerson() {

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
		TMDBPerson other = (TMDBPerson) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	public Integer getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public String getBirthday() {
		return birthday;
	}

	public String getDeathday() {
		return deathday;
	}

	public String getPlaceOfBirth() {
		return placeOfBirth;
	}

	public List<String> getAlsoKnownAs() {
		return alsoKnownAs;
	}

	public String getProfilePath() {
		return profilePath;
	}

	public String toString() {
		return "TMDBPerson [hashCode()=" + hashCode() + ", getId()=" + getId() + ", getName()=" + getName()
				+ ", getBirthday()=" + getBirthday() + ", getDeathday()=" + getDeathday() + ", getPlaceOfBirth()="
				+ getPlaceOfBirth() + ", getAlsoKnownAs()=" + getAlsoKnownAs() + ", getProfilePath()="
				+ getProfilePath() + "]";
	}

}
