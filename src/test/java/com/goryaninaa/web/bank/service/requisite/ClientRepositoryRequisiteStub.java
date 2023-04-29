package com.goryaninaa.web.bank.service.requisite;

import com.goryaninaa.web.bank.domain.model.client.Client;
import com.goryaninaa.web.bank.domain.service.requisite.ClientRepositoryRequisite;
import java.util.Optional;

public class ClientRepositoryRequisiteStub implements ClientRepositoryRequisite {

  private Client client;

  @Override
  public Optional<Client> findByPassport(String passport) {
    return Optional.of(client);
  }

  public void setClient(Client client) {
    this.client = client;
  }

}
