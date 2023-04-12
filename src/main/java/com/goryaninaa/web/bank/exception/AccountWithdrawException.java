package com.goryaninaa.web.bank.exception;

public class AccountWithdrawException extends Exception {

  private static final long serialVersionUID = 608911924743221891L;

  public AccountWithdrawException(String message) {
    super(message);
  }

  public AccountWithdrawException(String message, Throwable cause) {
    super(message, cause);
  }
}
