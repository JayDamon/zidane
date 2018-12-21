
package com.protean.legislativetracker.zidane.model;

import com.fasterxml.jackson.annotation.*;

import java.net.URL;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "roll_call_id",
        "bill_id",
        "date",
        "desc",
        "yea",
        "nay",
        "nv",
        "absent",
        "total",
        "passed",
        "chamber",
        "chamber_id",
        "url",
        "state_link",
        "votes"
})
public class RollCall {

    @JsonProperty("roll_call_id")
    private Long voteId;
    @JsonProperty("bill_id")
    private Integer billId;
    @JsonProperty("date")
    private Date billVoteDate;
    @JsonProperty("desc")
    private String description;
    @JsonProperty("yea")
    private Integer yea;
    @JsonProperty("nay")
    private Integer nay;
    @JsonProperty("nv")
    private Integer nv;
    @JsonProperty("absent")
    private Integer absent;
    @JsonProperty("total")
    private Integer total;
    @JsonProperty("passed")
    private Integer passed;
    @JsonProperty("chamber")
    private String bodyAbbreviation;
    @JsonProperty("chamber_id")
    private Integer bodyId;
    @JsonProperty("url")
    private URL legiscanUrl;
    @JsonProperty("state_link")
    private URL stateUrl;
    @JsonProperty("votes")
    private List<Vote> votes = null;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<>();

    /**
     * No args constructor for use in serialization
     */
    public RollCall() {
    }

    public RollCall(Long voteId, Integer billId, Date billVoteDate, String description, Integer yea, Integer nay, Integer nv, Integer absent, Integer total, Integer passed, String bodyAbbreviation, Integer bodyId, URL legiscanUrl, URL stateUrl, List<Vote> votes, Map<String, Object> additionalProperties) {
        this.voteId = voteId;
        this.billId = billId;
        this.billVoteDate = billVoteDate;
        this.description = description;
        this.yea = yea;
        this.nay = nay;
        this.nv = nv;
        this.absent = absent;
        this.total = total;
        this.passed = passed;
        this.bodyAbbreviation = bodyAbbreviation;
        this.bodyId = bodyId;
        this.legiscanUrl = legiscanUrl;
        this.stateUrl = stateUrl;
        this.votes = votes;
        this.additionalProperties = additionalProperties;
    }

    public Long getVoteId() {
        return voteId;
    }

    public void setVoteId(Long voteId) {
        this.voteId = voteId;
    }

    public Integer getBillId() {
        return billId;
    }

    public void setBillId(Integer billId) {
        this.billId = billId;
    }

    public Date getBillVoteDate() {
        return billVoteDate;
    }

    public void setBillVoteDate(Date billVoteDate) {
        this.billVoteDate = billVoteDate;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getYea() {
        return yea;
    }

    public void setYea(Integer yea) {
        this.yea = yea;
    }

    public Integer getNay() {
        return nay;
    }

    public void setNay(Integer nay) {
        this.nay = nay;
    }

    public Integer getNv() {
        return nv;
    }

    public void setNv(Integer nv) {
        this.nv = nv;
    }

    public Integer getAbsent() {
        return absent;
    }

    public void setAbsent(Integer absent) {
        this.absent = absent;
    }

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    public Integer getPassed() {
        return passed;
    }

    public void setPassed(Integer passed) {
        this.passed = passed;
    }

    public String getBodyAbbreviation() {
        return bodyAbbreviation;
    }

    public void setBodyAbbreviation(String bodyAbbreviation) {
        this.bodyAbbreviation = bodyAbbreviation;
    }

    public Integer getBodyId() {
        return bodyId;
    }

    public void setBodyId(Integer bodyId) {
        this.bodyId = bodyId;
    }

    public URL getLegiscanUrl() {
        return legiscanUrl;
    }

    public void setLegiscanUrl(URL legiscanUrl) {
        this.legiscanUrl = legiscanUrl;
    }

    public URL getStateUrl() {
        return stateUrl;
    }

    public void setStateUrl(URL stateUrl) {
        this.stateUrl = stateUrl;
    }

    public List<Vote> getVotes() {
        return votes;
    }

    public void setVotes(List<Vote> votes) {
        this.votes = votes;
    }

    public Map<String, Object> getAdditionalProperties() {
        return additionalProperties;
    }

    public void setAdditionalProperties(Map<String, Object> additionalProperties) {
        this.additionalProperties = additionalProperties;
    }

    @Override
    public String toString() {
        return "RollCall{" +
                "voteId=" + voteId +
                ", billId=" + billId +
                ", billVoteDate=" + billVoteDate +
                ", description='" + description + '\'' +
                ", yea=" + yea +
                ", nay=" + nay +
                ", nv=" + nv +
                ", absent=" + absent +
                ", total=" + total +
                ", passed=" + passed +
                ", bodyAbbreviation='" + bodyAbbreviation + '\'' +
                ", bodyId=" + bodyId +
                ", legiscanUrl=" + legiscanUrl +
                ", stateUrl=" + stateUrl +
                ", votes=" + votes +
                ", additionalProperties=" + additionalProperties +
                '}';
    }
}
