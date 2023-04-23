package com.goryaninaa.web.bank.education.winter.application;

import com.goryaninaa.web.bank.education.dao.stub.AccountDaoConcurrentStub;
import com.goryaninaa.web.bank.education.dao.stub.AccountDataAccessByNumberStrategy;
import com.goryaninaa.web.bank.education.dao.stub.AccountDataMediator;
import com.goryaninaa.web.bank.education.dao.stub.ClientDaoConcurrentStub;
import com.goryaninaa.web.bank.education.dao.stub.OperationDaoConcurrentStub;
import com.goryaninaa.web.bank.education.third.party.NumberCapacityApplication;
import com.goryaninaa.web.bank.education.winter.repository.number.capacity.NumberCapacity;
import com.goryaninaa.web.bank.model.account.Account;
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
  private final NumberCapacity numberCapacity = new NumberCapacityApplication();
  private final OperationDaoConcurrentStub transactionDao = new OperationDaoConcurrentStub();

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

  /* default */ OperationDaoConcurrentStub getTransactionDao() {
    return transactionDao;
  }
}
