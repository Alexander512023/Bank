package com.goryaninaa.web.bank.winter.repository.account;

import com.goryaninaa.web.bank.model.account.Account;
import com.goryaninaa.winter.cache.KeyExtractStrategy;

public class AccountNumberExtractStrategy implements KeyExtractStrategy {

  @Override
  public Object extractKey(Object entity) {
    Account account = (Account) entity;
    return account.getNumber();
  }

  @Override
  public String getStrategyType() {
    return AccountAccessStrategyType.NUMBER;
  }

}
