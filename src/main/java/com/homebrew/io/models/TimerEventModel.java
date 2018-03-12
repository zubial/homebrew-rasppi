package com.homebrew.io.models;


import java.io.Serializable;
import java.util.Date;

public class TimerEventModel implements Serializable {

    private final static long serialVersionUID = 1L;

    private Date timerStartDate;
    private Date timerEstimateEndDate;
    private String timerStepName;
    private Integer timerCountdown;

    public TimerEventModel() {
        // Default constructor
    }

    public Date getTimerStartDate() {
        return timerStartDate;
    }

    public void setTimerStartDate(Date timerStartDate) {
        this.timerStartDate = timerStartDate;
    }

    public Date getTimerEstimateEndDate() {
        return timerEstimateEndDate;
    }

    public void setTimerEstimateEndDate(Date timerEstimateEndDate) {
        this.timerEstimateEndDate = timerEstimateEndDate;
    }

    public String getTimerStepName() {
        return timerStepName;
    }

    public void setTimerStepName(String timerStepName) {
        this.timerStepName = timerStepName;
    }

    public Integer getTimerCountdown() {
        return timerCountdown;
    }

    public void setTimerCountdown(Integer timerCountdown) {
        this.timerCountdown = timerCountdown;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TimerEventModel that = (TimerEventModel) o;

        if (timerStartDate != null ? !timerStartDate.equals(that.timerStartDate) : that.timerStartDate != null)
            return false;
        if (timerEstimateEndDate != null ? !timerEstimateEndDate.equals(that.timerEstimateEndDate) : that.timerEstimateEndDate != null)
            return false;
        if (timerStepName != null ? !timerStepName.equals(that.timerStepName) : that.timerStepName != null)
            return false;
        return timerCountdown != null ? timerCountdown.equals(that.timerCountdown) : that.timerCountdown == null;
    }

    @Override
    public int hashCode() {
        int result = timerStartDate != null ? timerStartDate.hashCode() : 0;
        result = 31 * result + (timerEstimateEndDate != null ? timerEstimateEndDate.hashCode() : 0);
        result = 31 * result + (timerStepName != null ? timerStepName.hashCode() : 0);
        result = 31 * result + (timerCountdown != null ? timerCountdown.hashCode() : 0);
        return result;
    }
}
