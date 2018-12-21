package com.protean.legislativetracker.zidane.model;

import java.net.URL;
import java.util.Date;
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
        "amendment_id",
        "adopted",
        "chamber",
        "chamber_id",
        "date",
        "title",
        "description",
        "mime",
        "mime_id",
        "url",
        "state_link"
})
public class Amendment {

    @JsonProperty("amendment_id")
    private Integer amendmentId;
    @JsonProperty("adopted")
    private Boolean adopted;
    @JsonProperty("chamber")
    private Character chamber;
    @JsonProperty("chamber_id")
    private Integer chamberId;
    @JsonProperty("date")
    private Date date;
    @JsonProperty("title")
    private String title;
    @JsonProperty("description")
    private String description;
    @JsonProperty("mime")
    private String mime;
    @JsonProperty("mime_id")
    private Integer mimeId;
    @JsonProperty("url")
    private URL url;
    @JsonProperty("state_link")
    private URL stateLink;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("amendment_id")
    public Integer getAmendmentId() {
        return amendmentId;
    }

    @JsonProperty("amendment_id")
    public void setAmendmentId(Integer amendmentId) {
        this.amendmentId = amendmentId;
    }

    @JsonProperty("adopted")
    public Boolean getAdopted() {
        return adopted;
    }

    @JsonProperty("adopted")
    public void setAdopted(Boolean adopted) {
        this.adopted = adopted;
    }

    @JsonProperty("chamber")
    public Character getChamber() {
        return chamber;
    }

    @JsonProperty("chamber")
    public void setChamber(Character chamber) {
        this.chamber = chamber;
    }

    @JsonProperty("chamber_id")
    public Integer getChamberId() {
        return chamberId;
    }

    @JsonProperty("chamber_id")
    public void setChamberId(Integer chamberId) {
        this.chamberId = chamberId;
    }

    @JsonProperty("date")
    public Date getDate() {
        return date;
    }

    @JsonProperty("date")
    public void setDate(Date date) {
        this.date = date;
    }

    @JsonProperty("title")
    public String getTitle() {
        return title;
    }

    @JsonProperty("title")
    public void setTitle(String title) {
        this.title = title;
    }

    @JsonProperty("description")
    public String getDescription() {
        return description;
    }

    @JsonProperty("description")
    public void setDescription(String description) {
        this.description = description;
    }

    @JsonProperty("mime")
    public String getMime() {
        return mime;
    }

    @JsonProperty("mime")
    public void setMime(String mime) {
        this.mime = mime;
    }

    @JsonProperty("mime_id")
    public Integer getMimeId() {
        return mimeId;
    }

    @JsonProperty("mime_id")
    public void setMimeId(Integer mimeId) {
        this.mimeId = mimeId;
    }

    @JsonProperty("url")
    public URL getUrl() {
        return url;
    }

    @JsonProperty("url")
    public void setUrl(URL url) {
        this.url = url;
    }

    @JsonProperty("state_link")
    public URL getStateLink() {
        return stateLink;
    }

    @JsonProperty("state_link")
    public void setStateLink(URL stateLink) {
        this.stateLink = stateLink;
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