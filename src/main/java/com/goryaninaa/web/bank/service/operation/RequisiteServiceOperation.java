package com.goryaninaa.web.bank.service.operation;

import com.goryaninaa.web.bank.exception.AccountDepositException;
import com.goryaninaa.web.bank.exception.AccountOpenException;
import com.goryaninaa.web.bank.exception.AccountWithdrawException;
import com.goryaninaa.web.bank.model.account.Account;
import com.goryaninaa.web.bank.model.account.AccountOpenRequisites;
import com.goryaninaa.web.bank.model.operation.OperationRequisites;

public interface RequisiteServiceOperation {

  OperationRequisites prepareAccountOpenOperationRequisites(Account account,
                                                            AccountOpenRequisites requisites)
      throws AccountOpenException;

  OperationRequisites prepareDepositOperationRequisites(Account account,
                                                        OperationRequisites requisites)
      throws AccountDepositException;

  OperationRequisites prepareWithdrawOperationRequisites(Account account,
                                                         OperationRequisites requisites)
      throws AccountWithdrawException;

}
