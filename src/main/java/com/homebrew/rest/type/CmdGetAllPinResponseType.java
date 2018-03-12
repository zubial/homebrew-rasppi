package com.homebrew.rest.type;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "cmdGetAllPinResponseType", propOrder = {
		"done",
        "allPins"
})
public class CmdGetAllPinResponseType implements Serializable
{

	private final static long serialVersionUID = 1L;

	private Boolean done;
    private List<GpioPinType> allPins = new ArrayList<>();

	public Boolean isDone() {
		return done;
	}

	public void setDone(Boolean value) {
		this.done = value;
	}

    public List<GpioPinType> getAllPins() {
        return allPins;
    }

    public void setAllPins(List<GpioPinType> allPins) {
        this.allPins = allPins;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CmdGetAllPinResponseType that = (CmdGetAllPinResponseType) o;

        if (done != null ? !done.equals(that.done) : that.done != null) return false;
        return allPins != null ? allPins.equals(that.allPins) : that.allPins == null;
    }

    @Override
    public int hashCode() {
        int result = done != null ? done.hashCode() : 0;
        result = 31 * result + (allPins != null ? allPins.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "CmdGetAllPinResponseType{" +
                "done=" + done +
                ", allPins=" + allPins +
                '}';
    }
}