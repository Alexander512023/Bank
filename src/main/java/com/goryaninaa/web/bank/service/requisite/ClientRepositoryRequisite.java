package com.goryaninaa.web.bank.service.requisite;

import java.util.Optional;

import com.goryaninaa.web.bank.model.client.Client;

public interface ClientRepositoryRequisite {

	Optional<Client> findByPassport(String passport);

}
