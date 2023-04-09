package com.goryaninaa.web.bank.model.operation;

import com.goryaninaa.web.bank.model.account.Account;
import com.goryaninaa.web.bank.model.client.Client;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class OperationRequisitesTest {

    @Test
    void enrichShouldCorrectlyDefineBalanceBefore() {
        OperationRequisites requisites = new OperationRequisites(10, ServiceInitiator.POSTMAN,
                new Client());
        Account account = new Account();
        account.setBalance(100);
        requisites.enrich(account, new Client(), OperationType.DEPOSIT);
        boolean depositCorrect = requisites.getBalanceBefore() == 90;
        requisites.enrich(account, new Client(), OperationType.WITHDRAW);
        boolean withdrawCorrect = requisites.getBalanceBefore() == 110;
        requisites.setAmount(-10);
        requisites.enrich(account, new Client(), OperationType.WITHDRAW);
        withdrawCorrect = withdrawCorrect && requisites.getBalanceBefore() == 110;
        boolean testPass = depositCorrect && withdrawCorrect;
        assertTrue(testPass);
    }
}