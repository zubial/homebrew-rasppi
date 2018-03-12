package com.homebrew.io.managers.impl;

import com.homebrew.enums.ErrorCodeEnum;
import com.homebrew.exception.BaseException;
import com.homebrew.exception.TimerException;
import com.homebrew.io.managers.ISocketIOManager;
import com.homebrew.io.managers.ITimerManager;
import com.homebrew.io.models.TimerEventModel;
import org.apache.commons.lang3.time.DateUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Observable;
import java.util.Timer;
import java.util.TimerTask;

@Service
public class TimerManager extends Observable implements ITimerManager {

    private static final Logger LOGGER = Logger.getLogger(TimerManager.class);

    private Timer timer;
    private Timer stateTimer;

    private Integer pauseCountdown;
    private TimerTask timerTask;

    private String timerStepName;
    private Date timerStartDate;

    @Autowired
    ISocketIOManager socketIOManager;

    class StateTask extends TimerTask{

        @Override
        public void run() {
            TimerEventModel eventModel = new TimerEventModel();
            eventModel.setTimerStepName(timerStepName);
            eventModel.setTimerStartDate(timerStartDate);
            if (timerStartDate != null) {
                eventModel.setTimerEstimateEndDate(DateUtils.addSeconds(new Date(), getScheduledCountdown()));
            }
            eventModel.setTimerCountdown(getScheduledCountdown());

            try {
                socketIOManager.getTimerNamespace().getBroadcastOperations().sendEvent("message", eventModel);
            } catch (BaseException e) {
                LOGGER.error(e);
            }
        }
    }

    class NotifyOnceTask extends TimerTask{

        @Override
        public void run() {
            setChanged();
            notifyObservers();
            stop();
        }
    }

    public void createStep(String stepName) throws BaseException {
        try{
            timerStepName = stepName;
            timerStartDate = new Date();

        } catch (Exception e) {
            throw new TimerException(ErrorCodeEnum.TIMER_INIT_FAILED, e);
        }
    }

    public void scheduleOnce(Integer secondes) throws BaseException {
        try{
            if (timer != null) {
                timer.cancel();
            }
            if (stateTimer != null) {
                stateTimer.cancel();
            }
            timer = new Timer();
            stateTimer = new Timer();

            timerTask = new NotifyOnceTask();
            timer.schedule(timerTask, secondes*1000);
            stateTimer.schedule(new StateTask(), 0,1*1000);

        } catch (Exception e) {
            throw new TimerException(ErrorCodeEnum.TIMER_INIT_FAILED, e);
        }
    }

    public void pause() throws BaseException {
        if (timer != null) {
            pauseCountdown = getScheduledCountdown();

            timer.cancel();
            stateTimer.cancel();
        }
    }

    public void resume() throws BaseException {
        if (pauseCountdown != null) {
            scheduleOnce(pauseCountdown);
            pauseCountdown = null;
        }
    }

    public Integer getScheduledCountdown() {
        if (timerTask != null) {
            return (int)(timerTask.scheduledExecutionTime() - new Date().getTime()) / 1000;
        }
        return 0;
    }

    public void stop() {
        if(timer != null) {
            timer.cancel();
        }
        if (stateTimer != null) {
            stateTimer.cancel();
        }
        timer = null;
        stateTimer = null;
    }
}
