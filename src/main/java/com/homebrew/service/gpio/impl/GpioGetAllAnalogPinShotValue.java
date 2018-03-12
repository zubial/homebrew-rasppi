package com.homebrew.service.gpio.impl;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.homebrew.enums.GpioPinTypeEnum;
import com.homebrew.exception.BaseException;
import com.homebrew.io.managers.IGpioAnalogManager;
import com.homebrew.rest.type.CmdGetAllPinResponseType;
import com.homebrew.rest.type.GpioPinType;
import com.homebrew.service.gpio.IGpioGetAllAnalogPinShortValue;
import com.pi4j.io.gpio.GpioPinAnalogInput;

@Service
public class GpioGetAllAnalogPinShotValue implements IGpioGetAllAnalogPinShortValue {

    private static final Logger LOGGER = Logger.getLogger(GpioGetAllAnalogPinShotValue.class);

    @Autowired
    private IGpioAnalogManager gpioManager;

    @Override
    public CmdGetAllPinResponseType process() throws BaseException {

        CmdGetAllPinResponseType response = new CmdGetAllPinResponseType();

        for (GpioPinAnalogInput pin : gpioManager.getAllPin()) {
            GpioPinType pinType = new GpioPinType();
            pinType.setPinIndex(pin.getPin().getAddress());
            pinType.setPinName(pin.getName());
            pinType.setPinType(GpioPinTypeEnum.ANALOG_INPUT.label());
            pinType.setCurrentValue((int)pin.getValue());
            response.getAllPins().add(pinType);
        }

        response.setDone(true);

        return response;
    }
}
