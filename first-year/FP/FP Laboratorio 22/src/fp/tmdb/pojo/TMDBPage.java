package fp.tmdb.pojo;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class TMDBPage<X> {

	@JsonProperty("page")
	private Integer currentPage;
	@JsonProperty("total_results")
	private Integer totalResults;
	@JsonProperty("total_pages")
	private Integer totalPages;

	private List<X> results;

	public List<X> getResults() {

		return results;
	}

	public Integer getCurrentPage() {
		return currentPage;
	}

	public Integer getTotalResults() {
		return totalResults;
	}

	public Integer getTotalPages() {
		return totalPages;
	}

	public String toString() {
		return "TMDBPage [getResults()=" + getResults() + ", getCurrentPage()=" + getCurrentPage()
				+ ", getTotalResults()=" + getTotalResults() + ", getTotalPages()=" + getTotalPages() + ", hashCode()="
				+ hashCode() + "]";
	}

	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((currentPage == null) ? 0 : currentPage.hashCode());
		result = prime * result + ((results == null) ? 0 : results.hashCode());
		result = prime * result + ((totalPages == null) ? 0 : totalPages.hashCode());
		result = prime * result + ((totalResults == null) ? 0 : totalResults.hashCode());
		return result;
	}

	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		TMDBPage other = (TMDBPage) obj;
		if (currentPage == null) {
			if (other.currentPage != null)
				return false;
		} else if (!currentPage.equals(other.currentPage))
			return false;
		if (results == null) {
			if (other.results != null)
				return false;
		} else if (!results.equals(other.results))
			return false;
		if (totalPages == null) {
			if (other.totalPages != null)
				return false;
		} else if (!totalPages.equals(other.totalPages))
			return false;
		if (totalResults == null) {
			if (other.totalResults != null)
				return false;
		} else if (!totalResults.equals(other.totalResults))
			return false;
		return true;
	}

	
	

}
