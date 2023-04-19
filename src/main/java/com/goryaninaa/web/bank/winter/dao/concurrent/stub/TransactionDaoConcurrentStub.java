package com.goryaninaa.web.bank.winter.dao.concurrent.stub;

import com.goryaninaa.web.bank.model.operation.Operation;
import com.goryaninaa.web.bank.winter.repository.transaction.TransactionDao;
import com.sun.jdi.request.DuplicateRequestException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

public class TransactionDaoConcurrentStub implements TransactionDao {

  private static final AtomicInteger idCounter = new AtomicInteger(1);
  private final List<Operation> transactions;

  public TransactionDaoConcurrentStub() {
    this.transactions = new ArrayList<>();
  }

  @Override
  public void save(Operation transaction) {
    for (Operation savedEarlierTransaction : transactions) {
      if (savedEarlierTransaction.equals(transaction)) {
        throw new DuplicateRequestException("This transaction already exists");
      }
    }

    transaction.setOperationId(idCounter.getAndIncrement());

    transactions.add(transaction);
  }

  @Override
  public List<Operation> findTransactionsOfAccount(int accountId) {
    return transactions.stream().filter(t -> t.getAccount().getAccountId() == accountId)
        .collect(Collectors.toList());
  }
}
