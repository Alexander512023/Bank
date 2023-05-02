package com.goryaninaa.web.bank.education.winter.repository.client;

import com.goryaninaa.web.bank.domain.model.client.Client;
import com.goryaninaa.web.bank.domain.service.requisite.ClientRepository;
import java.util.Optional;

/**
 * This is simple implementation of {@link ClientRepository}. {@link ClientDao} is used to
 * find clients.
 */
public class ClientRepositoryPojo implements ClientRepository {

  private final ClientDao clientDao;

  public ClientRepositoryPojo(final ClientDao clientDao) {
    this.clientDao = clientDao;
  }

  @Override
  public Optional<Client> findByPassport(final String passport) {
    return clientDao.findByPassport(passport);
  }

  @Override
  public Optional<Client> findById(int clientId) {
    return clientDao.findById(clientId);
  }

}
