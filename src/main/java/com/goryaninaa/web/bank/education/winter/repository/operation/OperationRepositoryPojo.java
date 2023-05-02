package com.goryaninaa.web.bank.education.winter.repository.operation;

import com.goryaninaa.web.bank.domain.model.account.Account;
import com.goryaninaa.web.bank.domain.model.operation.Operation;
import com.goryaninaa.web.bank.domain.service.operation.OperationRepository;
import com.goryaninaa.web.bank.domain.service.requisite.OperationRepositoryRequisite;
import com.goryaninaa.web.bank.education.winter.repository.account.AccountDao;
import java.util.List;
import java.util.Optional;

/**
 * This is simple implementation of {@link OperationRepository}. {@link OperationDao} is used to
 * operate with transactions id database.
 */
public class OperationRepositoryPojo implements OperationRepository, OperationRepositoryRequisite {

  private final OperationDao operationDao;
  private final AccountDao accDao;


  public OperationRepositoryPojo(final OperationDao operationDao, AccountDao accDao) {
    this.operationDao = operationDao;
    this.accDao = accDao;
  }

  @Override
  public void save(final Operation operation) {
    if (operation.getAccountFrom() != null) {
      operation.setAccountFrom(
          accDao.getOneByNumber(operation.getAccountFrom().getNumber()).orElseThrow());
    }
    if (operation.getAccountRecipient() != null) {
      operation.setAccountRecipient(
          accDao.getOneByNumber(operation.getAccountRecipient().getNumber()).orElseThrow());
    }
    operationDao.save(operation);
  }

  @Override
  public Optional<Operation> getTopOp(Account account) {
    List<Operation> operations = operationDao.findOperationsOfAccount(account.getAccountId());
    return Optional.ofNullable(operations.get(operations.size() - 1));
  }
}
