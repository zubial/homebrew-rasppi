package com.homebrew.io.managers.impl;

import com.homebrew.enums.ErrorCodeEnum;
import com.homebrew.exception.BaseException;
import com.homebrew.exception.GpioException;
import com.homebrew.io.listeners.GpioDigitalLogsEventListener;
import com.homebrew.io.listeners.GpioImpulseCountEventListener;
import com.homebrew.io.listeners.GpioImpulseSocketIOEventListener;
import com.homebrew.io.managers.IGpioImpulseManager;
import com.pi4j.io.gpio.*;
import com.pi4j.io.gpio.event.GpioPinListenerDigital;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class GpioImpulseManager implements IGpioImpulseManager {

    private static final Logger LOGGER = Logger.getLogger(GpioImpulseManager.class);

    private GpioController gpioController;

    private Map<Integer, GpioPinDigitalInput> gpioPins = new HashMap<>();
    private Map<Integer, Integer> gpioPinsCount = new HashMap<>();

    private GpioPinListenerDigital countEventListener;
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
    public List<GpioPinDigitalInput> getAllPin() throws BaseException {
        List<GpioPinDigitalInput> all = new ArrayList<>();

        Iterator it = gpioPins.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry pair = (Map.Entry)it.next();
            all.add((GpioPinDigitalInput)pair.getValue());
        }

        return all;
    }

    @Override
    public GpioPinDigitalInput getPin(Integer pinIndex) throws BaseException {
        return this.gpioPins.get(pinIndex);
    }

    @Override
    public GpioPinDigitalInput createPin(Integer pinIndex, Pin pin, String pinName) throws BaseException {
        try{
            if (!this.gpioPins.containsKey(pinIndex)) {
                GpioPinDigitalInput newPin = getGpioController().provisionDigitalInputPin(pin, pinName, PinPullResistance.PULL_UP);
                newPin.setShutdownOptions(true);

                this.gpioPins.put(pinIndex, newPin);
                this.gpioPinsCount.put(pinIndex, 0);

                LOGGER.debug("PinDigitalImpulse created : "+pinName+" ("+pinIndex+")");
            }
            return this.gpioPins.get(pinIndex);
        } catch (Exception e) {
            throw new GpioException(ErrorCodeEnum.GPIO_CMD_FAILED, e);
        }
    }

    @Override
    public Integer getPinCount(Integer pinIndex) throws BaseException {
        if (this.gpioPinsCount.containsKey(pinIndex)) {
            return this.gpioPinsCount.get(pinIndex);
        }
        return null;
    }

    @Override
    public void setPinCount(Integer pinIndex, Integer pinCount) throws BaseException {
        if (this.gpioPinsCount.containsKey(pinIndex)) {
            this.gpioPinsCount.put(pinIndex, pinCount);
        }
    }

    @Override
    public void addPinCount(Integer pinIndex) throws BaseException {
        if (this.gpioPinsCount.containsKey(pinIndex)) {
            this.gpioPinsCount.put(pinIndex, this.gpioPinsCount.get(pinIndex) + 1);
        }
    }

    @Override
    public void startCountEvent(Integer pinIndex) throws BaseException {
        try{
            GpioPinDigitalInput pinOutput = getPin(pinIndex);

            if (this.countEventListener == null) {
                this.countEventListener = new GpioImpulseCountEventListener();
            }

            if (!pinOutput.hasListener(this.countEventListener)) {
                pinOutput.addListener(this.countEventListener);
            }

        } catch (Exception e) {
            throw new GpioException(ErrorCodeEnum.GPIO_EVENT_FAILED, e);
        }
    }

    @Override
    public void stopCountEvent(Integer pinIndex) throws BaseException {
        try{
            GpioPinDigitalInput pinOutput = getPin(pinIndex);

            if (this.countEventListener != null
                    && pinOutput.hasListener(this.countEventListener)) {
                pinOutput.removeListener(this.countEventListener);
            }

        } catch (Exception e) {
            throw new GpioException(ErrorCodeEnum.SOCKETIO_EVENT_FAILED, e);
        }
    }

    @Override
    public void startSocketIOEvent(Integer pinIndex) throws BaseException {
        try{
            GpioPinDigitalInput pinOutput = getPin(pinIndex);

            if (this.socketIOEventListener == null) {
                this.socketIOEventListener = new GpioImpulseSocketIOEventListener();
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
            GpioPinDigitalInput pinOutput = getPin(pinIndex);

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
            GpioPinDigitalInput pinOutput = getPin(pinIndex);

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
            GpioPinDigitalInput pinOutput = getPin(pinIndex);

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
