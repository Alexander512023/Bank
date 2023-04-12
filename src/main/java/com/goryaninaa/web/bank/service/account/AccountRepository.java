package com.goryaninaa.web.bank.service.account;

import com.goryaninaa.web.bank.model.account.Account;
import java.util.Optional;

public interface AccountRepository {

  void save(Account account);

  Optional<Account> findByNumber(int number);

  void update(Account account);

}
