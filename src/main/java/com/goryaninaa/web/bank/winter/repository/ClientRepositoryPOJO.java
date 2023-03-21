package com.goryaninaa.web.bank.winter.repository;

import java.util.Optional;

import com.goryaninaa.web.bank.winter.dao.concurrent.stub.ClientDAO;
import com.goryaninaa.web.bank.model.client.Client;
import com.goryaninaa.web.bank.service.requisite.ClientRepositoryRequisite;

public class ClientRepositoryPOJO implements ClientRepositoryRequisite {

	private final ClientDAO clientDAO;
	
	public ClientRepositoryPOJO(ClientDAO clientDAO) {
		this.clientDAO = clientDAO;
	}
	
	@Override
	public Optional<Client> findByPassport(String passport) {
		Optional<Client> client = clientDAO.findByPassport(passport);
		
		return client;
	}

}
