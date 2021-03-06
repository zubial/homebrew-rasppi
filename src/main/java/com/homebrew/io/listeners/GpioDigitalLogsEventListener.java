package com.homebrew.io.listeners;

import com.homebrew.exception.BaseException;
import com.homebrew.persistance.dao.ILogsEventDao;
import com.homebrew.persistance.dto.LogsEventDto;
import com.pi4j.io.gpio.event.GpioPinDigitalStateChangeEvent;
import com.pi4j.io.gpio.event.GpioPinListenerDigital;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

import java.util.Date;

public class GpioDigitalLogsEventListener implements GpioPinListenerDigital {

    private static final Logger LOGGER = Logger.getLogger(GpioDigitalLogsEventListener.class);

    @Autowired
    private ILogsEventDao logsEventDao;

    public GpioDigitalLogsEventListener() {
        super();

        // Init method
        SpringBeanAutowiringSupport.processInjectionBasedOnCurrentContext(this);
    }

    @Override
    public void handleGpioPinDigitalStateChangeEvent(GpioPinDigitalStateChangeEvent event) {
        // display pin state on console
        System.out.println(" LOGS : " + event.getPin() + " = "
                + event.getState() + " ");

        LogsEventDto logsEventDto = new LogsEventDto();
        logsEventDto.setPinProvider(event.getPin().getPin().getProvider());
        logsEventDto.setPinIndex(event.getPin().getPin().getAddress());
        logsEventDto.setPinName(event.getPin().getName());
        logsEventDto.setEventDate(new Date());
        logsEventDto.setEventValue(event.getState().getValue());

        try {
            logsEventDao.create(logsEventDto);
        } catch (BaseException e) {
            LOGGER.error(e);
        }
    }
}