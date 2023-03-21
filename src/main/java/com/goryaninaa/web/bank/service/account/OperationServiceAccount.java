package com.goryaninaa.web.bank.service.account;

import com.goryaninaa.web.bank.model.account.Account;
import com.goryaninaa.web.bank.model.account.AccountOpenRequisites;
import com.goryaninaa.web.bank.model.operation.OperationRequisites;
import com.goryaninaa.web.bank.exception.AccountDepositException;
import com.goryaninaa.web.bank.exception.AccountOpenException;
import com.goryaninaa.web.bank.exception.AccountWithdrawException;

public interface OperationServiceAccount {

	void processAccountOpen(Account account, AccountOpenRequisites requisites) throws AccountOpenException;

	void processDeposit(Account account, OperationRequisites requisites) throws AccountDepositException;

	void processWithdraw(Account account, OperationRequisites requisites) throws AccountWithdrawException;

}
