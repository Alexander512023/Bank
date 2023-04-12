package com.goryaninaa.web.bank.winter.repository.account;

import com.goryaninaa.web.bank.model.account.Account;

public interface AccountDAO {

    void save(Account account);

    void update(Account account);
}
