
package com.protean.legislativetracker.zidane.model;

import com.fasterxml.jackson.annotation.*;

import java.sql.Time;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "type_id",
        "type",
        "date",
        "time",
        "location",
        "description"
})
public class Calendar {

    @JsonProperty("type_id")
    private Integer eventTypeId;
    @JsonProperty("type")
    private String eventTypeDescription;
    @JsonProperty("date")
    private Date date;
    @JsonProperty("time")
    private Time time;
    @JsonProperty("location")
    private String location;
    @JsonProperty("description")
    private String description;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<>();

    /**
     * No args constructor for use in serialization
     */
    public Calendar() {
    }

    /**
     * @param time
     * @param location
     * @param description
     * @param date
     * @param eventTypeDescription
     * @param eventTypeId
     */
    public Calendar(Integer eventTypeId, String eventTypeDescription, Date date, Time time, String location, String description) {
        super();
        this.eventTypeId = eventTypeId;
        this.eventTypeDescription = eventTypeDescription;
        this.date = date;
        this.time = time;
        this.location = location;
        this.description = description;
    }

    @JsonProperty("type_id")
    public Integer getEventTypeId() {
        return eventTypeId;
    }

    @JsonProperty("type_id")
    public void setEventTypeId(Integer eventTypeId) {
        this.eventTypeId = eventTypeId;
    }

    @JsonProperty("type")
    public String getEventTypeDescription() {
        return eventTypeDescription;
    }

    @JsonProperty("type")
    public void setEventTypeDescription(String eventTypeDescription) {
        this.eventTypeDescription = eventTypeDescription;
    }

    @JsonProperty("date")
    public Date getDate() {
        return date;
    }

    @JsonProperty("date")
    public void setDate(Date date) {
        this.date = date;
    }

    @JsonProperty("time")
    public Time getTime() {
        return time;
    }

    @JsonProperty("time")
    public void setTime(String time) {

        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
        try {
            long ms = sdf.parse(time).getTime();
            this.time = new Time(ms);
        } catch (ParseException e) {
            e.printStackTrace();
            this.time = null;
        }
    }

    @JsonProperty("location")
    public String getLocation() {
        return location;
    }

    @JsonProperty("location")
    public void setLocation(String location) {
        this.location = location;
    }

    @JsonProperty("description")
    public String getDescription() {
        return description;
    }

    @JsonProperty("description")
    public void setDescription(String description) {
        this.description = description;
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
