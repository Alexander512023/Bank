package com.goryaninaa.web.bank.education.winter.repository.account;

import com.goryaninaa.web.bank.domain.model.account.Account;
import java.util.Optional;

/**
 * This interface should be implemented in the data access layer to ensure correct direction of
 * dependencies.
 */
public interface AccountDao {

  void save(Account account);

  void update(Account account);
  Optional<Account> getOneByNumber(int number);

}
