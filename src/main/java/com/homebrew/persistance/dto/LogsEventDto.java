package com.homebrew.persistance.dto;


import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "logs_event")
public class LogsEventDto {

    public static final String FIELD_ID = "id";
    public static final String FIELD_PIN_PROVIDER = "pinProvider";
    public static final String FIELD_PIN_INDEX = "pinIndex";
    public static final String FIELD_PIN_NAME = "pinName";
    public static final String FIELD_EVENT_VALUE = "eventValue";
    public static final String FIELD_EVENT_DATE = "eventDate";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;
    @Column(name = "pin_provider", length = 100)
    private String pinProvider;
    @Column(name = "pin_index")
    private Integer pinIndex;
    @Column(name = "pin_name", length = 100)
    private String pinName;
    @Column(name = "event_value")
    private Integer eventValue;
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "event_date")
    private Date eventDate;

    public LogsEventDto() {
        // Default constructor
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPinProvider() {
        return pinProvider;
    }

    public void setPinProvider(String pinProvider) {
        this.pinProvider = pinProvider;
    }

    public Integer getPinIndex() {
        return pinIndex;
    }

    public void setPinIndex(Integer pinIndex) {
        this.pinIndex = pinIndex;
    }

    public String getPinName() {
        return pinName;
    }

    public void setPinName(String pinName) {
        this.pinName = pinName;
    }

    public Integer getEventValue() {
        return eventValue;
    }

    public void setEventValue(Integer eventValue) {
        this.eventValue = eventValue;
    }

    public Date getEventDate() {
        return eventDate;
    }

    public void setEventDate(Date eventDate) {
        this.eventDate = eventDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        LogsEventDto that = (LogsEventDto) o;

        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (pinProvider != null ? !pinProvider.equals(that.pinProvider) : that.pinProvider != null) return false;
        if (pinIndex != null ? !pinIndex.equals(that.pinIndex) : that.pinIndex != null) return false;
        if (pinName != null ? !pinName.equals(that.pinName) : that.pinName != null) return false;
        if (eventValue != null ? !eventValue.equals(that.eventValue) : that.eventValue != null) return false;
        return eventDate != null ? eventDate.equals(that.eventDate) : that.eventDate == null;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (pinProvider != null ? pinProvider.hashCode() : 0);
        result = 31 * result + (pinIndex != null ? pinIndex.hashCode() : 0);
        result = 31 * result + (pinName != null ? pinName.hashCode() : 0);
        result = 31 * result + (eventValue != null ? eventValue.hashCode() : 0);
        result = 31 * result + (eventDate != null ? eventDate.hashCode() : 0);
        return result;
    }
}
