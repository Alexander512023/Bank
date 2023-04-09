package com.goryaninaa.web.bank.winter.application;

import com.goryaninaa.web.bank.service.account.AccountService;
import com.goryaninaa.web.bank.service.account.AccountServicePojo;
import com.goryaninaa.web.bank.service.operation.OperationService;
import com.goryaninaa.web.bank.service.operation.OperationServicePojo;
import com.goryaninaa.web.bank.service.requisite.RequisiteServicePojo;

public class ServiceLayer {
    private final AccountService accountService;
    private final OperationService operationService;
    private final RequisiteServicePojo requisiteService;

    /* default */ ServiceLayer(RepositoryLayer repLayer) {
        requisiteService = new RequisiteServicePojo(repLayer.getClientRep());
        operationService = new OperationServicePojo(requisiteService, repLayer.getTransactRep());
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