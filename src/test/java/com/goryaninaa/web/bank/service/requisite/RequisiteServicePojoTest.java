package com.goryaninaa.web.bank.service.requisite;

import com.goryaninaa.web.bank.exception.AccountDepositException;
import com.goryaninaa.web.bank.exception.AccountOpenException;
import com.goryaninaa.web.bank.exception.AccountWithdrawException;
import com.goryaninaa.web.bank.model.account.Account;
import com.goryaninaa.web.bank.model.account.AccountOpenRequisites;
import com.goryaninaa.web.bank.model.client.Client;
import com.goryaninaa.web.bank.model.operation.OperationRequisites;
import com.goryaninaa.web.bank.model.operation.OperationType;
import com.goryaninaa.web.bank.service.util.RequisitesGenerator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RequisiteServicePojoTest {

    private static RequisiteServicePojo requisiteService;
    private static ClientRepositoryRequisite clientRepositoryRequisite;

    @BeforeEach
    void init() {
        clientRepositoryRequisite = new ClientRepositoryRequisiteStub();
        requisiteService = new RequisiteServicePojo(clientRepositoryRequisite);
    }

    @Test
    void prepareAccountOpenRequisitesShouldEnrichWithClient() throws AccountOpenException {
        Client expected = new Client();
        ((ClientRepositoryRequisiteStub) clientRepositoryRequisite).setClient(expected);
        AccountOpenRequisites enrichedRequisites =
                requisiteService.prepareAccountOpenRequisites(RequisitesGenerator.defineOpenRequisites());
        Client actual = enrichedRequisites.getOperationRequisites().getClient();
        assertEquals(expected, actual);
    }

    @Test
    void prepareAccountOpenOperationRequisitesShouldEnrichCorrectly() throws AccountOpenException {
        Client client = new Client();
        ((ClientRepositoryRequisiteStub) clientRepositoryRequisite).setClient(client);
        Account account = new Account();
        OperationRequisites enrichedRequisites =
                requisiteService.prepareAccountOpenOperationRequisites(
                        account,
                        RequisitesGenerator.defineOpenRequisites());
        final boolean testPassed = enrichedRequisites.getClient().equals(client)
                && enrichedRequisites.getAccount().equals(account)
                && enrichedRequisites.getAccountRecipient().equals(account)
                && enrichedRequisites.getOperationType().equals(OperationType.DEPOSIT)
                && enrichedRequisites.getHistoryNumber() == 1;
        assertTrue(testPassed);
    }

    @Test
    void prepareDepositOperationRequisitesShouldEnrichCorrectly() throws AccountDepositException {
        Client client = new Client();
        ((ClientRepositoryRequisiteStub) clientRepositoryRequisite).setClient(client);
        Account account = new Account();
        OperationRequisites enrichedRequisites =
                requisiteService.prepareDepositOperationRequisites(
                        account,
                        RequisitesGenerator.defineOperationRequisites());
        final boolean testPassed = enrichedRequisites.getClient().equals(client)
                && enrichedRequisites.getAccount().equals(account)
                && enrichedRequisites.getOperationType().equals(OperationType.DEPOSIT);
        assertTrue(testPassed);
    }

    @Test
    void prepareWithdrawOperationRequisites() throws AccountWithdrawException {
        Client client = new Client();
        ((ClientRepositoryRequisiteStub) clientRepositoryRequisite).setClient(client);
        Account account = new Account();
        OperationRequisites enrichedRequisites =
                requisiteService.prepareWithdrawOperationRequisites(
                        account,
                        RequisitesGenerator.defineOperationRequisites());
        final boolean testPassed = enrichedRequisites.getClient().equals(client)
                && enrichedRequisites.getAccount().equals(account)
                && enrichedRequisites.getOperationType().equals(OperationType.WITHDRAW);
        assertTrue(testPassed);
    }
}