
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
    "firstName",
    "gender",
    "photo",
    "type",
    "tips",
    "lists",
    "homeCity",
    "bio",
    "contact"
})
public class User_ {

    @JsonProperty("id")
    private String id;
    @JsonProperty("firstName")
    private String firstName;
    @JsonProperty("gender")
    private String gender;
    @JsonProperty("photo")
    private Photo photo;
    @JsonProperty("type")
    private String type;
    @JsonProperty("tips")
    private Tips tips;
    @JsonProperty("lists")
    private Lists lists;
    @JsonProperty("homeCity")
    private String homeCity;
    @JsonProperty("bio")
    private String bio;
    @JsonProperty("contact")
    private Contact_ contact;
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

    @JsonProperty("firstName")
    public String getFirstName() {
        return firstName;
    }

    @JsonProperty("firstName")
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    @JsonProperty("gender")
    public String getGender() {
        return gender;
    }

    @JsonProperty("gender")
    public void setGender(String gender) {
        this.gender = gender;
    }

    @JsonProperty("photo")
    public Photo getPhoto() {
        return photo;
    }

    @JsonProperty("photo")
    public void setPhoto(Photo photo) {
        this.photo = photo;
    }

    @JsonProperty("type")
    public String getType() {
        return type;
    }

    @JsonProperty("type")
    public void setType(String type) {
        this.type = type;
    }

    @JsonProperty("tips")
    public Tips getTips() {
        return tips;
    }

    @JsonProperty("tips")
    public void setTips(Tips tips) {
        this.tips = tips;
    }

    @JsonProperty("lists")
    public Lists getLists() {
        return lists;
    }

    @JsonProperty("lists")
    public void setLists(Lists lists) {
        this.lists = lists;
    }

    @JsonProperty("homeCity")
    public String getHomeCity() {
        return homeCity;
    }

    @JsonProperty("homeCity")
    public void setHomeCity(String homeCity) {
        this.homeCity = homeCity;
    }

    @JsonProperty("bio")
    public String getBio() {
        return bio;
    }

    @JsonProperty("bio")
    public void setBio(String bio) {
        this.bio = bio;
    }

    @JsonProperty("contact")
    public Contact_ getContact() {
        return contact;
    }

    @JsonProperty("contact")
    public void setContact(Contact_ contact) {
        this.contact = contact;
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
