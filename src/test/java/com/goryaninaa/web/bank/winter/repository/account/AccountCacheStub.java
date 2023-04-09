package com.goryaninaa.web.bank.winter.repository.account;

import com.goryaninaa.web.bank.model.account.Account;
import com.goryaninaa.winter.cache.Cache;
import com.goryaninaa.winter.cache.CacheKey;
import java.util.List;
import java.util.Optional;

public class AccountCacheStub implements Cache<Account> {

    private Account account;

    private boolean removeInvoked;
    @Override
    public Optional<Account> getData(CacheKey cacheKey) {
        return Optional.of(account);
    }

    @Override
    public void remove(List<CacheKey> list) {
        removeInvoked = true;
    }

    public boolean isRemoveInvoked() {
        return removeInvoked;
    }

    public void setAccount(Account account) {
        this.account = account;
    }
}
