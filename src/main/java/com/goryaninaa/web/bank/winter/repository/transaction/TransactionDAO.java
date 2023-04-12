package com.goryaninaa.web.bank.winter.repository.transaction;

import com.goryaninaa.web.bank.model.operation.Operation;
import java.util.List;

public interface TransactionDAO {

  void save(Operation transaction);

  List<Operation> findTransactionsOfAccount(int accountId);
}
