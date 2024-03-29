package com.goryaninaa.web.bank.service.requisite;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.goryaninaa.web.bank.domain.service.requisite.ClientRepository;
import com.goryaninaa.web.bank.domain.service.requisite.RequisiteServicePojo;
import com.goryaninaa.web.bank.exception.AccountDepositException;
import com.goryaninaa.web.bank.exception.AccountOpenException;
import com.goryaninaa.web.bank.exception.AccountWithdrawException;
import com.goryaninaa.web.bank.domain.model.account.Account;
import com.goryaninaa.web.bank.domain.model.account.AccountOpenRequisites;
import com.goryaninaa.web.bank.domain.model.client.Client;
import com.goryaninaa.web.bank.domain.model.operation.OperationRequisites;
import com.goryaninaa.web.bank.domain.model.operation.OperationType;
import com.goryaninaa.web.bank.service.util.RequisitesGenerator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class RequisiteServicePojoTest {

  private static RequisiteServicePojo requisiteService;
  private static ClientRepository clientRepository;

  @BeforeEach
  void init() {
    clientRepository = new ClientRepositoryStub();
    requisiteService = new RequisiteServicePojo(clientRepository,
        new OperationRepositoryRequisiteStub());
  }

  @Test
  void prepareAccountOpenRequisitesShouldEnrichWithClient() throws AccountOpenException {
    Client expected = new Client();
    ((ClientRepositoryStub) clientRepository).setClient(expected);
    AccountOpenRequisites enrichedRequisites =
        requisiteService.prepareAccountOpenRequisites(RequisitesGenerator.defineOpenRequisites());
    Client actual = enrichedRequisites.getOperRequisites().getClient();
    assertEquals(expected, actual);
  }

  @Test
  void prepareAccountOpenOperationRequisitesShouldEnrichCorrectly() throws AccountOpenException {
    Client client = new Client();
    ((ClientRepositoryStub) clientRepository).setClient(client);
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
    ((ClientRepositoryStub) clientRepository).setClient(client);
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
    ((ClientRepositoryStub) clientRepository).setClient(client);
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