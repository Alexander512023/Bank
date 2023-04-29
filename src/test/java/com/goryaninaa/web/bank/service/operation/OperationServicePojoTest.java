package com.goryaninaa.web.bank.service.operation;

import static org.junit.jupiter.api.Assertions.assertTrue;

import com.goryaninaa.web.bank.domain.service.operation.OperationRepository;
import com.goryaninaa.web.bank.domain.service.operation.OperationServicePojo;
import com.goryaninaa.web.bank.domain.service.operation.RequisiteServiceOperation;
import com.goryaninaa.web.bank.exception.AccountDepositException;
import com.goryaninaa.web.bank.exception.AccountOpenException;
import com.goryaninaa.web.bank.exception.AccountWithdrawException;
import com.goryaninaa.web.bank.domain.model.account.Account;
import com.goryaninaa.web.bank.domain.model.account.AccountOpenRequisites;
import com.goryaninaa.web.bank.domain.model.operation.OperationRequisites;
import com.goryaninaa.web.bank.domain.model.operation.OperationType;
import com.goryaninaa.web.bank.service.util.RequisitesGenerator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class OperationServicePojoTest {

  private static OperationServicePojo operationService;
  private static OperationRepository operationRepository;
  private static RequisiteServiceOperation requisiteServiceOperation;

  @BeforeEach
  void init() {
    operationRepository = new OperationRepositoryStub();
    requisiteServiceOperation = new RequisiteServiceOperationStub();
    operationService = new OperationServicePojo(requisiteServiceOperation, operationRepository);
  }

  @Test
  void processAccountOpenShouldPrepareAndSave() throws AccountOpenException {
    AccountOpenRequisites requisites = RequisitesGenerator.defineOpenRequisites();
    requisites.getOperRequisites().setOperationType(OperationType.DEPOSIT);
    operationService.processAccountOpen(new Account(), requisites);
    final boolean testPassed =
        ((RequisiteServiceOperationStub) requisiteServiceOperation).isOpenInvoked()
            && ((OperationRepositoryStub) operationRepository).isSaveInvoked();
    assertTrue(testPassed);
  }

  @Test
  void processDepositShouldPrepareAndSave() throws AccountDepositException {
    OperationRequisites requisites = RequisitesGenerator.defineOperationRequisites();
    requisites.setOperationType(OperationType.DEPOSIT);
    operationService.processDeposit(new Account(), requisites);
    final boolean testPassed =
        ((RequisiteServiceOperationStub) requisiteServiceOperation).isDepositInvoked()
            && ((OperationRepositoryStub) operationRepository).isSaveInvoked();
    assertTrue(testPassed);
  }

  @Test
  void processWithdrawShouldPrepareAndSave() throws AccountWithdrawException {
    OperationRequisites requisites = RequisitesGenerator.defineOperationRequisites();
    requisites.setOperationType(OperationType.WITHDRAW);
    operationService.processWithdraw(new Account(), requisites);
    final boolean testPassed =
        ((RequisiteServiceOperationStub) requisiteServiceOperation).isWithdrawInvoked()
            && ((OperationRepositoryStub) operationRepository).isSaveInvoked();
    assertTrue(testPassed);
  }

}