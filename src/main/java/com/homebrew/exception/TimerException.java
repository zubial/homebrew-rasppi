package com.homebrew.exception;

import org.apache.log4j.Logger;

import com.homebrew.enums.ErrorCodeEnum;

public class TimerException extends BaseException {

	private static final long serialVersionUID = 1L;

	private static final Logger LOGGER = Logger.getLogger(TimerException.class);

	public TimerException(String errorMessage) {
		super(errorMessage);
	}

    public TimerException(String errorMessage, Throwable runtimeError) {
        super(errorMessage, runtimeError);
    }

    public TimerException(ErrorCodeEnum errorCodeEnum) {
		super(errorCodeEnum);
	}

    public TimerException(ErrorCodeEnum errorCodeEnum, Throwable runtimeError) {
        super(errorCodeEnum.label(), errorCodeEnum.message(), runtimeError);
    }

	public TimerException(ErrorCodeEnum errorCodeEnum, Object... args) {
		super(errorCodeEnum, args);
	}

	public TimerException(String errorCode, String errorMessage, Throwable runtimeError) {
		super(errorCode, errorMessage, runtimeError);
	}
}
