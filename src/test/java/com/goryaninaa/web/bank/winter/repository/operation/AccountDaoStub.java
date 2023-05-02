package com.goryaninaa.web.bank.winter.repository.operation;

import com.goryaninaa.web.bank.domain.model.account.Account;
import com.goryaninaa.web.bank.education.winter.repository.account.AccountDao;
import java.util.Optional;

public class AccountDaoStub implements AccountDao {
  @Override
  public void save(Account account) {

  }

  @Override
  public void update(Account account) {

  }

  @Override
  public Optional<Account> getOneByNumber(int number) {
    return Optional.empty();
  }

  @Override
  public Optional<Account> findById(int accountId) {
    return Optional.empty();
  }
}
