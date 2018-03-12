package com.homebrew.persistance.dto;


import javax.persistence.*;

@Entity
@Table(name = "gpio_config")
public class GpioConfigDto {

    public static final String FIELD_ID = "id";
    public static final String FIELD_CONFIG_NAME = "configName";
    public static final String FIELD_PIN_TYPE = "pinType";
    public static final String FIELD_PIN_INDEX = "pinIndex";
    public static final String FIELD_PIN_NAME = "pinName";
    public static final String FIELD_INITIAL_VALUE = "initialValue";
    public static final String FIELD_EVENT_TO_DB = "eventToDb";
    public static final String FIELD_EVENT_TO_SOCKET = "eventToSocket";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;
    @Column(name = "config_name", length = 100)
    private String configName;
    @Column(name = "pin_type", length = 100)
    private String pinType;
    @Column(name = "pin_index")
    private Integer pinIndex;
    @Column(name = "pin_name", length = 100)
    private String pinName;
    @Column(name = "initial_value")
    private Integer initialValue;
    @Column(name = "event_to_db")
    private Boolean eventToDb;
    @Column(name = "event_to_socket")
    private Boolean eventToSocket;

    public GpioConfigDto() {
        // Default constructor
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getConfigName() {
        return configName;
    }

    public void setConfigName(String configName) {
        this.configName = configName;
    }

    public String getPinType() {
        return pinType;
    }

    public void setPinType(String pinType) {
        this.pinType = pinType;
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

    public Integer getInitialValue() {
        return initialValue;
    }

    public void setInitialValue(Integer initialValue) {
        this.initialValue = initialValue;
    }

    public Boolean isEventToDb() {
        return eventToDb;
    }

    public void setEventToDb(Boolean eventToDb) {
        this.eventToDb = eventToDb;
    }

    public Boolean isEventToSocket() {
        return eventToSocket;
    }

    public void setEventToSocket(Boolean eventToSocket) {
        this.eventToSocket = eventToSocket;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        GpioConfigDto that = (GpioConfigDto) o;

        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (configName != null ? !configName.equals(that.configName) : that.configName != null) return false;
        if (pinType != null ? !pinType.equals(that.pinType) : that.pinType != null) return false;
        if (pinIndex != null ? !pinIndex.equals(that.pinIndex) : that.pinIndex != null) return false;
        if (pinName != null ? !pinName.equals(that.pinName) : that.pinName != null) return false;
        if (initialValue != null ? !initialValue.equals(that.initialValue) : that.initialValue != null) return false;
        if (eventToDb != null ? !eventToDb.equals(that.eventToDb) : that.eventToDb != null) return false;
        return eventToSocket != null ? eventToSocket.equals(that.eventToSocket) : that.eventToSocket == null;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (configName != null ? configName.hashCode() : 0);
        result = 31 * result + (pinType != null ? pinType.hashCode() : 0);
        result = 31 * result + (pinIndex != null ? pinIndex.hashCode() : 0);
        result = 31 * result + (pinName != null ? pinName.hashCode() : 0);
        result = 31 * result + (initialValue != null ? initialValue.hashCode() : 0);
        result = 31 * result + (eventToDb != null ? eventToDb.hashCode() : 0);
        result = 31 * result + (eventToSocket != null ? eventToSocket.hashCode() : 0);
        return result;
    }
}
