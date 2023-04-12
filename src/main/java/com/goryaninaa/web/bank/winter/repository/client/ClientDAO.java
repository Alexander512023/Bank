package com.goryaninaa.web.bank.winter.repository.client;

import com.goryaninaa.web.bank.model.client.Client;
import java.util.Optional;

public interface ClientDAO {

  Optional<Client> findByPassport(String passport);
}
