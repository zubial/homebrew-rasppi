package com.homebrew.io.managers;

import com.homebrew.exception.BaseException;
import com.pi4j.io.gpio.GpioController;
import com.pi4j.io.gpio.GpioPinDigitalInput;
import com.pi4j.io.gpio.Pin;

import java.util.List;

public interface IGpioImpulseManager {

    GpioController getGpioController() throws BaseException;

    GpioPinDigitalInput getPin(Integer pinIndex) throws BaseException;
    List<GpioPinDigitalInput> getAllPin() throws BaseException;

    GpioPinDigitalInput createPin(Integer pinIndex, Pin pin, String pinName) throws BaseException;

    Integer getPinCount(Integer pinIndex) throws BaseException;
    void setPinCount(Integer pinIndex, Integer pinCount) throws BaseException;
    void addPinCount(Integer pinIndex) throws BaseException;

    void startCountEvent(Integer pinIndex) throws BaseException;
    void stopCountEvent(Integer pinIndex) throws BaseException;

    void startLogsEvent(Integer pinIndex) throws BaseException;
    void stopLogsEvent(Integer pinIndex) throws BaseException;

    void startSocketIOEvent(Integer pinIndex) throws BaseException;
    void stopSocketIOEvent(Integer pinIndex) throws BaseException;

    void shutdown() throws BaseException;
}
