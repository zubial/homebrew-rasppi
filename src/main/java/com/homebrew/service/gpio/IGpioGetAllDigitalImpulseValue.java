package com.homebrew.service.gpio;


import com.homebrew.exception.BaseException;
import com.homebrew.rest.type.CmdGetAllPinResponseType;

public interface IGpioGetAllDigitalImpulseValue {
    CmdGetAllPinResponseType process()
                throws BaseException;
}
