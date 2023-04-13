package com.goryaninaa.web.bank.model.account;

/**
 * Enumeration of available account types.
 */
@SuppressWarnings("unused")
public enum AccountType {

  VKLAD100(100),
  VKLAD75(75);

  private final int rate;

  AccountType(final int rate) {
    this.rate = rate;
  }


  public int getRate() {
    return rate;
  }
}
