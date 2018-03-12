package com.homebrew.service.timer;


import com.homebrew.exception.BaseException;
import com.homebrew.rest.type.ResponseType;

public interface ITimerPause {
    ResponseType process()
                throws BaseException;
}
