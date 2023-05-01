package com.goryaninaa.web.bank.exception;

/**
 * This exception should be thrown if some operation on account failed during runtime.
 */
public class BankRuntimeException extends RuntimeException {
  public BankRuntimeException(final String message, final Throwable cause) {
    super(message, cause);
  }
}
