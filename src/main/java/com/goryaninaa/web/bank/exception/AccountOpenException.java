package com.goryaninaa.web.bank.exception;


/**
 * This exception should be thrown if for some reason account open operation failed.
 */
public class AccountOpenException extends Exception {

  private static final long serialVersionUID = -320902687856120305L;

  public AccountOpenException(final String message, final Throwable cause) {
    super(message, cause);
  }
}
