package com.goryaninaa.web.bank.service.operation;

import com.goryaninaa.web.bank.domain.model.account.Account;
import com.goryaninaa.web.bank.domain.model.account.AccountOpenRequisites;
import com.goryaninaa.web.bank.domain.model.client.Client;
import com.goryaninaa.web.bank.domain.model.operation.OperationRequisites;
import com.goryaninaa.web.bank.domain.model.operation.OperationType;
import com.goryaninaa.web.bank.domain.model.operation.ServiceInitiator;
import com.goryaninaa.web.bank.domain.service.operation.RequisiteServiceOperation;

public class RequisiteServiceOperationStub implements RequisiteServiceOperation {

  private boolean openInvoked;
  private boolean depositInvoked;
  private boolean withdrawInvoked;

  @Override
  public OperationRequisites prepareAccountOpenOperationRequisites(
      Account account, AccountOpenRequisites requisites) {
    openInvoked = true;
    OperationRequisites operationRequisites = new OperationRequisites(5,
        ServiceInitiator.POSTMAN, new Client());
    operationRequisites.setOperationType(OperationType.DEPOSIT);
    operationRequisites.setAccountFrom(new Account(1));
    operationRequisites.setAccountRecipient(new Account(2));
    return operationRequisites;
  }

  @Override
  public OperationRequisites prepareDepositOperationRequisites(
      Account account, OperationRequisites requisites) {
    depositInvoked = true;
    return requisites;
  }

  @Override
  public OperationRequisites prepareWithdrawOperationRequisites(
      Account account, OperationRequisites requisites) {
    withdrawInvoked = true;
    return requisites;
  }

  public boolean isOpenInvoked() {
    return openInvoked;
  }

  public boolean isDepositInvoked() {
    return depositInvoked;
  }

  public boolean isWithdrawInvoked() {
    return withdrawInvoked;
  }
}
