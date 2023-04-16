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

/**
 * This is simple implementation of {@link RequisiteServiceAccount} and
 * {@link RequisiteServiceOperation} interfaces. It operates {@link ClientRepositoryRequisite} in
 * order to get source of client's data.
 */
public class RequisiteServicePojo implements RequisiteServiceAccount, RequisiteServiceOperation {

  private static final String MESSAGE = "There is no such client";
  private final ClientRepositoryRequisite clientRepository;

  public RequisiteServicePojo(final ClientRepositoryRequisite clientRepository) {
    this.clientRepository = clientRepository;
  }

  @Override
  public AccountOpenRequisites prepareAccountOpenRequisites(final AccountOpenRequisites requisites)
      throws AccountOpenException {
    return enrichOpenAccountRequisites(requisites);
  }

  @Override
  public OperationRequisites prepareAccountOpenOperationRequisites(
      final Account account, final AccountOpenRequisites requisites) throws AccountOpenException {
    return prepareFirstOperationRequisites(requisites, account);
  }

  @Override
  public OperationRequisites prepareDepositOperationRequisites(
      final Account account, final OperationRequisites requisites) throws AccountDepositException {
    return enrichDepositRequisites(requisites, account);
  }

  @Override
  public OperationRequisites prepareWithdrawOperationRequisites(
      final Account account, final OperationRequisites requisites) throws AccountWithdrawException {
    return enrichWithdrawRequisites(requisites, account);
  }

  private AccountOpenRequisites enrichOpenAccountRequisites(final AccountOpenRequisites requisites)
      throws AccountOpenException {
    final Optional<Client> initiator = clientRepository.findByPassport(
        requisites.getOperRequisites().getClient().getPassport());
    if (initiator.isPresent()) {
      requisites.getOperRequisites().setClient(initiator.get());
      return requisites;
    } else {
      throw new AccountOpenException(MESSAGE, new IllegalArgumentException());
    }
  }

  private OperationRequisites prepareFirstOperationRequisites(
      final AccountOpenRequisites requisites, final Account account) throws AccountOpenException {
    final AccountOpenRequisites enrichedReq = enrichOpenAccountRequisites(requisites);
    final OperationRequisites firstOperReq = enrichedReq.getOperRequisites();
    firstOperReq.setAccount(account);
    firstOperReq.setAccountRecipient(account);
    firstOperReq.setOperationType(OperationType.DEPOSIT);
    firstOperReq.setHistoryNumber(1);
    return firstOperReq;
  }

  private OperationRequisites enrichDepositRequisites(
      final OperationRequisites requisites, final Account account) throws AccountDepositException {
    final Optional<Client> client = clientRepository
        .findByPassport(requisites.getClient().getPassport());
    if (client.isPresent()) {
      requisites.enrich(account, client.get(), OperationType.DEPOSIT);
      return requisites;
    } else {
      throw new AccountDepositException(MESSAGE, new IllegalArgumentException());
    }
  }

  private OperationRequisites enrichWithdrawRequisites(
      final OperationRequisites requisites, final Account account) throws AccountWithdrawException {
    final Optional<Client> client = clientRepository
        .findByPassport(requisites.getClient().getPassport());
    if (client.isPresent()) {
      requisites.enrich(account, client.get(), OperationType.WITHDRAW);
      return requisites;
    } else {
      throw new AccountWithdrawException(MESSAGE, new IllegalArgumentException());
    }
  }

}
