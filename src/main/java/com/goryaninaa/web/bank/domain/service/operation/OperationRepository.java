package com.goryaninaa.web.bank.domain.service.operation;

import com.goryaninaa.web.bank.domain.model.operation.Operation;

/**
 * Part of domain layer, this interface should be implemented in data access layer. It meant to be
 * between service and data access objects to implement complicated logic, such as caching, etc.
 * This interface is being used by {@link OperationServicePojo} to store the results of performed
 * actions on operation entities.
 */
public interface OperationRepository {

  void save(Operation operation);

}
