
package com.protean.legislativetracker.zidane.model;

import com.fasterxml.jackson.annotation.*;

import java.net.URL;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "doc_id",
        "date",
        "type",
        "type_id",
        "mime",
        "mime_id",
        "url",
        "state_link",
        "text_size"
})
public class Text {

    @JsonProperty("doc_id")
    private Integer textId;
    @JsonProperty("date")
    private Date date;
    @JsonProperty("type")
    private String textTypeName;
    @JsonProperty("type_id")
    private Integer textTypeId;
    @JsonProperty("mime")
    private String mimeTypeName;
    @JsonProperty("mime_id")
    private Integer mimeTypeId;
    @JsonProperty("url")
    private URL legiscanUrl;
    @JsonProperty("state_link")
    private URL stateUrl;
    @JsonProperty("text_size")
    private Integer textSize;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<>();

    /**
     * No args constructor for use in serialization
     */
    public Text() {
    }

    /**
     * @param textSize
     * @param mimeTypeId
     * @param mimeTypeName
     * @param textId
     * @param textTypeName
     * @param date
     * @param textTypeId
     * @param stateUrl
     * @param legiscanUrl
     */
    public Text(Integer textId, Date date, String textTypeName, Integer textTypeId, String mimeTypeName, Integer mimeTypeId, URL legiscanUrl, URL stateUrl, Integer textSize) {
        super();
        this.textId = textId;
        this.date = date;
        this.textTypeName = textTypeName;
        this.textTypeId = textTypeId;
        this.mimeTypeName = mimeTypeName;
        this.mimeTypeId = mimeTypeId;
        this.legiscanUrl = legiscanUrl;
        this.stateUrl = stateUrl;
        this.textSize = textSize;
    }

    @JsonProperty("doc_id")
    public Integer getTextId() {
        return textId;
    }

    @JsonProperty("doc_id")
    public void setTextId(Integer textId) {
        this.textId = textId;
    }

    @JsonProperty("date")
    public Date getDate() {
        return date;
    }

    @JsonProperty("date")
    public void setDate(Date date) {
        this.date = date;
    }

    @JsonProperty("type")
    public String getTextTypeName() {
        return textTypeName;
    }

    @JsonProperty("type")
    public void setTextTypeName(String textTypeName) {
        this.textTypeName = textTypeName;
    }

    @JsonProperty("type_id")
    public Integer getTextTypeId() {
        return textTypeId;
    }

    @JsonProperty("type_id")
    public void setTextTypeId(Integer textTypeId) {
        this.textTypeId = textTypeId;
    }

    @JsonProperty("mime")
    public String getMimeTypeName() {
        return mimeTypeName;
    }

    @JsonProperty("mime")
    public void setMimeTypeName(String mimeTypeName) {
        this.mimeTypeName = mimeTypeName;
    }

    @JsonProperty("mime_id")
    public Integer getMimeTypeId() {
        return mimeTypeId;
    }

    @JsonProperty("mime_id")
    public void setMimeTypeId(Integer mimeTypeId) {
        this.mimeTypeId = mimeTypeId;
    }

    @JsonProperty("url")
    public URL getLegiscanUrl() {
        return legiscanUrl;
    }

    @JsonProperty("url")
    public void setLegiscanUrl(URL legiscanUrl) {
        this.legiscanUrl = legiscanUrl;
    }

    @JsonProperty("state_link")
    public URL getStateUrl() {
        return stateUrl;
    }

    @JsonProperty("state_link")
    public void setStateUrl(URL stateUrl) {
        this.stateUrl = stateUrl;
    }

    @JsonProperty("text_size")
    public Integer getTextSize() {
        return textSize;
    }

    @JsonProperty("text_size")
    public void setTextSize(Integer textSize) {
        this.textSize = textSize;
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
