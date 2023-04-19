package com.goryaninaa.web.bank.winter.repository.client;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.goryaninaa.web.bank.model.client.Client;
import org.junit.jupiter.api.Test;

class ClientRepositoryPojoTest {

  @Test
  void findByPassportShouldCorrectlyReturnClient() {
    final ClientDaoStub clientDAO = new ClientDaoStub();
    final ClientRepositoryPojo clientRepositoryPOJO = new ClientRepositoryPojo(clientDAO);
    final Client expected = new Client();
    clientDAO.setClient(expected);
    final Client actual = clientRepositoryPOJO.findByPassport("1").orElseThrow();
    assertEquals(expected, actual);
  }
}