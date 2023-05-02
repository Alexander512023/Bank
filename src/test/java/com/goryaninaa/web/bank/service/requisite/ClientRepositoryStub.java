package com.goryaninaa.web.bank.service.requisite;

import com.goryaninaa.web.bank.domain.model.client.Client;
import com.goryaninaa.web.bank.domain.service.requisite.ClientRepository;
import java.util.Optional;

public class ClientRepositoryStub implements ClientRepository {

  private Client client;

  @Override
  public Optional<Client> findByPassport(String passport) {
    return Optional.of(client);
  }

  @Override
  public Optional<Client> findById(int clientId) {
    return Optional.empty();
  }

  public void setClient(Client client) {
    this.client = client;
  }

}
