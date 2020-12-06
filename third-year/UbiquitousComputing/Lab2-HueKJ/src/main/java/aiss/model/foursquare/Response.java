
package aiss.model.foursquare;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "suggestedFilters",
    "geocode",
    "headerLocation",
    "headerFullLocation",
    "headerLocationGranularity",
    "totalResults",
    "suggestedBounds",
    "groups"
})
public class Response {

    @JsonProperty("suggestedFilters")
    private SuggestedFilters suggestedFilters;
    @JsonProperty("geocode")
    private Geocode geocode;
    @JsonProperty("headerLocation")
    private String headerLocation;
    @JsonProperty("headerFullLocation")
    private String headerFullLocation;
    @JsonProperty("headerLocationGranularity")
    private String headerLocationGranularity;
    @JsonProperty("totalResults")
    private Integer totalResults;
    @JsonProperty("suggestedBounds")
    private SuggestedBounds suggestedBounds;
    @JsonProperty("groups")
    private List<Group> groups = null;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("suggestedFilters")
    public SuggestedFilters getSuggestedFilters() {
        return suggestedFilters;
    }

    @JsonProperty("suggestedFilters")
    public void setSuggestedFilters(SuggestedFilters suggestedFilters) {
        this.suggestedFilters = suggestedFilters;
    }

    @JsonProperty("geocode")
    public Geocode getGeocode() {
        return geocode;
    }

    @JsonProperty("geocode")
    public void setGeocode(Geocode geocode) {
        this.geocode = geocode;
    }

    @JsonProperty("headerLocation")
    public String getHeaderLocation() {
        return headerLocation;
    }

    @JsonProperty("headerLocation")
    public void setHeaderLocation(String headerLocation) {
        this.headerLocation = headerLocation;
    }

    @JsonProperty("headerFullLocation")
    public String getHeaderFullLocation() {
        return headerFullLocation;
    }

    @JsonProperty("headerFullLocation")
    public void setHeaderFullLocation(String headerFullLocation) {
        this.headerFullLocation = headerFullLocation;
    }

    @JsonProperty("headerLocationGranularity")
    public String getHeaderLocationGranularity() {
        return headerLocationGranularity;
    }

    @JsonProperty("headerLocationGranularity")
    public void setHeaderLocationGranularity(String headerLocationGranularity) {
        this.headerLocationGranularity = headerLocationGranularity;
    }

    @JsonProperty("totalResults")
    public Integer getTotalResults() {
        return totalResults;
    }

    @JsonProperty("totalResults")
    public void setTotalResults(Integer totalResults) {
        this.totalResults = totalResults;
    }

    @JsonProperty("suggestedBounds")
    public SuggestedBounds getSuggestedBounds() {
        return suggestedBounds;
    }

    @JsonProperty("suggestedBounds")
    public void setSuggestedBounds(SuggestedBounds suggestedBounds) {
        this.suggestedBounds = suggestedBounds;
    }

    @JsonProperty("groups")
    public List<Group> getGroups() {
        return groups;
    }

    @JsonProperty("groups")
    public void setGroups(List<Group> groups) {
        this.groups = groups;
    }

    @JsonAnyGetter
    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    @JsonAnySetter
    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

}
