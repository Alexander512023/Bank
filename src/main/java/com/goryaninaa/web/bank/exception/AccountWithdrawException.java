package com.goryaninaa.web.bank.exception;

/**
 * This exception should be thrown if for some reason withdraw operation failed.
 */
public class AccountWithdrawException extends Exception {

  private static final long serialVersionUID = 608911924743221891L;

  public AccountWithdrawException(final String message) {
    super(message);
  }

  public AccountWithdrawException(final String message, final Throwable cause) {
    super(message, cause);
  }
}
