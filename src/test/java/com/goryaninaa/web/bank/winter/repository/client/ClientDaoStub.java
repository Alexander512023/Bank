package com.goryaninaa.web.bank.winter.repository.client;

import com.goryaninaa.web.bank.education.winter.repository.client.ClientDao;
import com.goryaninaa.web.bank.domain.model.client.Client;
import java.util.Optional;

public class ClientDaoStub implements ClientDao {

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
