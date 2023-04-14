package com.goryaninaa.web.bank.service.operation;

import com.goryaninaa.web.bank.exception.AccountDepositException;
import com.goryaninaa.web.bank.exception.AccountOpenException;
import com.goryaninaa.web.bank.exception.AccountWithdrawException;
import com.goryaninaa.web.bank.model.account.Account;
import com.goryaninaa.web.bank.model.account.AccountOpenRequisites;
import com.goryaninaa.web.bank.model.operation.OperationRequisites;
import com.goryaninaa.web.bank.service.account.OperationServiceAccount;

/**
 * This interface extends logic of {@link OperationServiceAccount} and could be further extended
 * if there will be need to work with operations directly.
 */
public interface OperationService extends OperationServiceAccount {
  @Override
  void processAccountOpen(Account account, AccountOpenRequisites requisites)
      throws AccountOpenException;

  @Override
  void processDeposit(Account account, OperationRequisites requisites)
      throws AccountDepositException;

  @Override
  void processWithdraw(Account account, OperationRequisites requisites)
      throws AccountWithdrawException;
}
