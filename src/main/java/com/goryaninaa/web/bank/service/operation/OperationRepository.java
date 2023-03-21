package com.goryaninaa.web.bank.service.operation;

import java.util.List;

import com.goryaninaa.web.bank.model.operation.Operation;

public interface OperationRepository {

	void save(Operation operation);
	
	List<Operation> findOperationsOfAccount(int accountId);

}
