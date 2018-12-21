package com.protean.legislativetracker.zidane.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import java.util.HashMap;
import java.util.Map;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "people_id",
        "vote_id",
        "vote_text"
})
public class Vote {

    @JsonProperty("people_id")
    private Integer peopleId;
    @JsonProperty("vote_id")
    private Integer voteId;
    @JsonProperty("vote_text")
    private String voteText;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<>();

    public Vote() {
    }

    public Vote(Integer peopleId, Integer voteId, String voteText, Map<String, Object> additionalProperties) {
        this.peopleId = peopleId;
        this.voteId = voteId;
        this.voteText = voteText;
        this.additionalProperties = additionalProperties;
    }

    public Integer getPeopleId() {
        return peopleId;
    }

    public void setPeopleId(Integer peopleId) {
        this.peopleId = peopleId;
    }

    public Integer getVoteId() {
        return voteId;
    }

    public void setVoteId(Integer voteId) {
        this.voteId = voteId;
    }

    public String getVoteText() {
        return voteText;
    }

    public void setVoteText(String voteText) {
        this.voteText = voteText;
    }

    public Map<String, Object> getAdditionalProperties() {
        return additionalProperties;
    }

    public void setAdditionalProperties(Map<String, Object> additionalProperties) {
        this.additionalProperties = additionalProperties;
    }
}
