package com.homebrew.service.gpio;


import com.homebrew.exception.BaseException;
import com.homebrew.rest.type.ResponseType;

public interface IGpioAnalogShutdown {
    ResponseType process()
                throws BaseException;
}
