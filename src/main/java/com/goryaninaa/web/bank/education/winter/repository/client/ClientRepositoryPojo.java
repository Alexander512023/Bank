package com.goryaninaa.web.bank.education.winter.repository.client;

import com.goryaninaa.web.bank.model.client.Client;
import com.goryaninaa.web.bank.service.requisite.ClientRepositoryRequisite;
import java.util.Optional;

/**
 * This is simple implementation of {@link ClientRepositoryRequisite}. {@link ClientDao} is used to
 * find clients.
 */
public class ClientRepositoryPojo implements ClientRepositoryRequisite {

  private final ClientDao clientDao;

  public ClientRepositoryPojo(final ClientDao clientDao) {
    this.clientDao = clientDao;
  }

  @Override
  public Optional<Client> findByPassport(final String passport) {
    return clientDao.findByPassport(passport);
  }

}
