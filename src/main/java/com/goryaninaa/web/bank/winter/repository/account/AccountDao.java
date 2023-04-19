package com.goryaninaa.web.bank.winter.repository.account;

import com.goryaninaa.web.bank.model.account.Account;

/**
 * This interface should be implemented in the data access layer to ensure correct direction of
 * dependencies.
 */
public interface AccountDao {

  void save(Account account);

  void update(Account account);
}
