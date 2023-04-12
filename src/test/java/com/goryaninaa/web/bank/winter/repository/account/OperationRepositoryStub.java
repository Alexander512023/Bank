package com.goryaninaa.web.bank.winter.repository.account;

import com.goryaninaa.web.bank.model.operation.Operation;
import com.goryaninaa.web.bank.service.operation.OperationRepository;
import java.util.List;

public class OperationRepositoryStub implements OperationRepository {

  private List<Operation> operationList;

  @Override
  public void save(Operation operation) {

  }

  @Override
  public List<Operation> findOperationsOfAccount(int accountId) {
    return operationList;
  }

  public void setOperationList(List<Operation> operationList) {
    this.operationList = operationList;
  }
}
