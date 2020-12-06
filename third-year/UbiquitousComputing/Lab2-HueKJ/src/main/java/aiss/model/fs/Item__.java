
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
    "id",
    "createdAt",
    "text",
    "type",
    "canonicalUrl",
    "photo",
    "photourl",
    "lang",
    "likes",
    "logView",
    "agreeCount",
    "disagreeCount",
    "todo",
    "user",
    "editedAt",
    "authorInteractionType",
    "url"
})
public class Item__ {

    @JsonProperty("id")
    private String id;
    @JsonProperty("createdAt")
    private Integer createdAt;
    @JsonProperty("text")
    private String text;
    @JsonProperty("type")
    private String type;
    @JsonProperty("canonicalUrl")
    private String canonicalUrl;
    @JsonProperty("photo")
    private Photo_ photo;
    @JsonProperty("photourl")
    private String photourl;
    @JsonProperty("lang")
    private String lang;
    @JsonProperty("likes")
    private Likes_ likes;
    @JsonProperty("logView")
    private Boolean logView;
    @JsonProperty("agreeCount")
    private Integer agreeCount;
    @JsonProperty("disagreeCount")
    private Integer disagreeCount;
    @JsonProperty("todo")
    private Todo todo;
    @JsonProperty("user")
    private User__ user;
    @JsonProperty("editedAt")
    private Integer editedAt;
    @JsonProperty("authorInteractionType")
    private String authorInteractionType;
    @JsonProperty("url")
    private String url;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("id")
    public String getId() {
        return id;
    }

    @JsonProperty("id")
    public void setId(String id) {
        this.id = id;
    }

    @JsonProperty("createdAt")
    public Integer getCreatedAt() {
        return createdAt;
    }

    @JsonProperty("createdAt")
    public void setCreatedAt(Integer createdAt) {
        this.createdAt = createdAt;
    }

    @JsonProperty("text")
    public String getText() {
        return text;
    }

    @JsonProperty("text")
    public void setText(String text) {
        this.text = text;
    }

    @JsonProperty("type")
    public String getType() {
        return type;
    }

    @JsonProperty("type")
    public void setType(String type) {
        this.type = type;
    }

    @JsonProperty("canonicalUrl")
    public String getCanonicalUrl() {
        return canonicalUrl;
    }

    @JsonProperty("canonicalUrl")
    public void setCanonicalUrl(String canonicalUrl) {
        this.canonicalUrl = canonicalUrl;
    }

    @JsonProperty("photo")
    public Photo_ getPhoto() {
        return photo;
    }

    @JsonProperty("photo")
    public void setPhoto(Photo_ photo) {
        this.photo = photo;
    }

    @JsonProperty("photourl")
    public String getPhotourl() {
        return photourl;
    }

    @JsonProperty("photourl")
    public void setPhotourl(String photourl) {
        this.photourl = photourl;
    }

    @JsonProperty("lang")
    public String getLang() {
        return lang;
    }

    @JsonProperty("lang")
    public void setLang(String lang) {
        this.lang = lang;
    }

    @JsonProperty("likes")
    public Likes_ getLikes() {
        return likes;
    }

    @JsonProperty("likes")
    public void setLikes(Likes_ likes) {
        this.likes = likes;
    }

    @JsonProperty("logView")
    public Boolean getLogView() {
        return logView;
    }

    @JsonProperty("logView")
    public void setLogView(Boolean logView) {
        this.logView = logView;
    }

    @JsonProperty("agreeCount")
    public Integer getAgreeCount() {
        return agreeCount;
    }

    @JsonProperty("agreeCount")
    public void setAgreeCount(Integer agreeCount) {
        this.agreeCount = agreeCount;
    }

    @JsonProperty("disagreeCount")
    public Integer getDisagreeCount() {
        return disagreeCount;
    }

    @JsonProperty("disagreeCount")
    public void setDisagreeCount(Integer disagreeCount) {
        this.disagreeCount = disagreeCount;
    }

    @JsonProperty("todo")
    public Todo getTodo() {
        return todo;
    }

    @JsonProperty("todo")
    public void setTodo(Todo todo) {
        this.todo = todo;
    }

    @JsonProperty("user")
    public User__ getUser() {
        return user;
    }

    @JsonProperty("user")
    public void setUser(User__ user) {
        this.user = user;
    }

    @JsonProperty("editedAt")
    public Integer getEditedAt() {
        return editedAt;
    }

    @JsonProperty("editedAt")
    public void setEditedAt(Integer editedAt) {
        this.editedAt = editedAt;
    }

    @JsonProperty("authorInteractionType")
    public String getAuthorInteractionType() {
        return authorInteractionType;
    }

    @JsonProperty("authorInteractionType")
    public void setAuthorInteractionType(String authorInteractionType) {
        this.authorInteractionType = authorInteractionType;
    }

    @JsonProperty("url")
    public String getUrl() {
        return url;
    }

    @JsonProperty("url")
    public void setUrl(String url) {
        this.url = url;
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
