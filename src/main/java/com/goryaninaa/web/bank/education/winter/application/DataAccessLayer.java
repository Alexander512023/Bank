package com.goryaninaa.web.bank.education.winter.application;

import com.goryaninaa.web.bank.domain.model.account.Account;
import com.goryaninaa.web.bank.education.dao.AccountDataAccessByNumberStrategy;
import com.goryaninaa.web.bank.education.dao.AccountDataMediator;
import com.goryaninaa.web.bank.education.dao.jdbc.AccountDaoJdbc;
import com.goryaninaa.web.bank.education.dao.jdbc.ClientDaoJdbc;
import com.goryaninaa.web.bank.education.dao.jdbc.OperationDaoJdbc;
import com.goryaninaa.web.bank.education.third.party.NumberCapacityApplication;
import com.goryaninaa.web.bank.education.winter.repository.account.AccountDao;
import com.goryaninaa.web.bank.education.winter.repository.client.ClientDao;
import com.goryaninaa.web.bank.education.winter.repository.number.capacity.NumberCapacity;
import com.goryaninaa.web.bank.education.winter.repository.operation.OperationDao;
import com.goryaninaa.winter.cache.DataAccessStrategy;
import com.goryaninaa.winter.connection.pool.BasicConnectionPool;
import com.goryaninaa.winter.connection.pool.ConnectionPool;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Data access layer of the application. Responsible for database connectivity. Assembles together
 * all the functionality that is needed to complete layer.
 */
public class DataAccessLayer {
  private final AccountDao accountDao;
  private final AccountDataMediator accDataMediator;
  private final ClientDao clientDao;
  private final NumberCapacity numberCapacity = new NumberCapacityApplication();
  private final OperationDao operationDao;

  /* default */ DataAccessLayer(Properties properties) {
    ConnectionPool connectionPool = new BasicConnectionPool(properties);
    accountDao = new AccountDaoJdbc(connectionPool);
    clientDao = new ClientDaoJdbc(connectionPool);
    operationDao = new OperationDaoJdbc(connectionPool);
    final DataAccessStrategy<Account> accAccessByNum =
        new AccountDataAccessByNumberStrategy(accountDao);
    final Map<String, DataAccessStrategy<Account>> accDataAccesses =
        new ConcurrentHashMap<>();
    accDataAccesses.put(accAccessByNum.getStrategyType(),
        accAccessByNum);
    accDataMediator = new AccountDataMediator(accDataAccesses);
  }

  /* default */ AccountDao getAccountDao() {
    return accountDao;
  }

  /* default */ AccountDataMediator getAccDataMediator() {
    return accDataMediator;
  }

  /* default */ ClientDao getClientDao() {
    return clientDao;
  }

  /* default */ NumberCapacity getNumberCapacity() {
    return numberCapacity;
  }

  /* default */ OperationDao getOperationDao() {
    return operationDao;
  }
}
