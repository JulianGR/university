
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
    "summary",
    "metadata",
    "references",
    "media",
    "edit_html",
    "talk_page_html"
})
public class ApiUrls {

    @JsonProperty("summary")
    private String summary;
    @JsonProperty("metadata")
    private String metadata;
    @JsonProperty("references")
    private String references;
    @JsonProperty("media")
    private String media;
    @JsonProperty("edit_html")
    private String editHtml;
    @JsonProperty("talk_page_html")
    private String talkPageHtml;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("summary")
    public String getSummary() {
        return summary;
    }

    @JsonProperty("summary")
    public void setSummary(String summary) {
        this.summary = summary;
    }

    @JsonProperty("metadata")
    public String getMetadata() {
        return metadata;
    }

    @JsonProperty("metadata")
    public void setMetadata(String metadata) {
        this.metadata = metadata;
    }

    @JsonProperty("references")
    public String getReferences() {
        return references;
    }

    @JsonProperty("references")
    public void setReferences(String references) {
        this.references = references;
    }

    @JsonProperty("media")
    public String getMedia() {
        return media;
    }

    @JsonProperty("media")
    public void setMedia(String media) {
        this.media = media;
    }

    @JsonProperty("edit_html")
    public String getEditHtml() {
        return editHtml;
    }

    @JsonProperty("edit_html")
    public void setEditHtml(String editHtml) {
        this.editHtml = editHtml;
    }

    @JsonProperty("talk_page_html")
    public String getTalkPageHtml() {
        return talkPageHtml;
    }

    @JsonProperty("talk_page_html")
    public void setTalkPageHtml(String talkPageHtml) {
        this.talkPageHtml = talkPageHtml;
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
