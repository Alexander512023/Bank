package com.goryaninaa.web.bank.winter.repository.transaction;

import com.goryaninaa.web.bank.model.operation.Operation;
import com.goryaninaa.web.bank.service.operation.OperationRepository;
import java.util.List;

/**
 * This is simple implementation of {@link OperationRepository}. {@link TransactionDao} is used to
 * operate with transactions id database.
 */
public class TransactionRepositoryPojo implements OperationRepository {

  private final TransactionDao transactionDao;

  public TransactionRepositoryPojo(final TransactionDao transactionDao) {
    this.transactionDao = transactionDao;
  }

  @Override
  public void save(final Operation transaction) {
    transactionDao.save(transaction);
  }

  @Override
  public List<Operation> findOperationsOfAccount(final int accountId) {
    return transactionDao.findTransactionsOfAccount(accountId);
  }

}
