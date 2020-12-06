
package aiss.model.fs;

import java.util.HashMap;
import java.util.Map;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "count",
    "unconfirmedCount",
    "marked",
    "lastCheckinExpiredAt"
})
public class BeenHere {

    @JsonProperty("count")
    private Integer count;
    @JsonProperty("unconfirmedCount")
    private Integer unconfirmedCount;
    @JsonProperty("marked")
    private Boolean marked;
    @JsonProperty("lastCheckinExpiredAt")
    private Integer lastCheckinExpiredAt;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("count")
    public Integer getCount() {
        return count;
    }

    @JsonProperty("count")
    public void setCount(Integer count) {
        this.count = count;
    }

    @JsonProperty("unconfirmedCount")
    public Integer getUnconfirmedCount() {
        return unconfirmedCount;
    }

    @JsonProperty("unconfirmedCount")
    public void setUnconfirmedCount(Integer unconfirmedCount) {
        this.unconfirmedCount = unconfirmedCount;
    }

    @JsonProperty("marked")
    public Boolean getMarked() {
        return marked;
    }

    @JsonProperty("marked")
    public void setMarked(Boolean marked) {
        this.marked = marked;
    }

    @JsonProperty("lastCheckinExpiredAt")
    public Integer getLastCheckinExpiredAt() {
        return lastCheckinExpiredAt;
    }

    @JsonProperty("lastCheckinExpiredAt")
    public void setLastCheckinExpiredAt(Integer lastCheckinExpiredAt) {
        this.lastCheckinExpiredAt = lastCheckinExpiredAt;
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
