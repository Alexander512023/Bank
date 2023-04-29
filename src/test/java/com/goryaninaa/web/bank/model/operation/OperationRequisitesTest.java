package com.goryaninaa.web.bank.model.operation;

import static org.junit.jupiter.api.Assertions.assertTrue;

import com.goryaninaa.web.bank.domain.model.account.Account;
import com.goryaninaa.web.bank.domain.model.client.Client;
import com.goryaninaa.web.bank.domain.model.operation.OperationRequisites;
import com.goryaninaa.web.bank.domain.model.operation.OperationType;
import com.goryaninaa.web.bank.domain.model.operation.ServiceInitiator;
import org.junit.jupiter.api.Test;

class OperationRequisitesTest {

  @Test
  void enrichShouldCorrectlyDefineBalanceBefore() {
    OperationRequisites requisites = new OperationRequisites(10, ServiceInitiator.POSTMAN,
        new Client());
    Account account = new Account();
    account.setBalance(100);
    requisites.enrich(account, new Client(), OperationType.DEPOSIT, 1);
    boolean depositCorrect = requisites.getBalanceBefore() == 90;
    requisites.enrich(account, new Client(), OperationType.WITHDRAW, 1);
    boolean withdrawCorrect = requisites.getBalanceBefore() == 110;
    requisites.setAmount(-10);
    requisites.enrich(account, new Client(), OperationType.WITHDRAW, 1);
    withdrawCorrect = withdrawCorrect && requisites.getBalanceBefore() == 110;
    boolean testPass = depositCorrect && withdrawCorrect;
    assertTrue(testPass);
  }
}