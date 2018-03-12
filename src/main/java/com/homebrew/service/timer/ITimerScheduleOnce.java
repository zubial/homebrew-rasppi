package com.homebrew.service.timer;


import com.homebrew.exception.BaseException;
import com.homebrew.rest.type.ResponseType;

public interface ITimerScheduleOnce {
    ResponseType process(Integer minutes)
                throws BaseException;
}
