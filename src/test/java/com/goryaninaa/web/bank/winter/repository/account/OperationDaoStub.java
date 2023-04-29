package com.goryaninaa.web.bank.winter.repository.account;

import com.goryaninaa.web.bank.domain.model.operation.Operation;
import com.goryaninaa.web.bank.education.winter.repository.operation.OperationDao;
import java.util.List;

public class OperationDaoStub implements OperationDao {

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
