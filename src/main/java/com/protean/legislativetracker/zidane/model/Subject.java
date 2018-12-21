
package com.protean.legislativetracker.zidane.model;

import com.fasterxml.jackson.annotation.*;

import java.util.HashMap;
import java.util.Map;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "subject_id",
        "subject_name"
})
public class Subject {

    @JsonProperty("subject_id")
    private Integer subjectId;
    @JsonProperty("subject_name")
    private String subjectName;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<>();

    /**
     * No args constructor for use in serialization
     */
    public Subject() {
    }

    /**
     * @param subjectName
     * @param subjectId
     */
    public Subject(Integer subjectId, String subjectName) {
        super();
        this.subjectId = subjectId;
        this.subjectName = subjectName;
    }

    @JsonProperty("subject_id")
    public Integer getSubjectId() {
        return subjectId;
    }

    @JsonProperty("subject_id")
    public void setSubjectId(Integer subjectId) {
        this.subjectId = subjectId;
    }

    @JsonProperty("subject_name")
    public String getSubjectName() {
        return subjectName;
    }

    @JsonProperty("subject_name")
    public void setSubjectName(String subjectName) {
        this.subjectName = subjectName;
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
