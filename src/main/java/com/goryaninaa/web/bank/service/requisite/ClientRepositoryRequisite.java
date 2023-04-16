package com.goryaninaa.web.bank.service.requisite;

import com.goryaninaa.web.bank.model.client.Client;
import java.util.Optional;

/**
 * This interface should be implemented to provide functionality of searching clients by passport
 * in order to enrich requisites.
 */
public interface ClientRepositoryRequisite {

  Optional<Client> findByPassport(String passport);

}
