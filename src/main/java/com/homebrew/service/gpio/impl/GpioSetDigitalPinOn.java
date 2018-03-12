package com.homebrew.service.gpio.impl;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.homebrew.enums.ErrorCodeEnum;
import com.homebrew.exception.BaseException;
import com.homebrew.exception.ServiceException;
import com.homebrew.io.managers.IGpioDigitalManager;
import com.homebrew.service.gpio.IGpioSetDigitalPinOn;
import com.homebrew.rest.type.ResponseType;
import com.pi4j.io.gpio.GpioPinDigitalOutput;
import com.sun.istack.NotNull;

@Service
public class GpioSetDigitalPinOn implements IGpioSetDigitalPinOn {

    private static final Logger LOGGER = Logger.getLogger(GpioSetDigitalPinOn.class);

    @Autowired
    private IGpioDigitalManager gpioManager;

    @Override
    public ResponseType process(@NotNull Integer pinIndex) throws BaseException {

        GpioPinDigitalOutput pin = gpioManager.getPin(pinIndex);

        if (pin == null) {
            throw new ServiceException(ErrorCodeEnum.COMMON_PARAM_MISSING, "Pin index not found");
        }

        pin.high();

        ResponseType response = new ResponseType();
        response.setDone(true);
        response.setMessage("Pin" + pinIndex + " should be: ON");

        return response;
    }
}
