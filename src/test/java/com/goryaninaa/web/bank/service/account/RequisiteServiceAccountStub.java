package com.goryaninaa.web.bank.service.account;

import com.goryaninaa.web.bank.model.account.AccountOpenRequisites;

public class RequisiteServiceAccountStub implements RequisiteServiceAccount {
  @Override
  public AccountOpenRequisites prepareAccountOpenRequisites(AccountOpenRequisites requisites) {
    return requisites;
  }
}
