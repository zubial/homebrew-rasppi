package com.homebrew.rest.type;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;
import java.io.Serializable;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ErrorType", propOrder = {
        "status",
        "code",
        "message",
        "developerMessage"
})
public class ErrorType
        implements Serializable
{

    private final static long serialVersionUID = 1L;

    private Integer status;
    private String code;
    private String message;
    private String developerMessage;

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getDeveloperMessage() {
        return developerMessage;
    }

    public void setDeveloperMessage(String developerMessage) {
        this.developerMessage = developerMessage;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ErrorType errorType = (ErrorType) o;

        if (status != null ? !status.equals(errorType.status) : errorType.status != null) return false;
        if (code != null ? !code.equals(errorType.code) : errorType.code != null) return false;
        if (message != null ? !message.equals(errorType.message) : errorType.message != null) return false;
        return developerMessage != null ? developerMessage.equals(errorType.developerMessage) : errorType.developerMessage == null;
    }

    @Override
    public int hashCode() {
        int result = status != null ? status.hashCode() : 0;
        result = 31 * result + (code != null ? code.hashCode() : 0);
        result = 31 * result + (message != null ? message.hashCode() : 0);
        result = 31 * result + (developerMessage != null ? developerMessage.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "ErrorType{" +
                "status=" + status +
                ", code='" + code + '\'' +
                ", message='" + message + '\'' +
                ", developerMessage='" + developerMessage + '\'' +
                '}';
    }
}
