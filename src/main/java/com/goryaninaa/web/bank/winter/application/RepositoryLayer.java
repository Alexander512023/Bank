package com.goryaninaa.web.bank.winter.application;

import com.goryaninaa.web.bank.winter.repository.account.AccountNumberExtractStrategy;
import com.goryaninaa.web.bank.winter.repository.account.AccountRepositoryCached;
import com.goryaninaa.web.bank.winter.repository.client.ClientRepositoryPOJO;
import com.goryaninaa.web.bank.winter.repository.number.capacity.NumberCapacityRepositoryPOJO;
import com.goryaninaa.web.bank.winter.repository.transaction.TransactionRepositoryPOJO;
import com.goryaninaa.winter.cache.CacheKeyFactory;
import com.goryaninaa.winter.cache.CacheKeyFactoryStandard;
import com.goryaninaa.winter.cache.KeyExtractStrategy;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class RepositoryLayer {
  private final AccountRepositoryCached accountRep;
  private final ClientRepositoryPOJO clientRep;
  private final NumberCapacityRepositoryPOJO numberCapacityRep;
  private final TransactionRepositoryPOJO transactRep;

  /* default */ RepositoryLayer(CacheLayer cacheLayer, DaoLayer daoLayer) {
    transactRep = new TransactionRepositoryPOJO(daoLayer.getTransactionDAO());
    KeyExtractStrategy accountNumberExtractStrategy = new AccountNumberExtractStrategy();
    Map<String, KeyExtractStrategy> accountKeyExtractStrategies = new ConcurrentHashMap<>();
    accountKeyExtractStrategies.
        put(accountNumberExtractStrategy.getStrategyType(), accountNumberExtractStrategy);
    CacheKeyFactory accountCacheKeyFactory =
        new CacheKeyFactoryStandard(accountKeyExtractStrategies);
    accountRep = new AccountRepositoryCached(cacheLayer.getAccountCache(), daoLayer.getAccountDAO(),
        transactRep, accountCacheKeyFactory);

    clientRep = new ClientRepositoryPOJO(daoLayer.getClientDAO());
    numberCapacityRep = new NumberCapacityRepositoryPOJO(daoLayer.getNumberCapacity());
  }

  /* default */ AccountRepositoryCached getAccountRep() {
    return accountRep;
  }

  /* default */ ClientRepositoryPOJO getClientRep() {
    return clientRep;
  }

  /* default */ NumberCapacityRepositoryPOJO getNumberCapacityRep() {
    return numberCapacityRep;
  }

  /* default */ TransactionRepositoryPOJO getTransactRep() {
    return transactRep;
  }
}
