package com.homebrew.service.timer.impl;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.homebrew.exception.BaseException;
import com.homebrew.io.managers.ITimerManager;
import com.homebrew.io.observers.ObserverTest;
import com.homebrew.rest.type.ResponseType;
import com.homebrew.service.timer.ITimerScheduleOnce;
import com.sun.istack.NotNull;

@Service
public class TimerScheduleOnce implements ITimerScheduleOnce {

    private static final Logger LOGGER = Logger.getLogger(TimerScheduleOnce.class);

    @Autowired
    private ITimerManager timerManager;

    @Override
    public ResponseType process(@NotNull Integer minutes) throws BaseException {

        ObserverTest observer1 = new ObserverTest();

        timerManager.addObserver(observer1);
        timerManager.createStep("Step 1");
        timerManager.scheduleOnce(minutes*60);

        ResponseType response = new ResponseType();
        response.setDone(true);
        response.setMessage("Timer scheduleOnce in " + minutes + " minutes");

        return response;
    }
}
