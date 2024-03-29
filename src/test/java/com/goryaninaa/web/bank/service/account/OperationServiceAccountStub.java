package com.goryaninaa.web.bank.service.account;

import com.goryaninaa.web.bank.domain.model.account.Account;
import com.goryaninaa.web.bank.domain.model.account.AccountOpenRequisites;
import com.goryaninaa.web.bank.domain.model.operation.OperationRequisites;
import com.goryaninaa.web.bank.domain.service.account.OperationServiceAccount;

public class OperationServiceAccountStub implements OperationServiceAccount {

  private boolean processAccountOpenInvoked;
  private boolean processDepositInvoked;
  private boolean processWithdrawInvoked;

  @Override
  public void processAccountOpen(Account account, AccountOpenRequisites requisites) {
    processAccountOpenInvoked = true;
  }

  @Override
  public void processDeposit(Account account, OperationRequisites requisites) {
    processDepositInvoked = true;
  }

  @Override
  public void processWithdraw(Account account, OperationRequisites requisites) {
    processWithdrawInvoked = true;
  }

  public boolean isProcessAccountOpenInvoked() {
    return processAccountOpenInvoked;
  }

  public boolean isProcessDepositInvoked() {
    return processDepositInvoked;
  }

  public boolean isProcessWithdrawInvoked() {
    return processWithdrawInvoked;
  }
}
