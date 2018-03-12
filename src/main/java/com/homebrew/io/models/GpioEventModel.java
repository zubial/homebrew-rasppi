package com.homebrew.io.models;


import java.io.Serializable;
import java.util.Date;

public class GpioEventModel implements Serializable {

    private final static long serialVersionUID = 1L;

    private Integer id;
    private String pinProvider;
    private Integer pinIndex;
    private String pinName;
    private Integer eventValue;
    private Date eventDate;

    public GpioEventModel() {
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

        GpioEventModel that = (GpioEventModel) o;

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
