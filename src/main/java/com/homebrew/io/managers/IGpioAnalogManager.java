package com.homebrew.io.managers;

import com.homebrew.exception.BaseException;
import com.pi4j.gpio.extension.pcf.PCFAnalogShortGpioProvider;
import com.pi4j.io.gpio.GpioController;
import com.pi4j.io.gpio.GpioPinAnalogInput;

import java.util.List;

public interface IGpioAnalogManager {


    GpioController getGpioController() throws BaseException;
    PCFAnalogShortGpioProvider getGpioProvider() throws BaseException;

    List<GpioPinAnalogInput> getAllPin() throws BaseException;
    GpioPinAnalogInput getPin(Integer pinIndex) throws BaseException;
    GpioPinAnalogInput getPin00() throws BaseException;
    GpioPinAnalogInput getPin01() throws BaseException;
    GpioPinAnalogInput getPin02() throws BaseException;
    GpioPinAnalogInput getPin03() throws BaseException;
    GpioPinAnalogInput getPin04() throws BaseException;
    GpioPinAnalogInput getPin05() throws BaseException;

    GpioPinAnalogInput getPin00(String pinName) throws BaseException;
    GpioPinAnalogInput getPin01(String pinName) throws BaseException;
    GpioPinAnalogInput getPin02(String pinName) throws BaseException;
    GpioPinAnalogInput getPin03(String pinName) throws BaseException;
    GpioPinAnalogInput getPin04(String pinName) throws BaseException;
    GpioPinAnalogInput getPin05(String pinName) throws BaseException;

    void startSocketIOEvent(Integer pinIndex) throws BaseException;
    void stopSocketIOEvent(Integer pinIndex) throws BaseException;

    void startLogsEvent(Integer pinIndex) throws BaseException;
    void stopLogsEvent(Integer pinIndex) throws BaseException;

    void shutdown() throws BaseException;
}
