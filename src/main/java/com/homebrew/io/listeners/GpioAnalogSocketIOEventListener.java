package com.homebrew.io.listeners;

import com.homebrew.exception.BaseException;
import com.homebrew.io.managers.ISocketIOManager;
import com.homebrew.io.models.GpioEventModel;
import com.pi4j.io.gpio.event.GpioPinAnalogShortValueChangeEvent;
import com.pi4j.io.gpio.event.GpioPinListenerAnalogShort;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

import java.util.Date;

public class GpioAnalogSocketIOEventListener implements GpioPinListenerAnalogShort {

    private static final Logger LOGGER = Logger.getLogger(GpioDigitalLogsEventListener.class);

    @Autowired
    private ISocketIOManager socketIOManager;

    public GpioAnalogSocketIOEventListener() {
        super();

        // Init method
        SpringBeanAutowiringSupport.processInjectionBasedOnCurrentContext(this);
    }

    @Override
    public void handleGpioPinAnalogShortValueChangeEvent(GpioPinAnalogShortValueChangeEvent event) {
        // display pin state on console
        System.out.println(" SOCKET : " + event.getPin() + " = "
                + event.getValue() + " ");

        GpioEventModel eventModel = new GpioEventModel();
        eventModel.setPinProvider(event.getPin().getPin().getProvider());
        eventModel.setPinIndex(event.getPin().getPin().getAddress());
        eventModel.setPinName(event.getPin().getName());
        eventModel.setEventDate(new Date());
        eventModel.setEventValue(new Integer(event.getValue()));

        try {
            socketIOManager.getAnalogInputNamespace().getBroadcastOperations().sendEvent("message", eventModel);
        } catch (BaseException e) {
            LOGGER.error(e);
        }
    }
}