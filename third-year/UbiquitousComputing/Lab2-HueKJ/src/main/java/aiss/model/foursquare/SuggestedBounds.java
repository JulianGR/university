
package aiss.model.foursquare;

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
    "ne",
    "sw"
})
public class SuggestedBounds {

    @JsonProperty("ne")
    private Ne_ ne;
    @JsonProperty("sw")
    private Sw_ sw;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("ne")
    public Ne_ getNe() {
        return ne;
    }

    @JsonProperty("ne")
    public void setNe(Ne_ ne) {
        this.ne = ne;
    }

    @JsonProperty("sw")
    public Sw_ getSw() {
        return sw;
    }

    @JsonProperty("sw")
    public void setSw(Sw_ sw) {
        this.sw = sw;
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
