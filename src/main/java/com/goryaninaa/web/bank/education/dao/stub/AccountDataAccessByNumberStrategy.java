package com.goryaninaa.web.bank.education.dao.stub;

import com.goryaninaa.web.bank.model.account.Account;
import com.goryaninaa.web.bank.education.winter.repository.account.AccountAccessStrategyType;
import com.goryaninaa.winter.cache.DataAccessStrategy;
import java.util.Optional;

@SuppressWarnings("SameParameterValue")
public class AccountDataAccessByNumberStrategy implements DataAccessStrategy<Account> {

  private final AccountDaoConcurrentStub accountDAO;

  public AccountDataAccessByNumberStrategy(AccountDaoConcurrentStub accountDAO) {
    this.accountDAO = accountDAO;
  }

  @Override
  public Optional<Account> getData(Object key) {
    Optional<Account> account = Optional.of(accountDAO.getOneByNumber((Integer) key));
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
