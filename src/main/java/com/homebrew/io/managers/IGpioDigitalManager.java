package com.homebrew.io.managers;

import com.homebrew.exception.BaseException;
import com.pi4j.io.gpio.GpioController;
import com.pi4j.io.gpio.GpioPinDigitalOutput;
import com.pi4j.io.gpio.Pin;
import com.pi4j.io.gpio.PinState;

import java.util.List;

public interface IGpioDigitalManager {

    GpioController getGpioController() throws BaseException;

    GpioPinDigitalOutput getPin(Integer pinIndex) throws BaseException;
    List<GpioPinDigitalOutput> getAllPin() throws BaseException;

    GpioPinDigitalOutput createPin(Integer pinIndex, Pin pin, String pinName, PinState initialValue, PinState shutdownValue) throws BaseException;

    void startLogsEvent(Integer pinIndex) throws BaseException;
    void stopLogsEvent(Integer pinIndex) throws BaseException;

    void startSocketIOEvent(Integer pinIndex) throws BaseException;
    void stopSocketIOEvent(Integer pinIndex) throws BaseException;

    void shutdown() throws BaseException;
}
