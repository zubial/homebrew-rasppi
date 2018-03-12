package com.homebrew.service.gpio.impl;

import com.homebrew.enums.ErrorCodeEnum;
import com.homebrew.exception.BaseException;
import com.homebrew.exception.ServiceException;
import com.homebrew.io.managers.IGpioDigitalManager;
import com.homebrew.rest.type.ResponseType;
import com.homebrew.service.gpio.IGpioSetDigitalPinToggle;
import com.pi4j.io.gpio.GpioPinDigitalOutput;
import com.sun.istack.NotNull;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GpioSetDigitalPinToggle implements IGpioSetDigitalPinToggle {

    private static final Logger LOGGER = Logger.getLogger(GpioSetDigitalPinToggle.class);

    @Autowired
    private IGpioDigitalManager gpioManager;

    @Override
    public ResponseType process(@NotNull Integer pinIndex) throws BaseException {

        GpioPinDigitalOutput pin = gpioManager.getPin(pinIndex);
        if (pin == null) {
            throw new ServiceException(ErrorCodeEnum.COMMON_PARAM_MISSING, "Pin index not found");
        }

        pin.toggle();

        ResponseType response = new ResponseType();
        response.setDone(true);
        response.setMessage("Pin" + pinIndex + " should be: " + pin.getState().getName());

        return response;
    }
}
