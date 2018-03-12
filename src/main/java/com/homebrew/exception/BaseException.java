package com.homebrew.exception;

import com.homebrew.enums.ErrorCodeEnum;

import java.io.Serializable;

public abstract class BaseException extends Exception {

	private static final String DEFAULT_MESSAGE = "BaseException";
	private static final String DEFAULT_CODE = "-1";

	private final String errorCode;
	private final Serializable additionalInfo;

	public BaseException() {
		super(DEFAULT_MESSAGE);

		this.errorCode = DEFAULT_CODE;
		this.additionalInfo = null;
	}

	public BaseException(final String errorMessage) {
		super(errorMessage);

		this.errorCode = DEFAULT_CODE;
		this.additionalInfo = null;
	}

	public BaseException(final ErrorCodeEnum errorCodeEnum) {
		super(errorCodeEnum.label());

		this.errorCode = errorCodeEnum.name();
		this.additionalInfo = null;
	}

	public BaseException(final ErrorCodeEnum errorCodeEnum, final Object... args) {
		super(errorCodeEnum.label(args));

		this.errorCode = errorCodeEnum.name();
		this.additionalInfo = null;
	}

	public BaseException(final String errorCode, final String errorMessage) {
		super(errorCode + " : " + errorMessage);

		this.errorCode = errorCode;
		this.additionalInfo = null;
	}

	public BaseException(final String errorCode, final String errorMessage, final Serializable additionalInfo) {
		super(errorMessage);

		this.errorCode = errorCode;
		this.additionalInfo = additionalInfo;
	}

	public BaseException(final String errorMessage, final Throwable runtimeError) {
		super(errorMessage, runtimeError);

		this.errorCode = DEFAULT_CODE;
		this.additionalInfo = null;
	}

	public BaseException(final String errorCode, final String errorMessage, final Throwable runtimeError) {
		super(errorMessage, runtimeError);

		this.errorCode = errorCode;
		this.additionalInfo = null;
	}

	public String getErrorCode() {
		return errorCode;
	}

	public String getErrorMessage() {
		return getMessage();
	}

	public Object getAdditionalInfo() {
		return additionalInfo;
	}
}
