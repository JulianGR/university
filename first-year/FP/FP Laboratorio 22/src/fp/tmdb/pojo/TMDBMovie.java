package fp.tmdb.pojo;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class TMDBMovie {

	// id, title, releaseDate, runtime, originalTitle, originalLanguage, popularity,
	// genres, production
	// companies, production countries, vote average, vote counting, revenue,
	// backdrop path, poster path,
	// tagline, y overview.

	private Integer id;
	private String title;

	@JsonProperty("release_date")
	private String releaseDate;
	private Integer runtime;

	@JsonProperty("original_title")
	private String originalTitle;

	@JsonProperty("original_language")
	private String originalLanguage;

	private Integer popularity;
	private List<TMDBNamedObject> genres;

	@JsonProperty("production_companies")
	private List<TMDBNamedObject> productionCompanies;

	@JsonProperty("production_countries")
	private List<TMDBCountry> productionCountries;

	@JsonProperty("vote_average")
	private Integer voteAverage;

	@JsonProperty("vote_count")
	private Integer voteCount;

	private Integer revenue;

	@JsonProperty("backdrop_path")
	private String backdropPath;

	@JsonProperty("poster_path")
	private String posterPath;

	private String tagline;
	private String overview;

	public TMDBMovie() {
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
		TMDBMovie other = (TMDBMovie) obj;
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

	public String getTitle() {
		return title;
	}

	public String getReleaseDate() {
		return releaseDate;
	}

	public Integer getRuntime() {
		return runtime;
	}

	public String getOriginalTitle() {
		return originalTitle;
	}

	public String getOriginalLanguage() {
		return originalLanguage;
	}

	public Integer getPopularity() {
		return popularity;
	}

	public List<TMDBNamedObject> getGenres() {
		return genres;
	}

	public List<TMDBNamedObject> getProductionCompanies() {
		return productionCompanies;
	}

	public List<TMDBCountry> getProductionCountries() {
		return productionCountries;
	}

	public Integer getVoteAverage() {
		return voteAverage;
	}

	public Integer getVoteCount() {
		return voteCount;
	}

	public Integer getRevenue() {
		return revenue;
	}

	public String getBackdropPath() {
		return backdropPath;
	}

	public String getPosterPath() {
		return posterPath;
	}

	public String getTagline() {
		return tagline;
	}

	public String getOverview() {
		return overview;
	}

	public String toString() {
		return "TMDBMovie [getId()=" + getId() + ", getTitle()=" + getTitle() + ", getReleaseDate()=" + getReleaseDate()
				+ ", getRuntime()=" + getRuntime() + ", getOriginalTitle()=" + getOriginalTitle()
				+ ", getOriginalLanguage()=" + getOriginalLanguage() + ", getPopularity()=" + getPopularity()
				+ ", getGenres()=" + getGenres() + ", getProductionCompanies()=" + getProductionCompanies()
				+ ", getProductionCountries()=" + getProductionCountries() + ", getVoteAverage()=" + getVoteAverage()
				+ ", getVoteCount()=" + getVoteCount() + ", getRevenue()=" + getRevenue() + ", getBackdropPath()="
				+ getBackdropPath() + ", getPosterPath()=" + getPosterPath() + ", getTagline()=" + getTagline()
				+ ", getOverview()=" + getOverview() + "]";
	}

}