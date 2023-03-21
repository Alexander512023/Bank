package com.goryaninaa.web.bank.winter.application;

import com.goryaninaa.web.bank.model.account.Account;
import com.goryaninaa.web.bank.winter.dao.concurrent.stub.AccountDAO;
import com.goryaninaa.web.bank.winter.dao.concurrent.stub.AccountDataAccessByNumberStrategy;
import com.goryaninaa.web.bank.winter.dao.concurrent.stub.AccountDataMediator;
import com.goryaninaa.web.bank.winter.dao.concurrent.stub.ClientDAO;
import com.goryaninaa.web.bank.winter.dao.concurrent.stub.NumberCapacity;
import com.goryaninaa.web.bank.winter.dao.concurrent.stub.TransactionDAO;
import com.goryaninaa.winter.cache.DataAccessStrategy;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class DaoLayer {
    private final AccountDAO accountDAO = new AccountDAO();
    private final AccountDataMediator accountDataMediator;
    private final ClientDAO clientDAO = new ClientDAO();
    private final NumberCapacity numberCapacity = new NumberCapacity();
    private final TransactionDAO transactionDAO = new TransactionDAO();

    /* default */ AccountDAO getAccountDAO() {
        return accountDAO;
    }

    /* default */ AccountDataMediator getAccountDataMediator() {
        return accountDataMediator;
    }

    /* default */ ClientDAO getClientDAO() {
        return clientDAO;
    }

    /* default */ NumberCapacity getNumberCapacity() {
        return numberCapacity;
    }

    /* default */ TransactionDAO getTransactionDAO() {
        return transactionDAO;
    }

    /* default */ DaoLayer() {
        DataAccessStrategy accountDataAccessByNumber = new AccountDataAccessByNumberStrategy(accountDAO);
        Map<String, DataAccessStrategy<Account>> accountDataAccesses =
                new ConcurrentHashMap<>();
        accountDataAccesses.put(accountDataAccessByNumber.getStrategyType(),
                accountDataAccessByNumber);
        accountDataMediator = new AccountDataMediator(accountDataAccesses);
    }
}
