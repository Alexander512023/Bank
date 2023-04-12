package com.goryaninaa.web.bank.winter.dao.concurrent.stub;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.goryaninaa.web.bank.model.client.Client;
import com.goryaninaa.web.bank.winter.repository.client.ClientDAO;

public class ClientDAOConcurrentStub implements ClientDAO {

	private static int idCounter = 1;
	private final List<Client> clients;

	public ClientDAOConcurrentStub() {
		this.clients = new ArrayList<>();
		Client client = new Client(1, "36 10 000001", "Alex", "Goryanin", "30.10.1989");
		this.save(client);
	}
	
	public void save(Client client) {
		for (Client savedEarlierClient : clients) {
			if (savedEarlierClient.equals(client)) {
				throw new RuntimeException("This client already exists");
			}
		}
		client.setId(idCounter++);
		clients.add(client);
	}

	@Override
	public Optional<Client> findByPassport(String passport) {
		Optional<Client> desiredClient = Optional.empty();

		for (Client client : clients) {
			if (client.getPassport().equals(passport)) {
				desiredClient = Optional.ofNullable(client);
				break;
			}
		}
		return desiredClient;
	}

}
