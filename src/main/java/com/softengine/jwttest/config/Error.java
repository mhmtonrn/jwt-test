package com.softengine.jwttest.config;

public class Error {
	private int code;
	private String message;

	public Error() {
	}

	public Error( String message,int code) {
		this.code = code;
		this.message = message;
	}

	public int getCode() {
		return code;
	}

	public String getMessage() {
		return message;
	}

	@Override
	public String toString() {
		return "Error [code=" + code + ", message=" + message + "]";
	}
}
