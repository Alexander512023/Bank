package com.goryaninaa.web.bank.service.requisite;

import com.goryaninaa.web.bank.domain.model.account.Account;
import com.goryaninaa.web.bank.domain.model.operation.Operation;
import com.goryaninaa.web.bank.domain.service.requisite.OperationRepositoryRequisite;
import java.util.Optional;

public class OperationRepositoryRequisiteStub implements OperationRepositoryRequisite {
  @Override
  public Optional<Operation> getTopOp(Account account) {
    Operation operation = new Operation();
    operation.setHistoryNumber(1);
    return Optional.of(operation);
  }
}
