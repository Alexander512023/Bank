package com.goryaninaa.web.bank.winter.application;

import com.goryaninaa.web.bank.model.account.Account;
import com.goryaninaa.web.bank.winter.dao.concurrent.stub.AccountDaoConcurrentStub;
import com.goryaninaa.web.bank.winter.dao.concurrent.stub.AccountDataAccessByNumberStrategy;
import com.goryaninaa.web.bank.winter.dao.concurrent.stub.AccountDataMediator;
import com.goryaninaa.web.bank.winter.dao.concurrent.stub.ClientDaoConcurrentStub;
import com.goryaninaa.web.bank.winter.dao.concurrent.stub.NumberCapacity;
import com.goryaninaa.web.bank.winter.dao.concurrent.stub.TransactionDAOConcurrentStub;
import com.goryaninaa.winter.cache.DataAccessStrategy;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Data access layer of the application. Responsible for database connectivity. Assembles together
 * all the functionality that is needed to complete layer.
 */
public class DataAccessLayer {
  private final AccountDaoConcurrentStub accountDao = new AccountDaoConcurrentStub();
  private final AccountDataMediator accDataMediator;
  private final ClientDaoConcurrentStub clientDao = new ClientDaoConcurrentStub();
  private final NumberCapacity numberCapacity = new NumberCapacity();
  private final TransactionDAOConcurrentStub transactionDao = new TransactionDAOConcurrentStub();

  /* default */ DataAccessLayer() {
    final DataAccessStrategy<Account> accAccessByNum =
        new AccountDataAccessByNumberStrategy(accountDao);
    final Map<String, DataAccessStrategy<Account>> accDataAccesses =
        new ConcurrentHashMap<>();
    accDataAccesses.put(accAccessByNum.getStrategyType(),
        accAccessByNum);
    accDataMediator = new AccountDataMediator(accDataAccesses);
  }

  /* default */ AccountDaoConcurrentStub getAccountDao() {
    return accountDao;
  }

  /* default */ AccountDataMediator getAccDataMediator() {
    return accDataMediator;
  }

  /* default */ ClientDaoConcurrentStub getClientDao() {
    return clientDao;
  }

  /* default */ NumberCapacity getNumberCapacity() {
    return numberCapacity;
  }

  /* default */ TransactionDAOConcurrentStub getTransactionDao() {
    return transactionDao;
  }
}
