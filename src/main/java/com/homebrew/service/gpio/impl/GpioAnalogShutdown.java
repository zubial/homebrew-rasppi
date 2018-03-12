package com.homebrew.service.gpio.impl;

import com.homebrew.exception.BaseException;
import com.homebrew.io.managers.IGpioAnalogManager;
import com.homebrew.service.gpio.IGpioAnalogShutdown;
import com.homebrew.rest.type.ResponseType;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GpioAnalogShutdown implements IGpioAnalogShutdown {

    private static final Logger LOGGER = Logger.getLogger(GpioAnalogShutdown.class);

    @Autowired
    private IGpioAnalogManager gpioManager;

    @Override
    public ResponseType process() throws BaseException {

        gpioManager.shutdown();

        ResponseType response = new ResponseType();
        response.setDone(true);
        response.setMessage("Gpio Analog is shutdown");

        return response;
    }
}
