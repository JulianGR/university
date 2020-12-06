
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
    "status",
    "isOpen",
    "isLocalHoliday",
    "timeframes"
})
public class Popular {

    @JsonProperty("status")
    private String status;
    @JsonProperty("isOpen")
    private Boolean isOpen;
    @JsonProperty("isLocalHoliday")
    private Boolean isLocalHoliday;
    @JsonProperty("timeframes")
    private List<Timeframe_> timeframes = null;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("status")
    public String getStatus() {
        return status;
    }

    @JsonProperty("status")
    public void setStatus(String status) {
        this.status = status;
    }

    @JsonProperty("isOpen")
    public Boolean getIsOpen() {
        return isOpen;
    }

    @JsonProperty("isOpen")
    public void setIsOpen(Boolean isOpen) {
        this.isOpen = isOpen;
    }

    @JsonProperty("isLocalHoliday")
    public Boolean getIsLocalHoliday() {
        return isLocalHoliday;
    }

    @JsonProperty("isLocalHoliday")
    public void setIsLocalHoliday(Boolean isLocalHoliday) {
        this.isLocalHoliday = isLocalHoliday;
    }

    @JsonProperty("timeframes")
    public List<Timeframe_> getTimeframes() {
        return timeframes;
    }

    @JsonProperty("timeframes")
    public void setTimeframes(List<Timeframe_> timeframes) {
        this.timeframes = timeframes;
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
