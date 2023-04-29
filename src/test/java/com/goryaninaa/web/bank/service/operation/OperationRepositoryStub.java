package com.goryaninaa.web.bank.service.operation;

import com.goryaninaa.web.bank.domain.model.operation.Operation;
import com.goryaninaa.web.bank.domain.service.operation.OperationRepository;

public class OperationRepositoryStub implements OperationRepository {

  private boolean saveInvoked;

  @Override
  public void save(Operation operation) {
    saveInvoked = true;
  }

  public boolean isSaveInvoked() {
    return saveInvoked;
  }

}
