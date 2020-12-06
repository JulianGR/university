
package aiss.model.wikiSummary;

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
    "page",
    "revisions",
    "edit",
    "talk"
})
public class Desktop {

    @JsonProperty("page")
    private String page;
    @JsonProperty("revisions")
    private String revisions;
    @JsonProperty("edit")
    private String edit;
    @JsonProperty("talk")
    private String talk;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("page")
    public String getPage() {
        return page;
    }

    @JsonProperty("page")
    public void setPage(String page) {
        this.page = page;
    }

    @JsonProperty("revisions")
    public String getRevisions() {
        return revisions;
    }

    @JsonProperty("revisions")
    public void setRevisions(String revisions) {
        this.revisions = revisions;
    }

    @JsonProperty("edit")
    public String getEdit() {
        return edit;
    }

    @JsonProperty("edit")
    public void setEdit(String edit) {
        this.edit = edit;
    }

    @JsonProperty("talk")
    public String getTalk() {
        return talk;
    }

    @JsonProperty("talk")
    public void setTalk(String talk) {
        this.talk = talk;
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
