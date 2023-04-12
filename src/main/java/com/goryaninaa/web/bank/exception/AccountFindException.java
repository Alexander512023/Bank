package com.goryaninaa.web.bank.exception;

@SuppressWarnings("unused")
public class AccountFindException extends Exception {

  private static final long serialVersionUID = 4210301599490828786L;

  public AccountFindException(String message) {
    super(message);
  }

  public AccountFindException(String message, Throwable cause) {
    super(message, cause);
  }
}
