package com.homebrew.service.gpio.impl;

import com.homebrew.enums.GpioPinTypeEnum;
import com.homebrew.exception.BaseException;
import com.homebrew.io.managers.IGpioImpulseManager;
import com.homebrew.rest.type.CmdGetAllPinResponseType;
import com.homebrew.rest.type.GpioPinType;
import com.homebrew.service.gpio.IGpioGetAllDigitalImpulseValue;
import com.pi4j.io.gpio.GpioPinDigitalInput;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GpioGetAllDigitalImpulseValue implements IGpioGetAllDigitalImpulseValue {

    private static final Logger LOGGER = Logger.getLogger(GpioGetAllDigitalImpulseValue.class);

    @Autowired
    private IGpioImpulseManager gpioManager;

    @Override
    public CmdGetAllPinResponseType process() throws BaseException {

        CmdGetAllPinResponseType response = new CmdGetAllPinResponseType();

        for (GpioPinDigitalInput pin : gpioManager.getAllPin()) {
            GpioPinType pinType = new GpioPinType();
            pinType.setPinIndex(pin.getPin().getAddress());
            pinType.setPinName(pin.getName());
            pinType.setPinType(GpioPinTypeEnum.DIGITAL_INPUT.label());
            pinType.setCurrentValue(gpioManager.getPinCount(pin.getPin().getAddress()));
            response.getAllPins().add(pinType);
        }

        response.setDone(true);

        return response;
    }
}
