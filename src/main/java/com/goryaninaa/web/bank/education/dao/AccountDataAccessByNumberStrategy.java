package com.goryaninaa.web.bank.education.dao;

import com.goryaninaa.web.bank.domain.model.account.Account;
import com.goryaninaa.web.bank.education.winter.repository.account.AccountAccessStrategyType;
import com.goryaninaa.web.bank.education.winter.repository.account.AccountDao;
import com.goryaninaa.winter.cache.DataAccessStrategy;
import java.util.Optional;

@SuppressWarnings("SameParameterValue")
public class AccountDataAccessByNumberStrategy implements DataAccessStrategy<Account> {

  private final AccountDao accountDAO;

  public AccountDataAccessByNumberStrategy(AccountDao accountDAO) {
    this.accountDAO = accountDAO;
  }

  @Override
  public Optional<Account> getData(Object key) {
    Optional<Account> account = accountDAO.getOneByNumber((Integer) key);
    sleep(2000);
    return account;
  }

  private void sleep(int milliseconds) {
    try {
      Thread.sleep(milliseconds);
    } catch (InterruptedException e) {
      e.printStackTrace();
      Thread.currentThread().interrupt();
    }
  }

  @Override
  public String getStrategyType() {
    return AccountAccessStrategyType.NUMBER;
  }

}
