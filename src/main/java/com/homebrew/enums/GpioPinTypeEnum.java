package com.homebrew.enums;

public enum GpioPinTypeEnum {

	// COMMON
	ANALOG_INPUT("ANALOG_INPUT", "analogInput"),
    ANALOG_OUTPUT("ANALOG_OUTPUT", "analogOutput"),
    DIGITAL_OUTPUT("DIGITAL_OUTPUT", "digitalOutput"),
    DIGITAL_INPUT("DIGITAL_INPUT", "digitalInput");

	private final String label;
	private final String message;

	GpioPinTypeEnum(final String label, final String message) {
		this.label = label;
		this.message = message;
	}

	public static boolean containsName(final String s) {
		for (final GpioPinTypeEnum e : GpioPinTypeEnum.values()) {
			if ((e.name() != null) && e.name().equals(s)) {
				return true;
			}
		}
		return false;
	}

	public static boolean containsLabel(final String s) {
		for (final GpioPinTypeEnum e : GpioPinTypeEnum.values()) {
			if ((e.label() != null) && e.label().equals(s)) {
				return true;
			}
		}
		return false;
	}

	public static GpioPinTypeEnum findByName(final String s) {
		for (final GpioPinTypeEnum e : GpioPinTypeEnum.values()) {
			if ((e.name() != null) && e.name().equals(s)) {
				return e;
			}
		}
		return null;
	}

	public static GpioPinTypeEnum findByLabel(final String s) {
		for (final GpioPinTypeEnum e : GpioPinTypeEnum.values()) {
			if ((e.label() != null) && e.label().equals(s)) {
				return e;
			}
		}
		return null;
	}

	public static GpioPinTypeEnum[] getAll() {
		return values();
	}

	public static String[] getAllName() {
		final GpioPinTypeEnum[] values = values();
		final String[] result = new String[values.length];
		for (int i = 0; i < values.length; i++) {
			result[i] = values[i].name();
		}
		return result;
	}

	public static String[] getAllLabel() {
		final GpioPinTypeEnum[] values = values();
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