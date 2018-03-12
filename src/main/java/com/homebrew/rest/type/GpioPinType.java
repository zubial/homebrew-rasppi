package com.homebrew.rest.type;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;
import java.io.Serializable;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "gpioPinType", propOrder = {
        "pinType",
        "pinIndex",
        "pinName",
        "currentValue"
})
public class GpioPinType
        implements Serializable {

    private final static long serialVersionUID = 1L;

    private String pinType;
    private Integer pinIndex;
    private String pinName;
    private Integer currentValue;

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

    public Integer getCurrentValue() {
        return currentValue;
    }

    public void setCurrentValue(Integer currentValue) {
        this.currentValue = currentValue;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        GpioPinType that = (GpioPinType) o;

        if (pinType != null ? !pinType.equals(that.pinType) : that.pinType != null) return false;
        if (pinIndex != null ? !pinIndex.equals(that.pinIndex) : that.pinIndex != null) return false;
        if (pinName != null ? !pinName.equals(that.pinName) : that.pinName != null) return false;
        return currentValue != null ? currentValue.equals(that.currentValue) : that.currentValue == null;
    }

    @Override
    public int hashCode() {
        int result = pinType != null ? pinType.hashCode() : 0;
        result = 31 * result + (pinIndex != null ? pinIndex.hashCode() : 0);
        result = 31 * result + (pinName != null ? pinName.hashCode() : 0);
        result = 31 * result + (currentValue != null ? currentValue.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "GpioPinType{" +
                "pinType='" + pinType + '\'' +
                ", pinIndex=" + pinIndex +
                ", pinName='" + pinName + '\'' +
                ", currentValue=" + currentValue +
                '}';
    }
}
