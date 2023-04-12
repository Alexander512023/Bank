package com.goryaninaa.web.bank.service.operation;

import com.goryaninaa.web.bank.model.operation.Operation;
import java.util.List;

public interface OperationRepository {

  void save(Operation operation);

  List<Operation> findOperationsOfAccount(int accountId);

}
