package com.homebrew.service.gpio.impl;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.homebrew.exception.BaseException;
import com.homebrew.io.managers.IGpioDigitalManager;
import com.homebrew.service.gpio.IGpioDigitalShutdown;
import com.homebrew.rest.type.ResponseType;

@Service
public class GpioDigitalShutdown implements IGpioDigitalShutdown {

    private static final Logger LOGGER = Logger.getLogger(GpioDigitalShutdown.class);

    @Autowired
    private IGpioDigitalManager gpioManager;

    @Override
    public ResponseType process() throws BaseException {

        gpioManager.shutdown();

        ResponseType response = new ResponseType();
        response.setDone(true);
        response.setMessage("Gpio Digital is shutdown");

        return response;
    }
}
