package com.goryaninaa.web.bank.domain.service.account;

import com.goryaninaa.web.bank.domain.model.account.Account;
import com.goryaninaa.web.bank.domain.model.operation.OperationRequisites;
import com.goryaninaa.web.bank.exception.AccountDepositException;
import com.goryaninaa.web.bank.exception.AccountOpenException;
import com.goryaninaa.web.bank.exception.AccountWithdrawException;
import com.goryaninaa.web.bank.domain.model.account.AccountOpenRequisites;

/**
 * This interface provides functionality which is needed by {@link AccountServicePojo} to
 * process performed actions with accounts on corresponding operations.
 */
public interface OperationServiceAccount {

  void processAccountOpen(Account account, AccountOpenRequisites requisites)
      throws AccountOpenException;

  void processDeposit(Account account, OperationRequisites requisites)
      throws AccountDepositException;

  void processWithdraw(Account account, OperationRequisites requisites)
      throws AccountWithdrawException;

}
