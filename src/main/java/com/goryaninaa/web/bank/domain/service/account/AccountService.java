package com.goryaninaa.web.bank.domain.service.account;

import com.goryaninaa.web.bank.domain.model.account.Account;
import com.goryaninaa.web.bank.domain.model.account.AccountOpenRequisites;
import com.goryaninaa.web.bank.domain.model.operation.OperationRequisites;
import com.goryaninaa.web.bank.exception.AccountDepositException;
import com.goryaninaa.web.bank.exception.AccountFindException;
import com.goryaninaa.web.bank.exception.AccountOpenException;
import com.goryaninaa.web.bank.exception.AccountWithdrawException;

/**
 * This interface is important part of domain logic. It declares main actions, which can be
 * performed on {@link Account} type objects. This interface should be used by corresponding
 * controller.
 */
public interface AccountService {
  void open(AccountOpenRequisites requisites) throws AccountOpenException;

  void deposit(OperationRequisites requisites)
      throws AccountFindException, AccountDepositException;

  void withdraw(OperationRequisites requisites)
      throws AccountFindException, AccountWithdrawException;

  void transfer(OperationRequisites requisites)
      throws AccountFindException, AccountDepositException, AccountWithdrawException;

  Account findByNumber(int number) throws AccountFindException;
}
