package com.homebrew.exception;

import org.apache.log4j.Logger;

import com.homebrew.enums.ErrorCodeEnum;

public class SocketIOException extends BaseException {

	private static final long serialVersionUID = 1L;

	private static final Logger LOGGER = Logger.getLogger(SocketIOException.class);

	public SocketIOException(String errorMessage) {
		super(errorMessage);
	}

    public SocketIOException(String errorMessage, Throwable runtimeError) {
        super(errorMessage, runtimeError);
    }

    public SocketIOException(ErrorCodeEnum errorCodeEnum) {
		super(errorCodeEnum);
	}

    public SocketIOException(ErrorCodeEnum errorCodeEnum, Throwable runtimeError) {
        super(errorCodeEnum.label(), errorCodeEnum.message(), runtimeError);
    }

	public SocketIOException(ErrorCodeEnum errorCodeEnum, Object... args) {
		super(errorCodeEnum, args);
	}

	public SocketIOException(String errorCode, String errorMessage, Throwable runtimeError) {
		super(errorCode, errorMessage, runtimeError);
	}
}
