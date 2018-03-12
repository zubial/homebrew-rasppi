package com.homebrew.service.gpio;


import com.homebrew.exception.BaseException;
import com.homebrew.rest.type.CmdGetAllPinResponseType;

public interface IGpioGetAllDigitalPinState {
    CmdGetAllPinResponseType process()
                throws BaseException;
}
