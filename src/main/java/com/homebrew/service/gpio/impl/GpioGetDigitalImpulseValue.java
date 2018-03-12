package com.homebrew.service.gpio.impl;

import com.homebrew.enums.ErrorCodeEnum;
import com.homebrew.exception.BaseException;
import com.homebrew.exception.ServiceException;
import com.homebrew.io.managers.IGpioImpulseManager;
import com.homebrew.rest.type.ResponseType;
import com.homebrew.service.gpio.IGpioGetDigitalImpulseValue;
import com.pi4j.io.gpio.GpioPinDigitalInput;
import com.sun.istack.NotNull;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GpioGetDigitalImpulseValue implements IGpioGetDigitalImpulseValue {

    private static final Logger LOGGER = Logger.getLogger(GpioGetDigitalImpulseValue.class);

    @Autowired
    private IGpioImpulseManager gpioManager;

    @Override
    public ResponseType process(@NotNull Integer pinIndex) throws BaseException {

        GpioPinDigitalInput pin = gpioManager.getPin(pinIndex);

        if (pin == null) {
            throw new ServiceException(ErrorCodeEnum.COMMON_PARAM_MISSING, "Pin index not found");
        }

        ResponseType response = new ResponseType();
        response.setDone(true);
        response.setMessage("Pin" + pinIndex + " should be: " + gpioManager.getPinCount(pinIndex));

        return response;
    }
}
