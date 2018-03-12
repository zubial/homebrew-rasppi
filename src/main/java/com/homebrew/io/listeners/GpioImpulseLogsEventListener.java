package com.homebrew.io.listeners;

import com.homebrew.exception.BaseException;
import com.homebrew.io.managers.IGpioImpulseManager;
import com.homebrew.persistance.dao.ILogsEventDao;
import com.homebrew.persistance.dto.LogsEventDto;
import com.pi4j.io.gpio.event.GpioPinDigitalStateChangeEvent;
import com.pi4j.io.gpio.event.GpioPinListenerDigital;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

import java.util.Date;

public class GpioImpulseLogsEventListener implements GpioPinListenerDigital {

    private static final Logger LOGGER = Logger.getLogger(GpioImpulseLogsEventListener.class);

    @Autowired
    private ILogsEventDao logsEventDao;

    @Autowired
    private IGpioImpulseManager impluseManager;

    public GpioImpulseLogsEventListener() {
        super();

        // Init method
        SpringBeanAutowiringSupport.processInjectionBasedOnCurrentContext(this);
    }

    @Override
    public void handleGpioPinDigitalStateChangeEvent(GpioPinDigitalStateChangeEvent event) {
        try {
            Integer impulseCount = impluseManager.getPinCount(event.getPin().getPin().getAddress());

            if ((impulseCount % 5) == 0) {
                // display pin state on console
                System.out.println(" LOGS : " + event.getPin() + " = "
                        + impulseCount + " ");

                LogsEventDto logsEventDto = new LogsEventDto();
                logsEventDto.setPinProvider(event.getPin().getPin().getProvider());
                logsEventDto.setPinIndex(event.getPin().getPin().getAddress());
                logsEventDto.setPinName(event.getPin().getName());
                logsEventDto.setEventDate(new Date());
                logsEventDto.setEventValue(impulseCount);

                logsEventDao.create(logsEventDto);
            }
        } catch (BaseException e) {
            LOGGER.error(e);
        }
    }
}