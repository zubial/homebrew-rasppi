package com.homebrew.service.gpio.impl;

import com.homebrew.enums.ErrorCodeEnum;
import com.homebrew.exception.BaseException;
import com.homebrew.exception.ServiceException;
import com.homebrew.io.managers.IGpioAnalogManager;
import com.homebrew.service.gpio.IGpioGetAnalogPinShortValue;
import com.homebrew.rest.type.ResponseType;
import com.pi4j.io.gpio.GpioPinAnalogInput;
import com.sun.istack.NotNull;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GpioGetAnalogPinShortValue implements IGpioGetAnalogPinShortValue {

    private static final Logger LOGGER = Logger.getLogger(GpioGetAnalogPinShortValue.class);

    @Autowired
    private IGpioAnalogManager gpioManager;

    @Override
    public ResponseType process(@NotNull Integer pinIndex) throws BaseException {

        GpioPinAnalogInput pin = gpioManager.getPin(pinIndex);

        if (pin == null) {
            throw new ServiceException(ErrorCodeEnum.COMMON_PARAM_MISSING, "Pin index not found");
        }

        short value = gpioManager.getGpioProvider().getShortValue(pin.getPin());

        ResponseType response = new ResponseType();
        response.setDone(true);
        response.setMessage("Pin " + pin.getName() + " should be : " + value + "  ");

        return response;
    }
}
