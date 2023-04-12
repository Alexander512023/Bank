package com.goryaninaa.web.bank.winter.repository.client;

import com.goryaninaa.web.bank.model.client.Client;
import com.goryaninaa.web.bank.service.requisite.ClientRepositoryRequisite;
import java.util.Optional;

public class ClientRepositoryPOJO implements ClientRepositoryRequisite {

  private final ClientDAO clientDAO;

  public ClientRepositoryPOJO(ClientDAO clientDAO) {
    this.clientDAO = clientDAO;
  }

  @Override
  public Optional<Client> findByPassport(String passport) {
    return clientDAO.findByPassport(passport);
  }

}
