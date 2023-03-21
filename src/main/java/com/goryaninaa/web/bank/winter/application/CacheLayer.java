package com.goryaninaa.web.bank.winter.application;

import com.goryaninaa.web.bank.model.account.Account;
import com.goryaninaa.winter.cache.Cache;
import com.goryaninaa.winter.cache.CacheStorage;
import java.util.Properties;

public class CacheLayer {
    private final Cache<Account> accountCache;

    /* default */ CacheLayer(DaoLayer daoLayer, Properties properties) {
        accountCache = new CacheStorage<>(daoLayer.getAccountDataMediator(), properties);
    }

    /* default */ Cache<Account> getAccountCache() {
        return accountCache;
    }
}
