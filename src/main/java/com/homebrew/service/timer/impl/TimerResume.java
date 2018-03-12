package com.homebrew.service.timer.impl;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.homebrew.exception.BaseException;
import com.homebrew.io.managers.ITimerManager;
import com.homebrew.rest.type.ResponseType;
import com.homebrew.service.timer.ITimerResume;

@Service
public class TimerResume implements ITimerResume {

    private static final Logger LOGGER = Logger.getLogger(TimerResume.class);

    @Autowired
    private ITimerManager timerManager;

    @Override
    public ResponseType process() throws BaseException {

        timerManager.resume();

        ResponseType response = new ResponseType();
        response.setDone(true);
        response.setMessage("Timer resume");

        return response;
    }
}
