package com.protean.legislativetracker.zidane.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "committee_id",
        "chamber",
        "chamber_id",
        "name"
})
public class Committee {

    @JsonProperty("committee_id")
    private Integer id;

    @JsonProperty("chamber")
    private Character bodyAbbreviation;

    @JsonProperty("chamber_id")
    private Integer bodyId;

    @JsonProperty("name")
    private String name;

    /**
     * No args constructor for use in serialization
     */
    public Committee() {
    }

    /**
     *
     * @param id
     * @param bodyAbbreviation
     * @param bodyId
     * @param name
     */
    public Committee(Integer id, Character bodyAbbreviation, Integer bodyId, String name) {
        this.id = id;
        this.bodyAbbreviation = bodyAbbreviation;
        this.bodyId = bodyId;
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Character getBodyAbbreviation() {
        return bodyAbbreviation;
    }

    public void setBodyAbbreviation(Character bodyAbbreviation) {
        this.bodyAbbreviation = bodyAbbreviation;
    }

    public Integer getBodyId() {
        return bodyId;
    }

    public void setBodyId(Integer bodyId) {
        this.bodyId = bodyId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
