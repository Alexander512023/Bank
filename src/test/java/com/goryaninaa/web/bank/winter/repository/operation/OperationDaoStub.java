package com.goryaninaa.web.bank.winter.repository.operation;

import com.goryaninaa.web.bank.education.winter.repository.operation.OperationDao;
import com.goryaninaa.web.bank.model.operation.Operation;
import java.util.List;

public class OperationDaoStub implements OperationDao {

  private boolean saveInvoked;
  private boolean findInvoked;

  @Override
  public void save(Operation operation) {
    saveInvoked = true;
  }

  @Override
  public List<Operation> findOperationsOfAccount(int accountId) {
    findInvoked = true;
    return null;
  }

  public boolean isSaveInvoked() {
    return saveInvoked;
  }

  public boolean isFindInvoked() {
    return findInvoked;
  }
}
