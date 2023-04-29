package com.goryaninaa.web.bank.exception;

/**
 * This exception should be thrown if some operation on account failed during runtime.
 */
public class AccountRuntimeException extends RuntimeException {
  public AccountRuntimeException(final String message, final Throwable cause) {
    super(message, cause);
  }
}
