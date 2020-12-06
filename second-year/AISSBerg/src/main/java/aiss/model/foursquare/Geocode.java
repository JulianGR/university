
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
    "what",
    "where",
    "center",
    "displayString",
    "cc",
    "geometry",
    "slug",
    "longId"
})
public class Geocode {

    @JsonProperty("what")
    private String what;
    @JsonProperty("where")
    private String where;
    @JsonProperty("center")
    private Center center;
    @JsonProperty("displayString")
    private String displayString;
    @JsonProperty("cc")
    private String cc;
    @JsonProperty("geometry")
    private Geometry geometry;
    @JsonProperty("slug")
    private String slug;
    @JsonProperty("longId")
    private String longId;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("what")
    public String getWhat() {
        return what;
    }

    @JsonProperty("what")
    public void setWhat(String what) {
        this.what = what;
    }

    @JsonProperty("where")
    public String getWhere() {
        return where;
    }

    @JsonProperty("where")
    public void setWhere(String where) {
        this.where = where;
    }

    @JsonProperty("center")
    public Center getCenter() {
        return center;
    }

    @JsonProperty("center")
    public void setCenter(Center center) {
        this.center = center;
    }

    @JsonProperty("displayString")
    public String getDisplayString() {
        return displayString;
    }

    @JsonProperty("displayString")
    public void setDisplayString(String displayString) {
        this.displayString = displayString;
    }

    @JsonProperty("cc")
    public String getCc() {
        return cc;
    }

    @JsonProperty("cc")
    public void setCc(String cc) {
        this.cc = cc;
    }

    @JsonProperty("geometry")
    public Geometry getGeometry() {
        return geometry;
    }

    @JsonProperty("geometry")
    public void setGeometry(Geometry geometry) {
        this.geometry = geometry;
    }

    @JsonProperty("slug")
    public String getSlug() {
        return slug;
    }

    @JsonProperty("slug")
    public void setSlug(String slug) {
        this.slug = slug;
    }

    @JsonProperty("longId")
    public String getLongId() {
        return longId;
    }

    @JsonProperty("longId")
    public void setLongId(String longId) {
        this.longId = longId;
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
