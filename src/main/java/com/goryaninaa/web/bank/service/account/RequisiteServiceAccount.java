package com.goryaninaa.web.bank.service.account;

import com.goryaninaa.web.bank.exception.AccountOpenException;
import com.goryaninaa.web.bank.model.account.AccountOpenRequisites;

public interface RequisiteServiceAccount {

  AccountOpenRequisites prepareAccountOpenRequisites(AccountOpenRequisites requisites)
      throws AccountOpenException;

}
