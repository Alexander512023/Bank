package com.goryaninaa.web.bank.dto;

@SuppressWarnings("unused")
public class ErrorDTO {
	
	private final int code;
	private final String message;
	
	public ErrorDTO(int cODE, String message) {
		super();
		code = cODE;
		this.message = message;
	}

	public int getCode() {
		return code;
	}

	public String getMessage() {
		return message;
	}
}
