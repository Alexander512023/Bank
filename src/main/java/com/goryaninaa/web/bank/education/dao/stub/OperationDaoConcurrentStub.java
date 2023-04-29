package com.goryaninaa.web.bank.education.dao.stub;

import com.goryaninaa.web.bank.domain.model.operation.Operation;
import com.goryaninaa.web.bank.education.winter.repository.operation.OperationDao;
import com.sun.jdi.request.DuplicateRequestException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

public class OperationDaoConcurrentStub implements OperationDao {

  private static final AtomicInteger idCounter = new AtomicInteger(1);
  private final List<Operation> transactions;

  public OperationDaoConcurrentStub() {
    this.transactions = new ArrayList<>();
  }

  @Override
  public void save(Operation operation) {
    for (Operation savedEarlierTransaction : transactions) {
      if (savedEarlierTransaction.equals(operation)) {
        throw new DuplicateRequestException("This transaction already exists");
      }
    }

    operation.setOperationId(idCounter.getAndIncrement());

    transactions.add(operation);
  }

  @Override
  public List<Operation> findOperationsOfAccount(int accountId) {
    return transactions.stream().filter(t -> t.getAccount().getAccountId() == accountId)
        .collect(Collectors.toList());
  }
}
