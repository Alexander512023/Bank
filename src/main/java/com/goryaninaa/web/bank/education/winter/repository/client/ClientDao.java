package com.goryaninaa.web.bank.education.winter.repository.client;

import com.goryaninaa.web.bank.domain.model.client.Client;
import java.util.Optional;

/**
 * This interface should be implemented in the data access layer to ensure correct direction of
 * dependencies.
 */
public interface ClientDao {

  Optional<Client> findByPassport(String passport);

  Optional<Client> findById(int clientId);
}
