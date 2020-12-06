
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
public class Bounds {

    @JsonProperty("ne")
    private Ne ne;
    @JsonProperty("sw")
    private Sw sw;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("ne")
    public Ne getNe() {
        return ne;
    }

    @JsonProperty("ne")
    public void setNe(Ne ne) {
        this.ne = ne;
    }

    @JsonProperty("sw")
    public Sw getSw() {
        return sw;
    }

    @JsonProperty("sw")
    public void setSw(Sw sw) {
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
