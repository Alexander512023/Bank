package com.goryaninaa.web.bank.exception;

public class AccountOpenException extends Exception {

	private static final long serialVersionUID = -320902687856120305L;

	public AccountOpenException(String message, Throwable cause) {
		super(message, cause);
	}
}
