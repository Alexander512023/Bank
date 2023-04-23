package com.goryaninaa.web.bank.education.winter.repository.operation;

import com.goryaninaa.web.bank.model.operation.Operation;
import com.goryaninaa.web.bank.service.operation.OperationRepository;
import java.util.List;

/**
 * This is simple implementation of {@link OperationRepository}. {@link OperationDao} is used to
 * operate with transactions id database.
 */
public class OperationRepositoryPojo implements OperationRepository {

  private final OperationDao operationDao;

  public OperationRepositoryPojo(final OperationDao operationDao) {
    this.operationDao = operationDao;
  }

  @Override
  public void save(final Operation transaction) {
    operationDao.save(transaction);
  }

  @Override
  public List<Operation> findOperationsOfAccount(final int accountId) {
    return operationDao.findOperationsOfAccount(accountId);
  }

}
