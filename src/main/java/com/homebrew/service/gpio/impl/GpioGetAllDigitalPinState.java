package com.homebrew.service.gpio.impl;

import com.homebrew.enums.GpioPinTypeEnum;
import com.homebrew.exception.BaseException;
import com.homebrew.io.managers.IGpioDigitalManager;
import com.homebrew.rest.type.CmdGetAllPinResponseType;
import com.homebrew.rest.type.GpioPinType;
import com.homebrew.service.gpio.IGpioGetAllDigitalPinState;
import com.pi4j.io.gpio.GpioPinDigitalOutput;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GpioGetAllDigitalPinState implements IGpioGetAllDigitalPinState {

    private static final Logger LOGGER = Logger.getLogger(GpioGetAllDigitalPinState.class);

    @Autowired
    private IGpioDigitalManager gpioManager;

    @Override
    public CmdGetAllPinResponseType process() throws BaseException {

        CmdGetAllPinResponseType response = new CmdGetAllPinResponseType();

        for (GpioPinDigitalOutput pin : gpioManager.getAllPin()) {
            GpioPinType pinType = new GpioPinType();
            pinType.setPinIndex(pin.getPin().getAddress());
            pinType.setPinName(pin.getName());
            pinType.setPinType(GpioPinTypeEnum.DIGITAL_OUTPUT.label());
            pinType.setCurrentValue(pin.getState().getValue());
            response.getAllPins().add(pinType);
        }

        response.setDone(true);

        return response;
    }
}
