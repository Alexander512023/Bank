package com.goryaninaa.web.bank.service.account;

import com.goryaninaa.web.bank.model.account.Account;
import java.util.Optional;

/**
 * Part of domain layer, this interface should be implemented in data access layer. It meant to be
 * between service and data access objects to implement complicated logic, such as caching, etc.
 * This interface is being used by {@link AccountServicePojo} to store the results of performed
 * actions on account entities.
 */
public interface AccountRepository {

  void save(Account account);

  Optional<Account> findByNumber(int number);

  void update(Account account);

}
