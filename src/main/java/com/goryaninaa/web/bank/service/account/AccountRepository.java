package com.goryaninaa.web.bank.service.account;

import java.util.Optional;

import com.goryaninaa.web.bank.model.account.Account;

public interface AccountRepository {

	void save(Account account);

	Optional<Account> findByNumber(int number);

	void update(Account account);

}
