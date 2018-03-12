package com.homebrew.io.listeners;

import com.homebrew.exception.BaseException;
import com.homebrew.io.managers.ISocketIOManager;
import com.homebrew.io.models.GpioEventModel;
import com.pi4j.io.gpio.event.GpioPinDigitalStateChangeEvent;
import com.pi4j.io.gpio.event.GpioPinListenerDigital;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

import java.util.Date;

public class GpioDigitalSocketIOEventListener implements GpioPinListenerDigital {

    private static final Logger LOGGER = Logger.getLogger(GpioDigitalSocketIOEventListener.class);

    @Autowired
    private ISocketIOManager socketIOManager;

    public GpioDigitalSocketIOEventListener() {
        super();

        // Init method
        SpringBeanAutowiringSupport.processInjectionBasedOnCurrentContext(this);
    }

    @Override
    public void handleGpioPinDigitalStateChangeEvent(GpioPinDigitalStateChangeEvent event) {
        // display pin state on console
        System.out.println(" SOCKET : " + event.getPin() + " = "
                + event.getState() + " ");

        GpioEventModel eventModel = new GpioEventModel();
        eventModel.setPinProvider(event.getPin().getPin().getProvider());
        eventModel.setPinIndex(event.getPin().getPin().getAddress());
        eventModel.setPinName(event.getPin().getName());
        eventModel.setEventDate(new Date());
        eventModel.setEventValue(event.getState().getValue());

        try {
            socketIOManager.getDigitalOutputNamespace().getBroadcastOperations().sendEvent("message", eventModel);
        } catch (BaseException e) {
            LOGGER.error(e);
        }
    }
}