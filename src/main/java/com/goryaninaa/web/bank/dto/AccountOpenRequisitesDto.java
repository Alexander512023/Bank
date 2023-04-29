package com.goryaninaa.web.bank.dto;

import com.goryaninaa.web.bank.domain.model.account.AccountOpenRequisites;
import com.goryaninaa.web.bank.domain.model.account.AccountType;
import com.goryaninaa.web.bank.domain.model.client.Client;
import com.goryaninaa.web.bank.domain.model.operation.OperationRequisites;
import com.goryaninaa.web.bank.domain.model.operation.ServiceInitiator;

/**
 * This is data transfer object, which should be used to get requisites from client side in case of
 * new account opening.
 */
@SuppressWarnings("unused")
public class AccountOpenRequisitesDto { //NOPMD - suppressed DataClass - this is data transfer
  // object

  private int amount;
  private ClientDto clientDto;
  private AccountType accountType;
  private ServiceInitiator service;
  private int term;

  public AccountOpenRequisitesDto() {
    // Default constructor
  }

  /**
   * Method that converts dto to corresponding entity.
   *
   * @return - corresponding entity {@link  AccountOpenRequisites}
   */
  public AccountOpenRequisites extractAccountRequisites() {
    final Client client = new Client(clientDto.getPassport());
    final OperationRequisites transaction = new OperationRequisites(amount, service, client);

    return new AccountOpenRequisites(transaction, accountType, term);
  }

  public int getAmount() {
    return amount;
  }

  public void setAmount(final int amount) {
    this.amount = amount;
  }

  public ClientDto getClientDto() {
    return clientDto;
  }

  public void setClientDto(final ClientDto clientDto) {
    this.clientDto = clientDto;
  }

  public AccountType getAccountType() {
    return accountType;
  }

  public void setAccountType(final AccountType accountType) {
    this.accountType = accountType;
  }

  public ServiceInitiator getService() {
    return service;
  }

  public void setService(final ServiceInitiator service) {
    this.service = service;
  }

  public int getTerm() {
    return term;
  }

  public void setTerm(final int term) {
    this.term = term;
  }

}
