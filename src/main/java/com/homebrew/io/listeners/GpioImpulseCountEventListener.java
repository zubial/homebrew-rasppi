package com.homebrew.io.listeners;

import com.homebrew.exception.BaseException;
import com.homebrew.io.managers.IGpioImpulseManager;
import com.pi4j.io.gpio.event.GpioPinDigitalStateChangeEvent;
import com.pi4j.io.gpio.event.GpioPinListenerDigital;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

public class GpioImpulseCountEventListener implements GpioPinListenerDigital {

    private static final Logger LOGGER = Logger.getLogger(GpioImpulseCountEventListener.class);

    @Autowired
    private IGpioImpulseManager impluseManager;

    public GpioImpulseCountEventListener() {
        super();

        // Init method
        SpringBeanAutowiringSupport.processInjectionBasedOnCurrentContext(this);
    }

    @Override
    public void handleGpioPinDigitalStateChangeEvent(GpioPinDigitalStateChangeEvent event) {
        try {
            impluseManager.addPinCount(event.getPin().getPin().getAddress());

            // display pin state on console
            System.out.println(" COUNT : " + event.getPin() + " = "
                    + impluseManager.getPinCount(event.getPin().getPin().getAddress()) + " ");

        } catch (BaseException e) {
            LOGGER.error(e);
        }
    }
}