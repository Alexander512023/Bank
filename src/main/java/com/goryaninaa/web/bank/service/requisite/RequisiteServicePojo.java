package com.goryaninaa.web.bank.service.requisite;

import com.goryaninaa.web.bank.exception.AccountDepositException;
import com.goryaninaa.web.bank.exception.AccountOpenException;
import com.goryaninaa.web.bank.exception.AccountWithdrawException;
import com.goryaninaa.web.bank.model.account.Account;
import com.goryaninaa.web.bank.model.account.AccountOpenRequisites;
import com.goryaninaa.web.bank.model.client.Client;
import com.goryaninaa.web.bank.model.operation.OperationRequisites;
import com.goryaninaa.web.bank.model.operation.OperationType;
import com.goryaninaa.web.bank.service.account.RequisiteServiceAccount;
import com.goryaninaa.web.bank.service.operation.RequisiteServiceOperation;
import java.util.Optional;

public class RequisiteServicePojo implements RequisiteServiceAccount, RequisiteServiceOperation {

  private static final String MESSAGE = "There is no such client";
  private final ClientRepositoryRequisite clientRepository;

  public RequisiteServicePojo(ClientRepositoryRequisite clientRepository) {
    this.clientRepository = clientRepository;
  }

  @Override
  public AccountOpenRequisites prepareAccountOpenRequisites(AccountOpenRequisites requisites)
      throws AccountOpenException {
    return enrichOpenAccountRequisites(requisites);
  }

  @Override
  public OperationRequisites prepareAccountOpenOperationRequisites(Account account,
                                                                   AccountOpenRequisites requisites)
      throws AccountOpenException {
    return prepareFirstOperationRequisites(requisites, account);
  }

  @Override
  public OperationRequisites prepareDepositOperationRequisites(Account account,
                                                               OperationRequisites requisites)
      throws AccountDepositException {
    return enrichDepositRequisites(requisites, account);
  }

  @Override
  public OperationRequisites prepareWithdrawOperationRequisites(Account account,
                                                                OperationRequisites requisites)
      throws AccountWithdrawException {
    return enrichWithdrawRequisites(requisites, account);
  }

  private AccountOpenRequisites enrichOpenAccountRequisites(AccountOpenRequisites requisites)
      throws AccountOpenException {
    Optional<Client> initiator = clientRepository.findByPassport(
        requisites.getOperationRequisites().getClient().getPassport());
    if (initiator.isPresent()) {
      requisites.getOperationRequisites().setClient(initiator.get());
      return requisites;
    } else {
      throw new AccountOpenException(MESSAGE, new IllegalArgumentException());
    }
  }

  private OperationRequisites prepareFirstOperationRequisites(AccountOpenRequisites requisites,
                                                              Account account)
      throws AccountOpenException {
    AccountOpenRequisites enrichedRequisites = enrichOpenAccountRequisites(requisites);
    OperationRequisites firstOperationRequisites = enrichedRequisites.getOperationRequisites();
    firstOperationRequisites.setAccount(account);
    firstOperationRequisites.setAccountRecipient(account);
    firstOperationRequisites.setOperationType(OperationType.DEPOSIT);
    firstOperationRequisites.setHistoryNumber(1);
    return firstOperationRequisites;
  }

  private OperationRequisites enrichDepositRequisites(OperationRequisites requisites,
                                                      Account account)
      throws AccountDepositException {
    Optional<Client> client = clientRepository.findByPassport(requisites.getClient().getPassport());
    if (client.isPresent()) {
      requisites.enrich(account, client.get(), OperationType.DEPOSIT);
      return requisites;
    } else {
      throw new AccountDepositException(MESSAGE, new IllegalArgumentException());
    }
  }

  private OperationRequisites enrichWithdrawRequisites(OperationRequisites requisites,
                                                       Account account)
      throws AccountWithdrawException {
    Optional<Client> client = clientRepository.findByPassport(requisites.getClient().getPassport());
    if (client.isPresent()) {
      requisites.enrich(account, client.get(), OperationType.WITHDRAW);
      return requisites;
    } else {
      throw new AccountWithdrawException(MESSAGE, new IllegalArgumentException());
    }
  }

}
