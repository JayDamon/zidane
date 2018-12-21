
package com.protean.legislativetracker.zidane.model;

import com.fasterxml.jackson.annotation.*;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "date",
        "action",
        "chamber",
        "chamber_id",
        "importance"
})
public class History {

    @JsonProperty("date")
    private Date date;
    @JsonProperty("action")
    private String action;
    @JsonProperty("chamber")
    private String bodyShort;
    @JsonProperty("chamber_id")
    private Integer bodyId;
    @JsonProperty("importance")
    private Integer historyMajor;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<>();

    /**
     * No args constructor for use in serialization
     */
    public History() {
    }

    /**
     * @param historyMajor
     * @param action
     * @param bodyId
     * @param date
     * @param bodyShort
     */
    public History(Date date, String action, String bodyShort, Integer bodyId, Integer historyMajor) {
        super();
        this.date = date;
        this.action = action;
        this.bodyShort = bodyShort;
        this.bodyId = bodyId;
        this.historyMajor = historyMajor;
    }

    @JsonProperty("date")
    public Date getDate() {
        return date;
    }

    @JsonProperty("date")
    public void setDate(Date date) {
        this.date = date;
    }

    @JsonProperty("action")
    public String getAction() {
        return action;
    }

    @JsonProperty("action")
    public void setAction(String action) {
        this.action = action;
    }

    @JsonProperty("chamber")
    public String getBodyShort() {
        return bodyShort;
    }

    @JsonProperty("chamber")
    public void setBodyShort(String bodyShort) {
        this.bodyShort = bodyShort;
    }

    @JsonProperty("chamber_id")
    public Integer getBodyId() {
        return bodyId;
    }

    @JsonProperty("chamber_id")
    public void setBodyId(Integer bodyId) {
        this.bodyId = bodyId;
    }

    @JsonProperty("importance")
    public Integer getHistoryMajor() {
        return historyMajor;
    }

    @JsonProperty("importance")
    public void setHistoryMajor(Integer historyMajor) {
        this.historyMajor = historyMajor;
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
