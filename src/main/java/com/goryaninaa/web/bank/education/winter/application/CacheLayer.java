package com.goryaninaa.web.bank.education.winter.application;

import com.goryaninaa.web.bank.domain.model.account.Account;
import com.goryaninaa.winter.cache.Cache;
import com.goryaninaa.winter.cache.CacheStorage;
import java.util.Properties;

/**
 * Cache layer of the application. Provides account cache configured according to passed properties.
 */
public class CacheLayer {
  private final Cache<Account> accountCache;

  /* default */ CacheLayer(final DataAccessLayer dataAccessLayer, final Properties properties) {
    accountCache = new CacheStorage<>(dataAccessLayer.getAccDataMediator(), properties);
  }

  /* default */ Cache<Account> getAccountCache() {
    return accountCache;
  }
}
