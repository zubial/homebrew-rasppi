package com.homebrew.service.gpio.impl;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.homebrew.enums.ErrorCodeEnum;
import com.homebrew.exception.BaseException;
import com.homebrew.exception.ServiceException;
import com.homebrew.io.managers.IGpioImpulseManager;
import com.homebrew.rest.type.ResponseType;
import com.homebrew.service.gpio.IGpioResetDigitalImpulseValue;
import com.pi4j.io.gpio.GpioPinDigitalInput;
import com.sun.istack.NotNull;

@Service
public class GpioResetDigitalImpulseValue implements IGpioResetDigitalImpulseValue {

    private static final Logger LOGGER = Logger.getLogger(GpioResetDigitalImpulseValue.class);

    @Autowired
    private IGpioImpulseManager gpioManager;

    @Override
    public ResponseType process(@NotNull Integer pinIndex) throws BaseException {

        GpioPinDigitalInput pin = gpioManager.getPin(pinIndex);

        gpioManager.setPinCount(pinIndex, 0);

        if (pin == null) {
            throw new ServiceException(ErrorCodeEnum.COMMON_PARAM_MISSING, "Pin index not found");
        }

        ResponseType response = new ResponseType();
        response.setDone(true);
        response.setMessage("Pin" + pinIndex + " should be reset ");

        return response;
    }
}
