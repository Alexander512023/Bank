package com.goryaninaa.web.bank.winter.repository.transaction;

import com.goryaninaa.web.bank.model.operation.Operation;
import java.util.List;

public class TransactionDaoStub implements TransactionDao {

  private boolean saveInvoked;
  private boolean findInvoked;

  @Override
  public void save(Operation transaction) {
    saveInvoked = true;
  }

  @Override
  public List<Operation> findTransactionsOfAccount(int accountId) {
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
