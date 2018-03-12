package com.homebrew.service.gpio;


import com.homebrew.exception.BaseException;
import com.homebrew.rest.type.CmdGetAllPinResponseType;

public interface IGpioGetAllAnalogPinShortValue {
    CmdGetAllPinResponseType process()
                throws BaseException;
}
