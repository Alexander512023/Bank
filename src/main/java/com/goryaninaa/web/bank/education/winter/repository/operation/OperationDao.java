package com.goryaninaa.web.bank.education.winter.repository.operation;

import com.goryaninaa.web.bank.domain.model.operation.Operation;
import java.util.List;

/**
 * This interface should be implemented in the data access layer to ensure correct direction of
 * dependencies.
 */
public interface OperationDao {

  void save(Operation operation);

  List<Operation> findOperationsOfAccount(int accountId);
}
