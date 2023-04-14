package com.goryaninaa.web.bank.service.account;

import com.goryaninaa.web.bank.exception.AccountOpenException;
import com.goryaninaa.web.bank.model.account.AccountOpenRequisites;

/**
 * This interface provides functionality which is needed by {@link AccountServicePojo} to
 * perform preparation of account open requisites.
 */
public interface RequisiteServiceAccount {

  AccountOpenRequisites prepareAccountOpenRequisites(AccountOpenRequisites requisites)
      throws AccountOpenException;

}
