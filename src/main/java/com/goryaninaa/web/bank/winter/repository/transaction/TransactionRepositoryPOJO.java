package com.goryaninaa.web.bank.winter.repository.transaction;

import com.goryaninaa.web.bank.model.operation.Operation;
import com.goryaninaa.web.bank.service.operation.OperationRepository;
import java.util.List;

public class TransactionRepositoryPOJO implements OperationRepository {

  private final TransactionDAO transactionDAO;

  public TransactionRepositoryPOJO(TransactionDAO depositDAO) {
    this.transactionDAO = depositDAO;
  }

  @Override
  public void save(Operation transaction) {
    transactionDAO.save(transaction);
  }

  @Override
  public List<Operation> findOperationsOfAccount(int accountId) {
    return transactionDAO.findTransactionsOfAccount(accountId);
  }

}
