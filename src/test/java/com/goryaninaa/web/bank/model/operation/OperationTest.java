package com.goryaninaa.web.bank.model.operation;

import com.goryaninaa.web.bank.model.account.Account;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.*;

class OperationTest {

    @Test
    void operationDepositShouldCorrectlyDefineAccountsOnCreation() {
        Account recipient = new Account();
        OperationRequisites requisites = new OperationRequisites();
        requisites.setAccountRecipient(recipient);
        requisites.setOperationType(OperationType.DEPOSIT);
        Operation operation = new Operation(requisites);
        assertEquals(recipient, operation.getAccountRecipient());
    }

    @Test
    void operationWithdrawShouldCorrectlyDefineAccountsOnCreation() {
        Account from = new Account();
        OperationRequisites requisites = new OperationRequisites();
        requisites.setAccountFrom(from);
        requisites.setOperationType(OperationType.WITHDRAW);
        Operation operation = new Operation(requisites);
        assertEquals(from, operation.getAccountFrom());
    }

    @Test
    void operationTransferShouldCorrectlyDefineAccountsOnCreation() {
        Account recipient = new Account();
        Account from = new Account();
        OperationRequisites requisites = new OperationRequisites();
        requisites.setAccountRecipient(recipient);
        requisites.setAccountFrom(from);
        requisites.setOperationType(OperationType.TRANSFER);
        Operation operation = new Operation(requisites);
        boolean recipientCorrect = recipient.equals(operation.getAccountRecipient());
        boolean fromCorrect = from.equals(operation.getAccountFrom());
        assertTrue(recipientCorrect && fromCorrect);
    }

    @Test
    void compareToShouldWorkCorrect() {
        Operation operation1 = new Operation();
        operation1.setHistoryNumber(1);
        Operation operation2 = new Operation();
        operation2.setHistoryNumber(2);
        boolean greatestCorrect = operation2.compareTo(operation1) > 0;
        boolean leastCorrect = operation1.compareTo(operation2) < 0;
        assertTrue(greatestCorrect && leastCorrect);
    }

    @Test
    void hashCodeShouldDefineHashCodeCorrectly() {
        int id = 1;
        int amount = 10;
        int historyNumber = 1;
        Account account = new Account();
        account.setNumber(1);
        account.setOpenedAt(LocalDateTime.now());
        Operation operation = new Operation();
        operation.setId(id);
        operation.setAmount(amount);
        operation.setHistoryNumber(historyNumber);
        operation.setAccount(account);
        int expected = Objects.hash(id, amount, historyNumber, account);
        int actual = operation.hashCode();
        assertEquals(expected, actual);
    }

    @Test
    void equalsShouldWorkCorrect() {
        int id = 1;
        int amount = 10;
        int historyNumber = 1;
        Account account = new Account();
        account.setNumber(1);
        account.setOpenedAt(LocalDateTime.now());
        Operation operation1 = new Operation();
        operation1.setId(id);
        operation1.setAmount(amount);
        operation1.setHistoryNumber(historyNumber);
        operation1.setAccount(account);
        Operation operation2 = new Operation();
        operation2.setId(id);
        operation2.setAmount(amount);
        operation2.setHistoryNumber(historyNumber);
        operation2.setAccount(account);
        assertEquals(operation1, operation2);
    }
}