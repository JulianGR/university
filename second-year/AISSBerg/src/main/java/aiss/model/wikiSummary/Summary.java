
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
    "type",
    "title",
    "displaytitle",
    "namespace",
    "wikibase_item",
    "titles",
    "pageid",
    "thumbnail",
    "originalimage",
    "lang",
    "dir",
    "revision",
    "tid",
    "timestamp",
    "description",
    "coordinates",
    "content_urls",
    "api_urls",
    "extract",
    "extract_html"
})
public class Summary {

    @JsonProperty("type")
    private String type;
    @JsonProperty("title")
    private String title;
    @JsonProperty("displaytitle")
    private String displaytitle;
    @JsonProperty("namespace")
    private Namespace namespace;
    @JsonProperty("wikibase_item")
    private String wikibaseItem;
    @JsonProperty("titles")
    private Titles titles;
    @JsonProperty("pageid")
    private Integer pageid;
    @JsonProperty("thumbnail")
    private Thumbnail thumbnail;
    @JsonProperty("originalimage")
    private Originalimage originalimage;
    @JsonProperty("lang")
    private String lang;
    @JsonProperty("dir")
    private String dir;
    @JsonProperty("revision")
    private String revision;
    @JsonProperty("tid")
    private String tid;
    @JsonProperty("timestamp")
    private String timestamp;
    @JsonProperty("description")
    private String description;
    @JsonProperty("coordinates")
    private Coordinates coordinates;
    @JsonProperty("content_urls")
    private ContentUrls contentUrls;
    @JsonProperty("api_urls")
    private ApiUrls apiUrls;
    @JsonProperty("extract")
    private String extract;
    @JsonProperty("extract_html")
    private String extractHtml;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("type")
    public String getType() {
        return type;
    }

    @JsonProperty("type")
    public void setType(String type) {
        this.type = type;
    }

    @JsonProperty("title")
    public String getTitle() {
        return title;
    }

    @JsonProperty("title")
    public void setTitle(String title) {
        this.title = title;
    }

    @JsonProperty("displaytitle")
    public String getDisplaytitle() {
        return displaytitle;
    }

    @JsonProperty("displaytitle")
    public void setDisplaytitle(String displaytitle) {
        this.displaytitle = displaytitle;
    }

    @JsonProperty("namespace")
    public Namespace getNamespace() {
        return namespace;
    }

    @JsonProperty("namespace")
    public void setNamespace(Namespace namespace) {
        this.namespace = namespace;
    }

    @JsonProperty("wikibase_item")
    public String getWikibaseItem() {
        return wikibaseItem;
    }

    @JsonProperty("wikibase_item")
    public void setWikibaseItem(String wikibaseItem) {
        this.wikibaseItem = wikibaseItem;
    }

    @JsonProperty("titles")
    public Titles getTitles() {
        return titles;
    }

    @JsonProperty("titles")
    public void setTitles(Titles titles) {
        this.titles = titles;
    }

    @JsonProperty("pageid")
    public Integer getPageid() {
        return pageid;
    }

    @JsonProperty("pageid")
    public void setPageid(Integer pageid) {
        this.pageid = pageid;
    }

    @JsonProperty("thumbnail")
    public Thumbnail getThumbnail() {
        return thumbnail;
    }

    @JsonProperty("thumbnail")
    public void setThumbnail(Thumbnail thumbnail) {
        this.thumbnail = thumbnail;
    }

    @JsonProperty("originalimage")
    public Originalimage getOriginalimage() {
        return originalimage;
    }

    @JsonProperty("originalimage")
    public void setOriginalimage(Originalimage originalimage) {
        this.originalimage = originalimage;
    }

    @JsonProperty("lang")
    public String getLang() {
        return lang;
    }

    @JsonProperty("lang")
    public void setLang(String lang) {
        this.lang = lang;
    }

    @JsonProperty("dir")
    public String getDir() {
        return dir;
    }

    @JsonProperty("dir")
    public void setDir(String dir) {
        this.dir = dir;
    }

    @JsonProperty("revision")
    public String getRevision() {
        return revision;
    }

    @JsonProperty("revision")
    public void setRevision(String revision) {
        this.revision = revision;
    }

    @JsonProperty("tid")
    public String getTid() {
        return tid;
    }

    @JsonProperty("tid")
    public void setTid(String tid) {
        this.tid = tid;
    }

    @JsonProperty("timestamp")
    public String getTimestamp() {
        return timestamp;
    }

    @JsonProperty("timestamp")
    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    @JsonProperty("description")
    public String getDescription() {
        return description;
    }

    @JsonProperty("description")
    public void setDescription(String description) {
        this.description = description;
    }

    @JsonProperty("coordinates")
    public Coordinates getCoordinates() {
        return coordinates;
    }

    @JsonProperty("coordinates")
    public void setCoordinates(Coordinates coordinates) {
        this.coordinates = coordinates;
    }

    @JsonProperty("content_urls")
    public ContentUrls getContentUrls() {
        return contentUrls;
    }

    @JsonProperty("content_urls")
    public void setContentUrls(ContentUrls contentUrls) {
        this.contentUrls = contentUrls;
    }

    @JsonProperty("api_urls")
    public ApiUrls getApiUrls() {
        return apiUrls;
    }

    @JsonProperty("api_urls")
    public void setApiUrls(ApiUrls apiUrls) {
        this.apiUrls = apiUrls;
    }

    @JsonProperty("extract")
    public String getExtract() {
        return extract;
    }

    @JsonProperty("extract")
    public void setExtract(String extract) {
        this.extract = extract;
    }

    @JsonProperty("extract_html")
    public String getExtractHtml() {
        return extractHtml;
    }

    @JsonProperty("extract_html")
    public void setExtractHtml(String extractHtml) {
        this.extractHtml = extractHtml;
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
