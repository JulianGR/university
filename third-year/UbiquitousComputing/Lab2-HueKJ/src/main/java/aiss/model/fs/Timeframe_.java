
package aiss.model.fs;

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
    "days",
    "open",
    "segments"
})
public class Timeframe_ {

    @JsonProperty("days")
    private String days;
    @JsonProperty("open")
    private List<Open_> open = null;
    @JsonProperty("segments")
    private List<Object> segments = null;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("days")
    public String getDays() {
        return days;
    }

    @JsonProperty("days")
    public void setDays(String days) {
        this.days = days;
    }

    @JsonProperty("open")
    public List<Open_> getOpen() {
        return open;
    }

    @JsonProperty("open")
    public void setOpen(List<Open_> open) {
        this.open = open;
    }

    @JsonProperty("segments")
    public List<Object> getSegments() {
        return segments;
    }

    @JsonProperty("segments")
    public void setSegments(List<Object> segments) {
        this.segments = segments;
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
