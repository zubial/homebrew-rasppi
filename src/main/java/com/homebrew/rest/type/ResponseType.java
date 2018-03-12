package com.homebrew.rest.type;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;
import java.io.Serializable;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "responseType", propOrder = {
		"done",
        "message"
})
public class ResponseType implements Serializable
{

	private final static long serialVersionUID = 1L;

	private Boolean done;
    private String message;

	public Boolean isDone() {
		return done;
	}

	public void setDone(Boolean value) {
		this.done = value;
	}

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ResponseType that = (ResponseType) o;

        if (done != null ? !done.equals(that.done) : that.done != null) return false;
        return message != null ? message.equals(that.message) : that.message == null;
    }

    @Override
    public int hashCode() {
        int result = done != null ? done.hashCode() : 0;
        result = 31 * result + (message != null ? message.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "ResponseType{" +
                "done=" + done +
                ", message='" + message + '\'' +
                '}';
    }
}