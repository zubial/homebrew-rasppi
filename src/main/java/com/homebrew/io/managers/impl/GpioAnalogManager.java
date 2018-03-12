package com.homebrew.io.managers.impl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.homebrew.enums.ErrorCodeEnum;
import com.homebrew.exception.BaseException;
import com.homebrew.exception.GpioException;
import com.homebrew.io.listeners.GpioAnalogLogsEventListener;
import com.homebrew.io.listeners.GpioAnalogSocketIOEventListener;
import com.homebrew.io.managers.IGpioAnalogManager;
import com.pi4j.gpio.extension.pcf.PCFAnalogShortGpioProvider;
import com.pi4j.gpio.extension.pcf.PCFAnalogShortPin;
import com.pi4j.io.gpio.GpioController;
import com.pi4j.io.gpio.GpioFactory;
import com.pi4j.io.gpio.GpioPinAnalogInput;
import com.pi4j.io.gpio.GpioPinDigitalOutput;
import com.pi4j.io.gpio.event.GpioPinListenerAnalogShort;

@Service
public class GpioAnalogManager implements IGpioAnalogManager {

    private static final Logger LOGGER = Logger.getLogger(GpioDigitalManager.class);

    @Value("${gpio.i2c.bus}")
    private Integer i2cBus;

    @Value("${gpio.i2c.address}")
    private Integer i2cAddress;

    private GpioController gpioController;
    private PCFAnalogShortGpioProvider gpioProvider;

    private GpioPinAnalogInput pin00;
    private GpioPinAnalogInput pin01;
    private GpioPinAnalogInput pin02;
    private GpioPinAnalogInput pin03;
    private GpioPinAnalogInput pin04;
    private GpioPinAnalogInput pin05;

    private GpioPinListenerAnalogShort logsEventListener;
    private GpioPinListenerAnalogShort socketIOEventListener;

    private void initialize() throws BaseException {
        try{
            if (gpioController == null
                    || gpioProvider == null) {
                gpioController = GpioFactory.getInstance();
                gpioProvider = new PCFAnalogShortGpioProvider(i2cBus, i2cAddress);
            }
        } catch (Exception e) {
            throw new GpioException(ErrorCodeEnum.GPIO_INIT_FAILED, e);
        }
    }

    @Override
    public GpioController getGpioController() throws BaseException {
        initialize();
        return gpioController;
    }

    @Override
    public PCFAnalogShortGpioProvider getGpioProvider() throws BaseException {
        initialize();
        return gpioProvider;
    }

    @Override
    public List<GpioPinAnalogInput> getAllPin() throws BaseException {
        List<GpioPinAnalogInput> all = new ArrayList<>();

        if (pin00 != null) {
            all.add(pin00);
        }
        if (pin01 != null) {
            all.add(pin01);
        }
        if (pin02 != null) {
            all.add(pin02);
        }
        if (pin03 != null) {
            all.add(pin03);
        }
        if (pin04 != null) {
            all.add(pin04);
        }
        if (pin05 != null) {
            all.add(pin05);
        }
        return all;
    }

    @Override
    public GpioPinAnalogInput getPin(Integer pinIndex) throws BaseException {
        GpioPinAnalogInput pin = null;
        if (pinIndex == 0) {
            pin = getPin00();
        }else if (pinIndex == 1) {
            pin = getPin01();
        }else if (pinIndex == 2) {
            pin = getPin02();
        }else if (pinIndex == 3) {
            pin = getPin03();
        }else if (pinIndex == 4) {
            pin = getPin04();
        }else if (pinIndex == 5) {
            pin = getPin05();
        }
        return pin;
    }

    @Override
    public GpioPinAnalogInput getPin00() throws BaseException {
        return getPin00("");
    }
    @Override
    public GpioPinAnalogInput getPin00(String pinName) throws BaseException {
        try{
            if (pin00 == null) {
                this.pin00 = getGpioController().provisionAnalogInputPin(gpioProvider, PCFAnalogShortPin.GPIO_00, pinName);
            }
            return this.pin00;
        } catch (Exception e) {
            throw new GpioException(ErrorCodeEnum.GPIO_CMD_FAILED, e);
        }
    }

    @Override
    public GpioPinAnalogInput getPin01() throws BaseException {
        return getPin01("");
    }
    @Override
    public GpioPinAnalogInput getPin01(String pinName) throws BaseException {
        try{
            if (pin01 == null) {
                this.pin01 = getGpioController().provisionAnalogInputPin(gpioProvider, PCFAnalogShortPin.GPIO_01, pinName);
            }
            return this.pin01;
        } catch (Exception e) {
            throw new GpioException(ErrorCodeEnum.GPIO_CMD_FAILED, e);
        }
    }

    @Override
    public GpioPinAnalogInput getPin02() throws BaseException {
        return getPin02("");
    }
    @Override
    public GpioPinAnalogInput getPin02(String pinName) throws BaseException {
        try{
            if (pin02 == null) {
                this.pin02 = getGpioController().provisionAnalogInputPin(gpioProvider, PCFAnalogShortPin.GPIO_02, pinName);
            }
            return this.pin02;
        } catch (Exception e) {
            throw new GpioException(ErrorCodeEnum.GPIO_CMD_FAILED, e);
        }
    }

    @Override
    public GpioPinAnalogInput getPin03() throws BaseException {
        return getPin00("");
    }

    @Override
    public GpioPinAnalogInput getPin03(String pinName) throws BaseException {
        try{
            if (pin03 == null) {
                this.pin03 = getGpioController().provisionAnalogInputPin(gpioProvider, PCFAnalogShortPin.GPIO_03, pinName);
            }
            return this.pin03;
        } catch (Exception e) {
            throw new GpioException(ErrorCodeEnum.GPIO_CMD_FAILED, e);
        }
    }

    @Override
    public GpioPinAnalogInput getPin04() throws BaseException {
        return getPin04("");
    }
    @Override
    public GpioPinAnalogInput getPin04(String pinName) throws BaseException {
        try{
            if (pin04 == null) {
                this.pin04 = getGpioController().provisionAnalogInputPin(gpioProvider, PCFAnalogShortPin.GPIO_04, pinName);
            }
            return this.pin04;
        } catch (Exception e) {
            throw new GpioException(ErrorCodeEnum.GPIO_CMD_FAILED, e);
        }
    }

    @Override
    public GpioPinAnalogInput getPin05() throws BaseException {
        return getPin05("");
    }
    @Override
    public GpioPinAnalogInput getPin05(String pinName) throws BaseException {
        try{
            if (pin05 == null) {
                this.pin05 = getGpioController().provisionAnalogInputPin(gpioProvider, PCFAnalogShortPin.GPIO_05, pinName);
            }
            return this.pin05;
        } catch (Exception e) {
            throw new GpioException(ErrorCodeEnum.GPIO_CMD_FAILED, e);
        }
    }

    @Override
    public void startSocketIOEvent(Integer pinIndex) throws BaseException {
        try{
            GpioPinAnalogInput pinAnalogInput = getPin(pinIndex);

            if (this.socketIOEventListener == null) {
                this.socketIOEventListener = new GpioAnalogSocketIOEventListener();
            }

            if (!pinAnalogInput.hasListener(this.socketIOEventListener)) {
                pinAnalogInput.addListener(this.socketIOEventListener);
            }

        } catch (Exception e) {
            throw new GpioException(ErrorCodeEnum.SOCKETIO_EVENT_FAILED, e);
        }
    }

    @Override
    public void stopSocketIOEvent(Integer pinIndex) throws BaseException {
        try{
            GpioPinAnalogInput pinAnalogInput = getPin(pinIndex);

            if (this.socketIOEventListener != null
                    && pinAnalogInput.hasListener(this.socketIOEventListener)) {
                    pinAnalogInput.removeListener(this.socketIOEventListener);
            }

        } catch (Exception e) {
            throw new GpioException(ErrorCodeEnum.SOCKETIO_EVENT_FAILED, e);
        }
    }

    @Override
    public void startLogsEvent(Integer pinIndex) throws BaseException {
        try{
            GpioPinAnalogInput pinAnalogInput = getPin(pinIndex);

            if (this.logsEventListener == null) {
                this.logsEventListener = new GpioAnalogLogsEventListener();
            }

            if (!pinAnalogInput.hasListener(this.logsEventListener)) {
                pinAnalogInput.addListener(this.logsEventListener);
            }

        } catch (Exception e) {
            throw new GpioException(ErrorCodeEnum.GPIO_LOGS_FAILED, e);
        }
    }

    @Override
    public void stopLogsEvent(Integer pinIndex) throws BaseException {
        try{
            GpioPinAnalogInput pinAnalogInput = getPin(pinIndex);

            if (this.logsEventListener == null) {
                this.logsEventListener = new GpioAnalogLogsEventListener();
            }

            if (pinAnalogInput.hasListener(this.logsEventListener)) {
                pinAnalogInput.removeListener(this.logsEventListener);
            }

        } catch (Exception e) {
            throw new GpioException(ErrorCodeEnum.GPIO_LOGS_FAILED, e);
        }
    }

    @Override
    public void shutdown() throws BaseException {
        try{
            getGpioController().shutdown();

            this.logsEventListener = null;
            this.socketIOEventListener = null;
            this.gpioProvider = null;

            this.gpioController = null;
        } catch (Exception e) {
            throw new GpioException(ErrorCodeEnum.GPIO_SHUTDOWN_FAILED, e);
        }
    }
}
