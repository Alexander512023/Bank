package com.goryaninaa.web.bank.service.account;

import com.goryaninaa.web.bank.model.account.Account;
import java.util.Optional;

public class AccountRepositoryStub implements  AccountRepository {

    private boolean saveInvoked;
    private Account account1;
    private Account account2;

    public AccountRepositoryStub() {
        this.account1 = new Account(1);
        this.account1.setBalance(10);
        this.account2 = new Account(2);
        this.account2.setBalance(10);
    }
    @Override
    public void save(Account account) {
        saveInvoked = true;
    }

    @Override
    public Optional<Account> findByNumber(int number) {
        Optional<Account> account = Optional.empty();
        if (number == 1) {
            account = Optional.of(account1);
        } else if (number == 2) {
            account = Optional.of(account2);
        }
        return account;
    }

    @Override
    public void update(Account account) {
        if (account.getNumber() == 1) {
            this.account1 = account;
        } else if (account.getNumber() == 2) {
            this.account2 = account;
        }
    }

    public boolean isSaveInvoked() {
        return saveInvoked;
    }
}
