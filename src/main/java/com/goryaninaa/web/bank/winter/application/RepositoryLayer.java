package com.goryaninaa.web.bank.winter.application;

import com.goryaninaa.web.bank.winter.repository.account.AccountNumberExtractStrategy;
import com.goryaninaa.web.bank.winter.repository.account.AccountRepositoryCached;
import com.goryaninaa.web.bank.winter.repository.client.ClientRepositoryPojo;
import com.goryaninaa.web.bank.winter.repository.number.capacity.NumberCapacityRepositoryPojo;
import com.goryaninaa.web.bank.winter.repository.transaction.TransactionRepositoryPojo;
import com.goryaninaa.winter.cache.CacheKeyFactory;
import com.goryaninaa.winter.cache.CacheKeyFactoryStandard;
import com.goryaninaa.winter.cache.KeyExtractStrategy;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Repository layer is responsible to join service and data access layers. It also plugs in
 * caching functionality.
 */
public class RepositoryLayer {
  private final AccountRepositoryCached accountRep;
  private final ClientRepositoryPojo clientRep;
  private final NumberCapacityRepositoryPojo numberCapacityRep;
  private final TransactionRepositoryPojo transactRep;

  /* default */ RepositoryLayer(
      final CacheLayer cacheLayer, final DataAccessLayer dataAccessLayer) {
    transactRep = new TransactionRepositoryPojo(dataAccessLayer.getTransactionDao());
    final KeyExtractStrategy accNumExtract = new AccountNumberExtractStrategy();
    final Map<String, KeyExtractStrategy> accKeyExtracts = new ConcurrentHashMap<>();
    accKeyExtracts.put(accNumExtract.getStrategyType(), accNumExtract);
    final CacheKeyFactory accCacheKeyFact = new CacheKeyFactoryStandard(accKeyExtracts);
    accountRep = new AccountRepositoryCached(cacheLayer.getAccountCache(),
        dataAccessLayer.getAccountDao(), transactRep, accCacheKeyFact);
    clientRep = new ClientRepositoryPojo(dataAccessLayer.getClientDao());
    numberCapacityRep = new NumberCapacityRepositoryPojo(dataAccessLayer.getNumberCapacity());
  }

  /* default */ AccountRepositoryCached getAccountRep() {
    return accountRep;
  }

  /* default */ ClientRepositoryPojo getClientRep() {
    return clientRep;
  }

  /* default */ NumberCapacityRepositoryPojo getNumberCapacityRep() {
    return numberCapacityRep;
  }

  /* default */ TransactionRepositoryPojo getTransactRep() {
    return transactRep;
  }
}
