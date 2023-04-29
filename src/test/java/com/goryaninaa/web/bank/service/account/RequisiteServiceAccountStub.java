package com.goryaninaa.web.bank.service.account;

import com.goryaninaa.web.bank.domain.model.account.AccountOpenRequisites;
import com.goryaninaa.web.bank.domain.service.account.RequisiteServiceAccount;

public class RequisiteServiceAccountStub implements RequisiteServiceAccount {
  @Override
  public AccountOpenRequisites prepareAccountOpenRequisites(AccountOpenRequisites requisites) {
    return requisites;
  }
}
