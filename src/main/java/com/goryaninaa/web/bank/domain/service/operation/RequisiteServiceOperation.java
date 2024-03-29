package com.goryaninaa.web.bank.domain.service.operation;

import com.goryaninaa.web.bank.domain.model.account.Account;
import com.goryaninaa.web.bank.domain.model.account.AccountOpenRequisites;
import com.goryaninaa.web.bank.domain.model.operation.OperationRequisites;
import com.goryaninaa.web.bank.exception.AccountDepositException;
import com.goryaninaa.web.bank.exception.AccountOpenException;
import com.goryaninaa.web.bank.exception.AccountWithdrawException;

/**
 * This interface provides functionality which is needed by {@link OperationServicePojo} to
 * perform different operations on accounts.
 */
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
