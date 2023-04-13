package com.goryaninaa.web.bank.exception;

/**
 * This exception should be thrown if for some reason account find operation failed.
 */
@SuppressWarnings("unused")
public class AccountFindException extends Exception {

  private static final long serialVersionUID = 4210301599490828786L;

  public AccountFindException(final String message) {
    super(message);
  }

  public AccountFindException(final String message, final Throwable cause) {
    super(message, cause);
  }
}
