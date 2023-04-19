package com.goryaninaa.web.bank.winter.repository.transaction;

import com.goryaninaa.web.bank.model.operation.Operation;
import java.util.List;

/**
 * This interface should be implemented in the data access layer to ensure correct direction of
 * dependencies.
 */
public interface TransactionDao {

  void save(Operation transaction);

  List<Operation> findTransactionsOfAccount(int accountId);
}
