package com.goryaninaa.web.bank.exception;

/**
 * This exception should be thrown if some operation on account failed during runtime.
 */
@SuppressWarnings("unused")
public class BankRuntimeException extends RuntimeException {
  public BankRuntimeException() {
  }

  public BankRuntimeException(String message) {
    super(message);
  }

  public BankRuntimeException(String message, Throwable cause) {
    super(message, cause);
  }

  public BankRuntimeException(Throwable cause) {
    super(cause);
  }

  public BankRuntimeException(String message, Throwable cause, boolean enableSuppression,
                              boolean writableStackTrace) {
    super(message, cause, enableSuppression, writableStackTrace);
  }
}
