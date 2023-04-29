package com.goryaninaa.web.bank.service.util;

import com.goryaninaa.web.bank.domain.model.account.Account;
import com.goryaninaa.web.bank.domain.model.account.AccountOpenRequisites;
import com.goryaninaa.web.bank.domain.model.account.AccountType;
import com.goryaninaa.web.bank.domain.model.client.Client;
import com.goryaninaa.web.bank.domain.model.operation.OperationRequisites;
import com.goryaninaa.web.bank.domain.model.operation.ServiceInitiator;

public class RequisitesGenerator {

  public static OperationRequisites defineOperationRequisites() {
    OperationRequisites requisites =
        new OperationRequisites(5, ServiceInitiator.POSTMAN, new Client());
    requisites.setAccountFrom(new Account(1));
    requisites.setAccountRecipient(new Account(2));
    return requisites;
  }

  public static AccountOpenRequisites defineOpenRequisites() {
    return new AccountOpenRequisites(defineOperationRequisites(), AccountType.VKLAD75, 1);
  }
}
