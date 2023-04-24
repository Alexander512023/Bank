package com.goryaninaa.web.bank.education.winter.application;

import com.goryaninaa.web.bank.education.winter.repository.operation.OperationRepositoryPojo;
import com.goryaninaa.web.bank.service.account.AccountService;
import com.goryaninaa.web.bank.service.account.AccountServicePojo;
import com.goryaninaa.web.bank.service.operation.OperationService;
import com.goryaninaa.web.bank.service.operation.OperationServicePojo;
import com.goryaninaa.web.bank.service.requisite.RequisiteServicePojo;

/**
 * Service layer provides all domain logic.
 */
@SuppressWarnings("unused")
public class ServiceLayer {
  private final AccountService accountService;
  private final OperationService operationService;
  private final RequisiteServicePojo requisiteService;

  /* default */ ServiceLayer(final RepositoryLayer repLayer) {
    OperationRepositoryPojo operRep = repLayer.getOperationRep();
    requisiteService = new RequisiteServicePojo(repLayer.getClientRep(), operRep);
    operationService = new OperationServicePojo(requisiteService, operRep);
    accountService = new AccountServicePojo(repLayer.getAccountRep(), operationService,
        repLayer.getNumberCapacityRep(), requisiteService);
  }

  /* default */ AccountService getAccountService() {
    return accountService;
  }

  /* default */ OperationService getOperationService() {
    return operationService;
  }

  /* default */ RequisiteServicePojo getRequisiteService() {
    return requisiteService;
  }
}
