package com.homebrew.io.managers.impl;

import com.homebrew.enums.ErrorCodeEnum;
import com.homebrew.exception.BaseException;
import com.homebrew.exception.GpioException;
import com.homebrew.io.listeners.GpioDigitalLogsEventListener;
import com.homebrew.io.listeners.GpioDigitalSocketIOEventListener;
import com.homebrew.io.managers.IGpioDigitalManager;
import com.pi4j.io.gpio.*;
import com.pi4j.io.gpio.event.GpioPinListenerDigital;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class GpioDigitalManager implements IGpioDigitalManager {

    private static final Logger LOGGER = Logger.getLogger(GpioAnalogManager.class);

    private GpioController gpioController;

    private Map<Integer, GpioPinDigitalOutput> gpioPins = new HashMap<>();

    private GpioPinListenerDigital logsEventListener;
    private GpioPinListenerDigital socketIOEventListener;

    @Override
    public GpioController getGpioController() throws BaseException {
        try{
            if (gpioController == null) {
                gpioController = GpioFactory.getInstance();
            }
            return gpioController;
        } catch (Exception e) {
            throw new GpioException(ErrorCodeEnum.GPIO_INIT_FAILED, e);
        }
    }

    @Override
    public List<GpioPinDigitalOutput> getAllPin() throws BaseException {
        List<GpioPinDigitalOutput> all = new ArrayList<>();

        Iterator it = gpioPins.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry pair = (Map.Entry)it.next();
            all.add((GpioPinDigitalOutput)pair.getValue());
        }

        return all;
    }

    @Override
    public GpioPinDigitalOutput getPin(Integer pinIndex) throws BaseException {
        return this.gpioPins.get(pinIndex);
    }

    @Override
    public GpioPinDigitalOutput createPin(Integer pinIndex, Pin pin, String pinName, PinState initialValue, PinState shutdownValue) throws BaseException {
        try{
            if (!this.gpioPins.containsKey(pinIndex)) {
                GpioPinDigitalOutput newPin = getGpioController().provisionDigitalOutputPin(pin, pinName, initialValue);
                newPin.setShutdownOptions(true, shutdownValue);

                this.gpioPins.put(pinIndex, newPin);

                LOGGER.debug("PinDigitalOutput created : "+pinName+" ("+pinIndex+")");
            }
            return this.gpioPins.get(pinIndex);
        } catch (Exception e) {
            throw new GpioException(ErrorCodeEnum.GPIO_CMD_FAILED, e);
        }
    }

    @Override
    public void startSocketIOEvent(Integer pinIndex) throws BaseException {
        try{
            GpioPinDigitalOutput pinOutput = getPin(pinIndex);

            if (this.socketIOEventListener == null) {
                this.socketIOEventListener = new GpioDigitalSocketIOEventListener();
            }

            if (!pinOutput.hasListener(this.socketIOEventListener)) {
                pinOutput.addListener(this.socketIOEventListener);
            }

        } catch (Exception e) {
            throw new GpioException(ErrorCodeEnum.SOCKETIO_EVENT_FAILED, e);
        }
    }

    @Override
    public void stopSocketIOEvent(Integer pinIndex) throws BaseException {
        try{
            GpioPinDigitalOutput pinOutput = getPin(pinIndex);

            if (this.socketIOEventListener != null
                    && pinOutput.hasListener(this.socketIOEventListener)) {
                pinOutput.removeListener(this.socketIOEventListener);
            }

        } catch (Exception e) {
            throw new GpioException(ErrorCodeEnum.SOCKETIO_EVENT_FAILED, e);
        }
    }

    @Override
    public void startLogsEvent(Integer pinIndex) throws BaseException {
        try{
            GpioPinDigitalOutput pinOutput = getPin(pinIndex);

            if (this.logsEventListener == null) {
                this.logsEventListener = new GpioDigitalLogsEventListener();
            }

            if (!pinOutput.hasListener(this.logsEventListener)) {
                pinOutput.addListener(this.logsEventListener);
            }

        } catch (Exception e) {
            throw new GpioException(ErrorCodeEnum.GPIO_LOGS_FAILED, e);
        }
    }

    @Override
    public void stopLogsEvent(Integer pinIndex) throws BaseException {
        try{
            GpioPinDigitalOutput pinOutput = getPin(pinIndex);

            if (this.logsEventListener != null
                    && pinOutput.hasListener(this.logsEventListener)) {
                pinOutput.removeListener(this.logsEventListener);
            }

        } catch (Exception e) {
            throw new GpioException(ErrorCodeEnum.GPIO_LOGS_FAILED, e);
        }
    }

    @Override
    public void shutdown() throws BaseException {
        try{
            getGpioController().shutdown();

            this.gpioController = null;
        } catch (Exception e) {
            throw new GpioException(ErrorCodeEnum.GPIO_SHUTDOWN_FAILED, e);
        }
    }
}
