package com.goryaninaa.web.bank.winter.repository.account;

import com.goryaninaa.web.bank.model.account.Account;

public class AccountDAOStub implements AccountDAO {

  private boolean saveInvoked;
  private boolean updateInvoked;

  @Override
  public void save(Account account) {
    saveInvoked = true;
  }

  @Override
  public void update(Account account) {
    updateInvoked = true;
  }

  public boolean isSaveInvoked() {
    return saveInvoked;
  }

  public boolean isUpdateInvoked() {
    return updateInvoked;
  }
}
