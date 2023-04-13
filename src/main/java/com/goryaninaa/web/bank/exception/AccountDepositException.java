package com.goryaninaa.web.bank.exception;

/**
 * This exception should be thrown if for some reason deposit operation failed.
 */
public class AccountDepositException extends Exception {

  private static final long serialVersionUID = -5909711284210946914L;

  public AccountDepositException(final String message, final Throwable cause) {
    super(message, cause);
  }
}
