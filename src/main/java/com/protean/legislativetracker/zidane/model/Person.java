package com.protean.legislativetracker.zidane.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "people_id",
        "person_hash",
        "state_id",
        "party_id",
        "party",
        "role_id",
        "role",
        "name",
        "first_name",
        "middle_name",
        "last_name",
        "suffix",
        "nickname",
        "district",
        "ftm_eid",
        "votesmart_id",
        "opensecrets_id",
        "ballotpedia",
        "committee_sponsor",
        "committee_id"
})
public class Person {

    @JsonProperty("people_id")
    private Long id;
    @JsonProperty("person_hash")
    private String personHash;
    @JsonProperty("state_id")
    private Integer stateId;
    @JsonProperty("party_id")
    private String partyId;
    @JsonProperty("party")
    private String partyAbbreviation;
    @JsonProperty("role_id")
    private Integer roleId;
    @JsonProperty("role")
    private String roleAbbreviation;
    @JsonProperty("name")
    private String name;
    @JsonProperty("first_name")
    private String firstName;
    @JsonProperty("middle_name")
    private String middleName;
    @JsonProperty("last_name")
    private String lastName;
    @JsonProperty("suffix")
    private String suffix;
    @JsonProperty("nickname")
    private String nickname;
    @JsonProperty("district")
    private String district;
    @JsonProperty("ftm_eid")
    private String followTheMoneyId;
    @JsonProperty("votesmart_id")
    private Integer votesmartId;
    @JsonProperty("opensecrets_id")
    private String opensecretsId;
    @JsonProperty("ballotpedia")
    private String ballotpedia;
    @JsonProperty("sponsor_type_id")
    private Integer sponsorTypeId;
    @JsonProperty("sponsor_order")
    private Integer idSponsorOrder;
    @JsonProperty("committee_sponsor")
    private Integer committeeSponsor;
    @JsonProperty("committee_id")
    private Integer committeeId;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    /**
     * No args constructor for use in serialization
     *
     */
    public Person() {
    }

    /**
     * @param id
     * @param personHash
     * @param stateId
     * @param partyId
     * @param partyAbbreviation
     * @param roleId
     * @param roleAbbreviation
     * @param name
     * @param firstName
     * @param middleName
     * @param lastName
     * @param suffix
     * @param nickname
     * @param district
     * @param followTheMoneyId
     * @param votesmartId
     * @param opensecretsId
     * @param ballotpedia
     * @param sponsorTypeId
     * @param idSponsorOrder
     * @param committeeSponsor
     * @param committeeId
     */
    public Person(Long id, String personHash, Integer stateId, String partyId, String partyAbbreviation, Integer roleId, String roleAbbreviation, String name, String firstName, String middleName, String lastName, String suffix, String nickname, String district, String followTheMoneyId, Integer votesmartId, String opensecretsId, String ballotpedia, Integer sponsorTypeId, Integer idSponsorOrder, Integer committeeSponsor, Integer committeeId) {
        this.id = id;
        this.personHash = personHash;
        this.stateId = stateId;
        this.partyId = partyId;
        this.partyAbbreviation = partyAbbreviation;
        this.roleId = roleId;
        this.roleAbbreviation = roleAbbreviation;
        this.name = name;
        this.firstName = firstName;
        this.middleName = middleName;
        this.lastName = lastName;
        this.suffix = suffix;
        this.nickname = nickname;
        this.district = district;
        this.followTheMoneyId = followTheMoneyId;
        this.votesmartId = votesmartId;
        this.opensecretsId = opensecretsId;
        this.ballotpedia = ballotpedia;
        this.sponsorTypeId = sponsorTypeId;
        this.idSponsorOrder = idSponsorOrder;
        this.committeeSponsor = committeeSponsor;
        this.committeeId = committeeId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPersonHash() {
        return personHash;
    }

    public void setPersonHash(String personHash) {
        this.personHash = personHash;
    }

    public Integer getStateId() {
        return stateId;
    }

    public void setStateId(Integer stateId) {
        this.stateId = stateId;
    }

    public String getPartyId() {
        return partyId;
    }

    public void setPartyId(String partyId) {
        this.partyId = partyId;
    }

    public String getPartyAbbreviation() {
        return partyAbbreviation;
    }

    public void setPartyAbbreviation(String partyAbbreviation) {
        this.partyAbbreviation = partyAbbreviation;
    }

    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

    public String getRoleAbbreviation() {
        return roleAbbreviation;
    }

    public void setRoleAbbreviation(String roleAbbreviation) {
        this.roleAbbreviation = roleAbbreviation;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getSuffix() {
        return suffix;
    }

    public void setSuffix(String suffix) {
        this.suffix = suffix;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public String getFollowTheMoneyId() {
        return followTheMoneyId;
    }

    public void setFollowTheMoneyId(String followTheMoneyId) {
        this.followTheMoneyId = followTheMoneyId;
    }

    public Integer getVotesmartId() {
        return votesmartId;
    }

    public void setVotesmartId(Integer votesmartId) {
        this.votesmartId = votesmartId;
    }

    public String getOpensecretsId() {
        return opensecretsId;
    }

    public void setOpensecretsId(String opensecretsId) {
        this.opensecretsId = opensecretsId;
    }

    public String getBallotpedia() {
        return ballotpedia;
    }

    public void setBallotpedia(String ballotpedia) {
        this.ballotpedia = ballotpedia;
    }

    public Integer getSponsorTypeId() {
        return sponsorTypeId;
    }

    public void setSponsorTypeId(Integer sponsorTypeId) {
        this.sponsorTypeId = sponsorTypeId;
    }

    public Integer getIdSponsorOrder() {
        return idSponsorOrder;
    }

    public void setIdSponsorOrder(Integer idSponsorOrder) {
        this.idSponsorOrder = idSponsorOrder;
    }

    public Integer getCommitteeSponsor() {
        return committeeSponsor;
    }

    public void setCommitteeSponsor(Integer committeeSponsor) {
        this.committeeSponsor = committeeSponsor;
    }

    public Integer getCommitteeId() {
        return committeeId;
    }

    public void setCommitteeId(Integer committeeId) {
        this.committeeId = committeeId;
    }

    public Map<String, Object> getAdditionalProperties() {
        return additionalProperties;
    }

    public void setAdditionalProperties(Map<String, Object> additionalProperties) {
        this.additionalProperties = additionalProperties;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Person person = (Person) o;
        return Objects.equals(id, person.id) &&
                Objects.equals(personHash, person.personHash) &&
                Objects.equals(stateId, person.stateId) &&
                Objects.equals(partyId, person.partyId) &&
                Objects.equals(partyAbbreviation, person.partyAbbreviation) &&
                Objects.equals(roleId, person.roleId) &&
                Objects.equals(roleAbbreviation, person.roleAbbreviation) &&
                Objects.equals(name, person.name) &&
                Objects.equals(firstName, person.firstName) &&
                Objects.equals(middleName, person.middleName) &&
                Objects.equals(lastName, person.lastName) &&
                Objects.equals(suffix, person.suffix) &&
                Objects.equals(nickname, person.nickname) &&
                Objects.equals(district, person.district) &&
                Objects.equals(followTheMoneyId, person.followTheMoneyId) &&
                Objects.equals(votesmartId, person.votesmartId) &&
                Objects.equals(opensecretsId, person.opensecretsId) &&
                Objects.equals(ballotpedia, person.ballotpedia) &&
                Objects.equals(sponsorTypeId, person.sponsorTypeId) &&
                Objects.equals(idSponsorOrder, person.idSponsorOrder) &&
                Objects.equals(committeeSponsor, person.committeeSponsor) &&
                Objects.equals(committeeId, person.committeeId) &&
                Objects.equals(additionalProperties, person.additionalProperties);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, personHash, stateId, partyId, partyAbbreviation, roleId, roleAbbreviation, name, firstName, middleName, lastName, suffix, nickname, district, followTheMoneyId, votesmartId, opensecretsId, ballotpedia, sponsorTypeId, idSponsorOrder, committeeSponsor, committeeId, additionalProperties);
    }

    @Override
    public String toString() {
        return "Person{" +
                "id=" + id +
                ", personHash='" + personHash + '\'' +
                ", stateId=" + stateId +
                ", partyId='" + partyId + '\'' +
                ", partyAbbreviation='" + partyAbbreviation + '\'' +
                ", roleId=" + roleId +
                ", roleAbbreviation='" + roleAbbreviation + '\'' +
                ", name='" + name + '\'' +
                ", firstName='" + firstName + '\'' +
                ", middleName='" + middleName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", suffix='" + suffix + '\'' +
                ", nickname='" + nickname + '\'' +
                ", district='" + district + '\'' +
                ", followTheMoneyId='" + followTheMoneyId + '\'' +
                ", votesmartId=" + votesmartId +
                ", opensecretsId='" + opensecretsId + '\'' +
                ", ballotpedia='" + ballotpedia + '\'' +
                ", sponsorTypeId=" + sponsorTypeId +
                ", idSponsorOrder=" + idSponsorOrder +
                ", committeeSponsor=" + committeeSponsor +
                ", committeeId=" + committeeId +
                ", additionalProperties=" + additionalProperties +
                '}';
    }
}
