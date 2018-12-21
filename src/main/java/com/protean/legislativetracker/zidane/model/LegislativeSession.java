
package com.protean.legislativetracker.zidane.model;

import com.fasterxml.jackson.annotation.*;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "session_id",
        "state_id",
        "session_name",
        "session_title",
        "year_start",
        "year_end",
        "special"
})
public class LegislativeSession {

    @JsonProperty("session_id")
    private Integer sessionId;
    @JsonProperty("state_id")
    private Integer stateId;
    @JsonProperty("session_name")
    private String sessionName;
    @JsonProperty("name")
    private String sessionTitle;
    @JsonProperty("year_start")
    private Integer yearStart;
    @JsonProperty("year_end")
    private Integer yearEnd;
    @JsonProperty("special")
    private Integer special;
    @JsonProperty("session_hash")
    private String sessionHash;

    /**
     * No args constructor for use in serialization
     */
    public LegislativeSession() {
    }

    /**
     * @param sessionId
     * @param stateId
     * @param sessionName
     * @param sessionTitle
     * @param yearStart
     * @param yearEnd
     * @param special
     * @param sessionHash
     */
    public LegislativeSession(Integer sessionId, Integer stateId, String sessionName, String sessionTitle, Integer yearStart, Integer yearEnd, Integer special, String sessionHash) {
        super();
        this.sessionId = sessionId;
        this.sessionName = sessionName;
        this.sessionTitle = sessionTitle;
        this.yearStart = yearStart;
        this.yearEnd = yearEnd;
        this.special = special;
        this.sessionHash = sessionHash;
    }

    @JsonProperty("session_id")
    public Integer getSessionId() {
        return sessionId;
    }

    @JsonProperty("session_id")
    public void setSessionId(Integer sessionId) {
        this.sessionId = sessionId;
    }

    @JsonProperty("state_id")
    public Integer getStateId() {
        return stateId;
    }

    public void setStateId(Integer stateId) {
        this.stateId = stateId;
    }

    @JsonProperty("session_name")
    public String getSessionName() {
        return sessionName;
    }

    @JsonProperty("session_name")
    public void setSessionName(String sessionName) {
        this.sessionName = sessionName;
    }

    @JsonProperty("session_title")
    public String getSessionTitle() {
        return sessionTitle;
    }

    @JsonProperty("session_title")
    public void setSessionTitle(String sessionTitle) {
        this.sessionTitle = sessionTitle;
    }

    @JsonProperty("year_start")
    public Integer getYearStart() {
        return yearStart;
    }

    @JsonProperty("year_start")
    public void setYearStart(Integer yearStart) {
        this.yearStart = yearStart;
    }

    @JsonProperty("year_end")
    public Integer getYearEnd() {
        return yearEnd;
    }

    @JsonProperty("year_end")
    public void setYearEnd(Integer yearEnd) {
        this.yearEnd = yearEnd;
    }

    @JsonProperty("special")
    public Integer getSpecial() {
        return special;
    }

    @JsonProperty("special")
    public void setSpecial(Integer special) {
        this.special = special;
    }

    public String getSessionHash() {
        return sessionHash;
    }

    public void setSessionHash(String sessionHash) {
        this.sessionHash = sessionHash;
    }

}
