package com.goryaninaa.web.bank.education.winter.repository.operation;

import com.goryaninaa.web.bank.domain.model.account.Account;
import com.goryaninaa.web.bank.domain.model.operation.Operation;
import com.goryaninaa.web.bank.domain.service.operation.OperationRepository;
import com.goryaninaa.web.bank.domain.service.requisite.OperationRepositoryRequisite;
import java.util.List;
import java.util.Optional;

/**
 * This is simple implementation of {@link OperationRepository}. {@link OperationDao} is used to
 * operate with transactions id database.
 */
public class OperationRepositoryPojo implements OperationRepository, OperationRepositoryRequisite {

  private final OperationDao operationDao;

  public OperationRepositoryPojo(final OperationDao operationDao) {
    this.operationDao = operationDao;
  }

  @Override
  public void save(final Operation transaction) {
    operationDao.save(transaction);
  }

  @Override
  public Optional<Operation> getTopOp(Account account) {
    List<Operation> operations = operationDao.findOperationsOfAccount(account.getAccountId());
    return Optional.ofNullable(operations.get(operations.size() - 1));
  }
}
