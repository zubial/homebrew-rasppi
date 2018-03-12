package com.homebrew.enums;

public enum ErrorCodeEnum {

	// COMMON
	COMMON_OK("Operation Done", "commonOperationDone"),
    COMMON_PARAM_MISSING("Parameters missing : %s", "commonParamMissing"),

	// SOCKET I/O
    SOCKETIO_INIT_FAILED("Socket I/O initialization failed", "socketioInitFailed"),
    SOCKETIO_STOP_FAILED("Socket I/O stopping failed", "socketioStopFailed"),
    SOCKETIO_EVENT_FAILED("Socket I/O event failed", "socketioEventFailed"),

	// GPIO
    GPIO_INIT_FAILED("Gpio initialization failed", "gpioInitFailed"),
    GPIO_LOAD_FAILED("Gpio loading failed", "gpioLoadFailed"),
    GPIO_SHUTDOWN_FAILED("Gpio shutdown failed", "gpioShutdownFailed"),
    GPIO_CMD_FAILED("Gpio gpio failed", "gpioCmdFailed"),
    GPIO_LOGS_FAILED("Gpio logs failed", "gpioLogsFailed"),
    GPIO_EVENT_FAILED("Gpio event failed", "gpioEventFailed"),

    // TIMER
    TIMER_INIT_FAILED("Timer initialization failed", "timerInitFailed");

    private final String label;
	private final String message;

	ErrorCodeEnum(final String label, final String message) {
		this.label = label;
		this.message = message;
	}

	public static boolean containsName(final String s) {
		for (final ErrorCodeEnum e : ErrorCodeEnum.values()) {
			if ((e.name() != null) && e.name().equals(s)) {
				return true;
			}
		}
		return false;
	}

	public static boolean containsLabel(final String s) {
		for (final ErrorCodeEnum e : ErrorCodeEnum.values()) {
			if ((e.label() != null) && e.label().equals(s)) {
				return true;
			}
		}
		return false;
	}

	public static ErrorCodeEnum findByName(final String s) {
		for (final ErrorCodeEnum e : ErrorCodeEnum.values()) {
			if ((e.name() != null) && e.name().equals(s)) {
				return e;
			}
		}
		return null;
	}

	public static ErrorCodeEnum findByLabel(final String s) {
		for (final ErrorCodeEnum e : ErrorCodeEnum.values()) {
			if ((e.label() != null) && e.label().equals(s)) {
				return e;
			}
		}
		return null;
	}

	public static ErrorCodeEnum[] getAll() {
		return values();
	}

	public static String[] getAllName() {
		final ErrorCodeEnum[] values = values();
		final String[] result = new String[values.length];
		for (int i = 0; i < values.length; i++) {
			result[i] = values[i].name();
		}
		return result;
	}

	public static String[] getAllLabel() {
		final ErrorCodeEnum[] values = values();
		final String[] result = new String[values.length];
		for (int i = 0; i < values.length; i++) {
			result[i] = values[i].label();
		}
		return result;
	}

	public String label() {
		return label;
	}

	public String label(final Object... args) {
		return String.format(this.label, args);
	}

	public String message() {
		return message;
	}
}