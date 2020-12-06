
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
    "id",
    "name",
    "contact",
    "location",
    "canonicalUrl",
    "categories",
    "verified",
    "stats",
    "url",
    "likes",
    "rating",
    "ratingColor",
    "ratingSignals",
    "beenHere",
    "photos",
    "description",
    "storeId",
    "page",
    "hereNow",
    "createdAt",
    "tips",
    "shortUrl",
    "timeZone",
    "listed",
    "phrases",
    "hours",
    "popular",
    "pageUpdates",
    "inbox",
    "venueChains",
    "attributes",
    "bestPhoto"
})
public class Venue {

    @JsonProperty("id")
    private String id;
    @JsonProperty("name")
    private String name;
    @JsonProperty("contact")
    private Contact contact;
    @JsonProperty("location")
    private Location location;
    @JsonProperty("canonicalUrl")
    private String canonicalUrl;
    @JsonProperty("categories")
    private List<Category> categories = null;
    @JsonProperty("verified")
    private Boolean verified;
    @JsonProperty("stats")
    private Stats stats;
    @JsonProperty("url")
    private String url;
    @JsonProperty("likes")
    private Likes likes;
    @JsonProperty("rating")
    private Double rating;
    @JsonProperty("ratingColor")
    private String ratingColor;
    @JsonProperty("ratingSignals")
    private Integer ratingSignals;
    @JsonProperty("beenHere")
    private BeenHere beenHere;
    @JsonProperty("photos")
    private Photos photos;
    @JsonProperty("description")
    private String description;
    @JsonProperty("storeId")
    private String storeId;
    @JsonProperty("page")
    private Page page;
    @JsonProperty("hereNow")
    private HereNow hereNow;
    @JsonProperty("createdAt")
    private Integer createdAt;
    @JsonProperty("tips")
    private Tips_ tips;
    @JsonProperty("shortUrl")
    private String shortUrl;
    @JsonProperty("timeZone")
    private String timeZone;
    @JsonProperty("listed")
    private Listed listed;
    @JsonProperty("phrases")
    private List<Phrase> phrases = null;
    @JsonProperty("hours")
    private Hours hours;
    @JsonProperty("popular")
    private Popular popular;
    @JsonProperty("pageUpdates")
    private PageUpdates pageUpdates;
    @JsonProperty("inbox")
    private Inbox inbox;
    @JsonProperty("venueChains")
    private List<Object> venueChains = null;
    @JsonProperty("attributes")
    private Attributes attributes;
    @JsonProperty("bestPhoto")
    private BestPhoto bestPhoto;
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

    @JsonProperty("name")
    public String getName() {
        return name;
    }

    @JsonProperty("name")
    public void setName(String name) {
        this.name = name;
    }

    @JsonProperty("contact")
    public Contact getContact() {
        return contact;
    }

    @JsonProperty("contact")
    public void setContact(Contact contact) {
        this.contact = contact;
    }

    @JsonProperty("location")
    public Location getLocation() {
        return location;
    }

    @JsonProperty("location")
    public void setLocation(Location location) {
        this.location = location;
    }

    @JsonProperty("canonicalUrl")
    public String getCanonicalUrl() {
        return canonicalUrl;
    }

    @JsonProperty("canonicalUrl")
    public void setCanonicalUrl(String canonicalUrl) {
        this.canonicalUrl = canonicalUrl;
    }

    @JsonProperty("categories")
    public List<Category> getCategories() {
        return categories;
    }

    @JsonProperty("categories")
    public void setCategories(List<Category> categories) {
        this.categories = categories;
    }

    @JsonProperty("verified")
    public Boolean getVerified() {
        return verified;
    }

    @JsonProperty("verified")
    public void setVerified(Boolean verified) {
        this.verified = verified;
    }

    @JsonProperty("stats")
    public Stats getStats() {
        return stats;
    }

    @JsonProperty("stats")
    public void setStats(Stats stats) {
        this.stats = stats;
    }

    @JsonProperty("url")
    public String getUrl() {
        return url;
    }

    @JsonProperty("url")
    public void setUrl(String url) {
        this.url = url;
    }

    @JsonProperty("likes")
    public Likes getLikes() {
        return likes;
    }

    @JsonProperty("likes")
    public void setLikes(Likes likes) {
        this.likes = likes;
    }

    @JsonProperty("rating")
    public Double getRating() {
        return rating;
    }

    @JsonProperty("rating")
    public void setRating(Double rating) {
        this.rating = rating;
    }

    @JsonProperty("ratingColor")
    public String getRatingColor() {
        return ratingColor;
    }

    @JsonProperty("ratingColor")
    public void setRatingColor(String ratingColor) {
        this.ratingColor = ratingColor;
    }

    @JsonProperty("ratingSignals")
    public Integer getRatingSignals() {
        return ratingSignals;
    }

    @JsonProperty("ratingSignals")
    public void setRatingSignals(Integer ratingSignals) {
        this.ratingSignals = ratingSignals;
    }

    @JsonProperty("beenHere")
    public BeenHere getBeenHere() {
        return beenHere;
    }

    @JsonProperty("beenHere")
    public void setBeenHere(BeenHere beenHere) {
        this.beenHere = beenHere;
    }

    @JsonProperty("photos")
    public Photos getPhotos() {
        return photos;
    }

    @JsonProperty("photos")
    public void setPhotos(Photos photos) {
        this.photos = photos;
    }

    @JsonProperty("description")
    public String getDescription() {
        return description;
    }

    @JsonProperty("description")
    public void setDescription(String description) {
        this.description = description;
    }

    @JsonProperty("storeId")
    public String getStoreId() {
        return storeId;
    }

    @JsonProperty("storeId")
    public void setStoreId(String storeId) {
        this.storeId = storeId;
    }

    @JsonProperty("page")
    public Page getPage() {
        return page;
    }

    @JsonProperty("page")
    public void setPage(Page page) {
        this.page = page;
    }

    @JsonProperty("hereNow")
    public HereNow getHereNow() {
        return hereNow;
    }

    @JsonProperty("hereNow")
    public void setHereNow(HereNow hereNow) {
        this.hereNow = hereNow;
    }

    @JsonProperty("createdAt")
    public Integer getCreatedAt() {
        return createdAt;
    }

    @JsonProperty("createdAt")
    public void setCreatedAt(Integer createdAt) {
        this.createdAt = createdAt;
    }

    @JsonProperty("tips")
    public Tips_ getTips() {
        return tips;
    }

    @JsonProperty("tips")
    public void setTips(Tips_ tips) {
        this.tips = tips;
    }

    @JsonProperty("shortUrl")
    public String getShortUrl() {
        return shortUrl;
    }

    @JsonProperty("shortUrl")
    public void setShortUrl(String shortUrl) {
        this.shortUrl = shortUrl;
    }

    @JsonProperty("timeZone")
    public String getTimeZone() {
        return timeZone;
    }

    @JsonProperty("timeZone")
    public void setTimeZone(String timeZone) {
        this.timeZone = timeZone;
    }

    @JsonProperty("listed")
    public Listed getListed() {
        return listed;
    }

    @JsonProperty("listed")
    public void setListed(Listed listed) {
        this.listed = listed;
    }

    @JsonProperty("phrases")
    public List<Phrase> getPhrases() {
        return phrases;
    }

    @JsonProperty("phrases")
    public void setPhrases(List<Phrase> phrases) {
        this.phrases = phrases;
    }

    @JsonProperty("hours")
    public Hours getHours() {
        return hours;
    }

    @JsonProperty("hours")
    public void setHours(Hours hours) {
        this.hours = hours;
    }

    @JsonProperty("popular")
    public Popular getPopular() {
        return popular;
    }

    @JsonProperty("popular")
    public void setPopular(Popular popular) {
        this.popular = popular;
    }

    @JsonProperty("pageUpdates")
    public PageUpdates getPageUpdates() {
        return pageUpdates;
    }

    @JsonProperty("pageUpdates")
    public void setPageUpdates(PageUpdates pageUpdates) {
        this.pageUpdates = pageUpdates;
    }

    @JsonProperty("inbox")
    public Inbox getInbox() {
        return inbox;
    }

    @JsonProperty("inbox")
    public void setInbox(Inbox inbox) {
        this.inbox = inbox;
    }

    @JsonProperty("venueChains")
    public List<Object> getVenueChains() {
        return venueChains;
    }

    @JsonProperty("venueChains")
    public void setVenueChains(List<Object> venueChains) {
        this.venueChains = venueChains;
    }

    @JsonProperty("attributes")
    public Attributes getAttributes() {
        return attributes;
    }

    @JsonProperty("attributes")
    public void setAttributes(Attributes attributes) {
        this.attributes = attributes;
    }

    @JsonProperty("bestPhoto")
    public BestPhoto getBestPhoto() {
        return bestPhoto;
    }

    @JsonProperty("bestPhoto")
    public void setBestPhoto(BestPhoto bestPhoto) {
        this.bestPhoto = bestPhoto;
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
