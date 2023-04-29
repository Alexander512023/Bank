package com.goryaninaa.web.bank.domain.service.requisite;

import com.goryaninaa.web.bank.domain.model.client.Client;
import java.util.Optional;

/**
 * This interface should be implemented to provide functionality of searching clients by passport
 * in order to enrich requisites.
 */
public interface ClientRepositoryRequisite {

  Optional<Client> findByPassport(String passport);

}
