package com.goryaninaa.web.bank.service.operation;

import com.goryaninaa.web.bank.model.operation.Operation;
import java.util.List;

public class OperationRepositoryStub implements OperationRepository {

  private boolean saveInvoked;

  @Override
  public void save(Operation operation) {
    saveInvoked = true;
  }

  @Override
  public List<Operation> findOperationsOfAccount(int accountId) {
    return null;
  }

  public boolean isSaveInvoked() {
    return saveInvoked;
  }

}
