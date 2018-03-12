package com.homebrew.exception;

import com.homebrew.enums.ErrorCodeEnum;
import org.apache.log4j.Logger;

public class GpioException extends BaseException {

	private static final long serialVersionUID = 1L;

	private static final Logger LOGGER = Logger.getLogger(GpioException.class);

	public GpioException(String errorMessage) {
		super(errorMessage);
	}

    public GpioException(String errorMessage, Throwable runtimeError) {
        super(errorMessage, runtimeError);
    }

    public GpioException(ErrorCodeEnum errorCodeEnum) {
		super(errorCodeEnum);
	}

    public GpioException(ErrorCodeEnum errorCodeEnum, Throwable runtimeError) {
        super(errorCodeEnum.label(), errorCodeEnum.message(), runtimeError);
    }

	public GpioException(ErrorCodeEnum errorCodeEnum, Object... args) {
		super(errorCodeEnum, args);
	}

	public GpioException(String errorCode, String errorMessage, Throwable runtimeError) {
		super(errorCode, errorMessage, runtimeError);
	}
}
