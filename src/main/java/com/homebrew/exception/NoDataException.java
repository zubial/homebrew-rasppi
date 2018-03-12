package com.homebrew.exception;

import com.homebrew.enums.ErrorCodeEnum;
import org.apache.log4j.Logger;

import java.io.Serializable;

public class NoDataException extends BaseException {

	private static final long serialVersionUID = 1L;

	private static final Logger LOGGER = Logger.getLogger(NoDataException.class);

	public NoDataException() {
		super();
	}

	public NoDataException(String errorMessage) {
		super(errorMessage);
	}

	public NoDataException(ErrorCodeEnum errorCodeEnum) {
		super(errorCodeEnum);
	}

	public NoDataException(ErrorCodeEnum errorCodeEnum, Object... args) {
		super(errorCodeEnum, args);
	}

	public NoDataException(String errorCode, String errorMessage) {
		super(errorCode, errorMessage);
	}

	public NoDataException(String errorCode, String errorMessage, Serializable additionalInfo) {
		super(errorCode, errorMessage, additionalInfo);
	}

	public NoDataException(String errorMessage, Throwable runtimeError) {
		super(errorMessage, runtimeError);
	}

	public NoDataException(String errorCode, String errorMessage, Throwable runtimeError) {
		super(errorCode, errorMessage, runtimeError);
	}
}
