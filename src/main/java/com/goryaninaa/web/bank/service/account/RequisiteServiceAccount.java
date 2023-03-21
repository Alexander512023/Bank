package com.goryaninaa.web.bank.service.account;

import com.goryaninaa.web.bank.model.account.AccountOpenRequisites;
import com.goryaninaa.web.bank.exception.AccountOpenException;

public interface RequisiteServiceAccount {

	AccountOpenRequisites prepareAccountOpenRequisites(AccountOpenRequisites requisites) throws AccountOpenException;

}
