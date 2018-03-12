package com.homebrew.enums;

public enum ErrorHttpEnum {

	HTTP_400(400, "Bad Request"),
	HTTP_401(401, "Unauthorized"),
	HTTP_402(402, "Payment Required"),
	HTTP_403(403, "Forbidden"),
	HTTP_404(404, "Not Found"),
	HTTP_405(405, "Method Not Allowed"),
	HTTP_406(406, "Not Acceptable"),
	HTTP_407(407, "Proxy Authentication Required"),
	HTTP_408(408, "Request Time-out"),
	HTTP_409(409, "Conflict"),
	HTTP_410(410, "Gone"),
	HTTP_411(411, "Length Required"),
	HTTP_412(412, "Precondition Failed"),
	HTTP_413(413, "Request Entity Too Large"),
	HTTP_414(414, "Request-URI Too Long"),
	HTTP_415(415, "Unsupported Media Type"),
	HTTP_416(416, "Requested range unsatisfiable"),
	HTTP_417(417, "Expectation failed"),
	HTTP_418(418, "I’m a teapot"),
	HTTP_422(422, "Unprocessable entity"),
	HTTP_423(423, "Locked"),
	HTTP_424(424, "Method failure"),
	HTTP_425(425, "Unordered Collection"),
	HTTP_426(426, "Upgrade Required"),
	HTTP_428(428, "Precondition Required"),
	HTTP_429(429, "Too Many Requests"),
	HTTP_431(431, "Request Header Fields Too Large"),
	HTTP_449(449, "Retry With"),
	HTTP_450(450, "Blocked by Windows Parental Controls"),
	HTTP_456(456, "Unrecoverable Error"),
	HTTP_499(499, "client has closed connection"),

	HTTP_500(500, "Internal Server Error"),
	HTTP_501(501, "Not Implemented"),
	HTTP_502(502, "Bad Gateway ou Proxy Error"),
	HTTP_503(503, "Service Unavailable"),
	HTTP_504(504, "Gateway Time-out"),
	HTTP_505(505, "HTTP Version not supported"),
	HTTP_506(506, "Variant also negociate"),
	HTTP_507(507, "Insufficient storage"),
	HTTP_508(508, "Loop detected"),
	HTTP_509(509, "Bandwidth Limit Exceeded"),
	HTTP_510(510, "Not extended"),
	HTTP_520(520, "Web server is returning an unknown error");

	private final Integer id;
	private final String label;

	ErrorHttpEnum(final Integer id, final String label) {
		this.id = id;
		this.label = label;
	}

	public static boolean containsId(final Integer i) {
		for (final ErrorHttpEnum e : ErrorHttpEnum.values()) {
			if ((e.id() != null) && e.id().equals(i)) {
				return true;
			}
		}
		return false;
	}

	public static boolean containsName(final String s) {
		for (final ErrorHttpEnum e : ErrorHttpEnum.values()) {
			if ((e.name() != null) && e.name().equals(s)) {
				return true;
			}
		}
		return false;
	}

	public static boolean containsLabel(final String s) {
		for (final ErrorHttpEnum e : ErrorHttpEnum.values()) {
			if ((e.label() != null) && e.label().equals(s)) {
				return true;
			}
		}
		return false;
	}

	public static ErrorHttpEnum findById(final Integer i) {
		for (final ErrorHttpEnum e : ErrorHttpEnum.values()) {
			if ((e.id() != null) && e.id().equals(i)) {
				return e;
			}
		}
		return null;
	}

	public static ErrorHttpEnum findByName(final String s) {
		for (final ErrorHttpEnum e : ErrorHttpEnum.values()) {
			if ((e.name() != null) && e.name().equals(s)) {
				return e;
			}
		}
		return null;
	}

	public static ErrorHttpEnum findByLabel(final String s) {
		for (final ErrorHttpEnum e : ErrorHttpEnum.values()) {
			if ((e.label() != null) && e.label().equals(s)) {
				return e;
			}
		}
		return null;
	}

	public static ErrorHttpEnum[] getAll() {
		return values();
	}

	public static Integer[] getAllId() {
		final ErrorHttpEnum[] values = values();
		final Integer[] result = new Integer[values.length];
		for (int i = 0; i < values.length; i++) {
			result[i] = values[i].id();
		}
		return result;
	}

	public static String[] getAllName() {
		final ErrorHttpEnum[] values = values();
		final String[] result = new String[values.length];
		for (int i = 0; i < values.length; i++) {
			result[i] = values[i].name();
		}
		return result;
	}

	public static String[] getAllLabel() {
		final ErrorHttpEnum[] values = values();
		final String[] result = new String[values.length];
		for (int i = 0; i < values.length; i++) {
			result[i] = values[i].label();
		}
		return result;
	}

	public Integer id() {
		return id;
	}

	public String label() {
		return label;
	}

	public String label(final Object... args) {
		return String.format(this.label, args);
	}
}