package com.goryaninaa.web.bank.winter.repository.account;

import com.goryaninaa.web.bank.domain.model.client.Client;
import com.goryaninaa.web.bank.education.winter.repository.client.ClientDao;
import java.util.Optional;

public class ClientDaoStub implements ClientDao {

  private Client client;
  @Override
  public Optional<Client> findByPassport(String passport) {
    return Optional.empty();
  }

  public void setClient(Client client) {
    this.client = client;
  }

  @Override
  public Optional<Client> findById(int clientId) {
    return Optional.of(client);
  }
}
