package com.goryaninaa.web.bank.education.winter.repository.account;

import com.goryaninaa.web.bank.model.account.Account;
import com.goryaninaa.winter.cache.KeyExtractStrategy;

/**
 * This class is a part of Strategy GOF pattern. It allows to extract number as key for
 * {@link Account} objects.
 */
public class AccountNumberExtractStrategy implements KeyExtractStrategy {

  @Override
  public Object extractKey(final Object entity) {
    return ((Account) entity).getNumber();
  }

  @Override
  public String getStrategyType() {
    return AccountAccessStrategyType.NUMBER;
  }

}
