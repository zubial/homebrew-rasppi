package com.homebrew.exception;

import com.homebrew.enums.ErrorCodeEnum;
import org.apache.log4j.Logger;

import java.io.Serializable;
import java.sql.SQLException;

public class ServiceException extends BaseException {

	private static final long serialVersionUID = 1L;

	private static final Logger LOGGER = Logger.getLogger(ServiceException.class);

	public ServiceException() {
		super();
	}

	public ServiceException(String errorMessage) {
		super(errorMessage);
	}

	public ServiceException(ErrorCodeEnum errorCodeEnum) {
		super(errorCodeEnum);
	}

	public ServiceException(ErrorCodeEnum errorCodeEnum, Object... args) {
		super(errorCodeEnum, args);
	}

	public ServiceException(String errorCode, String errorMessage) {
		super(errorCode, errorMessage);
	}

	public ServiceException(String errorCode, String errorMessage, Serializable additionalInfo) {
		super(errorCode, errorMessage, additionalInfo);
	}

	public ServiceException(String errorMessage, Throwable runtimeError) {
		super(errorMessage, runtimeError);
	}

	public ServiceException(String errorCode, String errorMessage, Throwable runtimeError) {
		super(errorCode, errorMessage, runtimeError);
	}

	public ErrorSql getErrorSQL() {

		SQLException sqlException = null;

		for (Throwable t = this; t != null && sqlException == null; t = t.getCause()) {
			if (t instanceof SQLException) {
				sqlException = (SQLException) t;
			}
		}

		if (sqlException != null) {
			if (sqlException.getMessage().startsWith("ORA")) {
				try {

					final String exceptionMessage = sqlException.getMessage();
					final int code = Integer.parseInt(exceptionMessage.substring(3, 9));
					final String name = exceptionMessage.substring(10, sqlException.getMessage().indexOf("ORA", 9) - 1);
					final String message = exceptionMessage.substring(11);

					return new ErrorSql(code, name, message);
				} catch (final Exception e) {
					LOGGER.error(e.getMessage(), e);

					return new ErrorSql(-20000, "Unknown SQL error:", sqlException.getMessage());
				}
			}
			return new ErrorSql(-20000, "Unknown SQL error: ", sqlException.getMessage());
		}

		return new ErrorSql(0, "Unknown error: ", getMessage());
	}

	public static class ErrorSql {

		private final int code;
		private final String name;
		private final String message;

		ErrorSql(final int code, final String name, final String message) {
			this.code = code;
			this.name = name;
			this.message = message;
		}

		public String getMessage() {
			return message;
		}

		public int getCode() {
			return code;
		}

		public String getName() {
			return name;
		}
	}
}
