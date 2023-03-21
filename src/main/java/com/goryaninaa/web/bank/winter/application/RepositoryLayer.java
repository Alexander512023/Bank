package com.goryaninaa.web.bank.winter.application;

import com.goryaninaa.web.bank.service.account.AccountRepository;
import com.goryaninaa.web.bank.winter.repository.AccountNumberExtractStrategy;
import com.goryaninaa.web.bank.winter.repository.AccountRepositoryCached;
import com.goryaninaa.web.bank.winter.repository.ClientRepositoryPOJO;
import com.goryaninaa.web.bank.winter.repository.NumberCapacityRepositoryPOJO;
import com.goryaninaa.web.bank.winter.repository.TransactionRepositoryPOJO;
import com.goryaninaa.winter.cache.CacheKeyFactory;
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
        CacheKeyFactory accountCacheKeyFactory = new CacheKeyFactory(accountKeyExtractStrategies);
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
