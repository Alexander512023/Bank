package com.goryaninaa.web.bank.model.account;

@SuppressWarnings("unused")
public enum AccountType {

  VKLAD100(100),
  VKLAD75(75);

  private final int rate;

  AccountType(int rate) {
    this.rate = rate;
  }


  public int getRate() {
    return rate;
  }
}
