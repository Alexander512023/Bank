package com.goryaninaa.web.bank.education.dao.stub;

import com.goryaninaa.web.bank.domain.model.client.Client;
import com.goryaninaa.web.bank.education.winter.repository.client.ClientDao;
import com.sun.jdi.request.DuplicateRequestException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Stub.
 */
@SuppressWarnings("unused")
public class ClientDaoConcurrentStub implements ClientDao {

  private static final AtomicInteger idCounter = new AtomicInteger(1);
  private final List<Client> clients;

  /**
   * Stub's constructor.
   */
  public ClientDaoConcurrentStub() {
    this.clients = new ArrayList<>();
  }

  /**
   * Blah-blah.
   *
   * @param client - blah
   */
  public void save(Client client) {
    for (Client savedEarlierClient : clients) {
      if (savedEarlierClient.equals(client)) {
        throw new DuplicateRequestException("This client already exists");
      }
    }
    client.setClientId(idCounter.getAndIncrement());
    clients.add(client);
  }

  @Override
  public Optional<Client> findByPassport(String passport) {
    Optional<Client> desiredClient = Optional.empty();
    for (Client client : clients) {
      if (client.getPassport().equals(passport)) {
        desiredClient = Optional.of(client);
        break;
      }
    }
    return desiredClient;
  }

  @Override
  public Optional<Client> findById(int clientId) {
    Optional<Client> desiredClient = Optional.empty();
    for (Client client : clients) {
      if (client.getClientId() ==  clientId) {
        desiredClient = Optional.of(client);
        break;
      }
    }
    return desiredClient;
  }

}
