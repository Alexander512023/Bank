package com.goryaninaa.web.bank.winter.application;

import com.goryaninaa.web.bank.model.account.Account;
import com.goryaninaa.web.bank.winter.dao.concurrent.stub.AccountDAOConcurrentStub;
import com.goryaninaa.web.bank.winter.dao.concurrent.stub.AccountDataAccessByNumberStrategy;
import com.goryaninaa.web.bank.winter.dao.concurrent.stub.AccountDataMediator;
import com.goryaninaa.web.bank.winter.dao.concurrent.stub.ClientDAOConcurrentStub;
import com.goryaninaa.web.bank.winter.dao.concurrent.stub.NumberCapacity;
import com.goryaninaa.web.bank.winter.dao.concurrent.stub.TransactionDAOConcurrentStub;
import com.goryaninaa.winter.cache.DataAccessStrategy;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class DaoLayer {
  private final AccountDAOConcurrentStub accountDAO = new AccountDAOConcurrentStub();
  private final AccountDataMediator accountDataMediator;
  private final ClientDAOConcurrentStub clientDAO = new ClientDAOConcurrentStub();
  private final NumberCapacity numberCapacity = new NumberCapacity();
  private final TransactionDAOConcurrentStub transactionDAO = new TransactionDAOConcurrentStub();

  /* default */ DaoLayer() {
    DataAccessStrategy<Account> accountDataAccessByNumber =
        new AccountDataAccessByNumberStrategy(accountDAO);
    Map<String, DataAccessStrategy<Account>> accountDataAccesses =
        new ConcurrentHashMap<>();
    accountDataAccesses.put(accountDataAccessByNumber.getStrategyType(),
        accountDataAccessByNumber);
    accountDataMediator = new AccountDataMediator(accountDataAccesses);
  }

  /* default */ AccountDAOConcurrentStub getAccountDAO() {
    return accountDAO;
  }

  /* default */ AccountDataMediator getAccountDataMediator() {
    return accountDataMediator;
  }

  /* default */ ClientDAOConcurrentStub getClientDAO() {
    return clientDAO;
  }

  /* default */ NumberCapacity getNumberCapacity() {
    return numberCapacity;
  }

  /* default */ TransactionDAOConcurrentStub getTransactionDAO() {
    return transactionDAO;
  }
}
