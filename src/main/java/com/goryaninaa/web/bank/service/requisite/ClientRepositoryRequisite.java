package com.goryaninaa.web.bank.service.requisite;

import com.goryaninaa.web.bank.model.client.Client;
import java.util.Optional;

public interface ClientRepositoryRequisite {

  Optional<Client> findByPassport(String passport);

}
