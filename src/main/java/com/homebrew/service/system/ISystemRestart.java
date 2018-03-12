package com.homebrew.service.system;


import com.homebrew.exception.BaseException;
import com.homebrew.rest.type.ResponseType;

public interface ISystemRestart {
    ResponseType process()
                throws BaseException;
}
