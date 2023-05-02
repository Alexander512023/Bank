package com.goryaninaa.web.bank.winter.repository.account;

import com.goryaninaa.web.bank.domain.model.account.Account;
import com.goryaninaa.web.bank.education.winter.repository.account.AccountDao;
import java.util.Optional;

public class AccountDaoStub implements AccountDao {

  private boolean saveInvoked;
  private boolean updateInvoked;
  private Account account;

  @Override
  public void save(Account account) {
    this.account = account;
    saveInvoked = true;
  }

  @Override
  public void update(Account account) {
    updateInvoked = true;
  }

  @Override
  public Optional<Account> getOneByNumber(int number) {
    return Optional.of(account);
  }

  @Override
  public Optional<Account> findById(int accountId) {
    return Optional.empty();
  }

  public boolean isSaveInvoked() {
    return saveInvoked;
  }

  public boolean isUpdateInvoked() {
    return updateInvoked;
  }
}
