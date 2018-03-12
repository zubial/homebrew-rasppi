package com.homebrew.service.gpio;


import com.homebrew.exception.BaseException;
import com.homebrew.rest.type.ResponseType;

public interface IGpioSetDigitalPinOn {
    ResponseType process(Integer pinIndex)
                throws BaseException;
}
