package com.goryaninaa.web.bank.education.db.h2;

/**
 * This exception is thrown if there is some difficulties on inmemory DB creation.
 */
@SuppressWarnings("unused")
public class H2InitilizationException extends RuntimeException {

  public H2InitilizationException() {
  }

  public H2InitilizationException(String message) {
    super(message);
  }

  public H2InitilizationException(String message, Throwable cause) {
    super(message, cause);
  }

  public H2InitilizationException(Throwable cause) {
    super(cause);
  }

  public H2InitilizationException(String message, Throwable cause, boolean enableSuppression,
                                  boolean writableStackTrace) {
    super(message, cause, enableSuppression, writableStackTrace);
  }
}
