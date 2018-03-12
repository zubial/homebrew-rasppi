package com.homebrew.service.timer.impl;

import com.homebrew.exception.BaseException;
import com.homebrew.io.managers.ITimerManager;
import com.homebrew.rest.type.ResponseType;
import com.homebrew.service.timer.ITimerPause;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TimerPause implements ITimerPause {

    private static final Logger LOGGER = Logger.getLogger(TimerPause.class);

    @Autowired
    private ITimerManager timerManager;

    @Override
    public ResponseType process() throws BaseException {

        timerManager.pause();

        ResponseType response = new ResponseType();
        response.setDone(true);
        response.setMessage("Timer pause");

        return response;
    }
}
