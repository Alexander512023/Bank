package com.goryaninaa.web.bank.model.operation;

import com.goryaninaa.web.bank.model.account.Account;
import com.goryaninaa.web.bank.model.client.Client;

/**
 * Class with main goal of encapsulating operation fields. Instances of this class should be passed
 * through constructors of {@link Operation} class.
 */
public class OperationRequisites { //NOPMD - suppressed DataClass - this class was created to
  // reduce the number of arguments passed through constructor of Operation.class

  private int amount;
  private int balanceBefore;
  private int balanceAfter;
  private Account account;
  private Account accountFrom;
  private Account accountRecipient;
  private Client client;
  private ServiceInitiator service;
  private OperationType operationType;
  private int historyNumber;

  public OperationRequisites() {
    // Default constructor
  }

  /**
   * Constructor for primary identification with parameters.
   *
   * @param amount - amount of funds
   * @param service - source of operation
   * @param client - initiator
   */
  public OperationRequisites(final int amount, final ServiceInitiator service,
                             final Client client) {
    this.amount = amount;
    this.service = service;
    this.client = client;
  }

  /**
   * Use this method to enrich this instance with corresponding requisites.
   *
   * @param account - account on which operation should be performed
   * @param client - initiator
   * @param operationType - type of operation
   */
  public void enrich(final Account account, final Client client,
                     final OperationType operationType) {
    this.setAccount(account);
    this.setAccountRecipient(account);
    this.setClient(client);
    this.setBalanceAfter(account.getBalance());
    this.setHistoryNumber(account.getLastOperNumber());
    this.setOperationType(operationType);
    defineBalanceBefore(operationType, amount, balanceAfter);
  }

  public int getAmount() {
    return amount;
  }

  public void setAmount(final int amount) {
    this.amount = amount;
  }

  public int getBalanceBefore() {
    return balanceBefore;
  }

  public void setBalanceBefore(final int balanceBefore) {
    this.balanceBefore = balanceBefore;
  }

  public int getBalanceAfter() {
    return balanceAfter;
  }

  public void setBalanceAfter(final int balanceAfter) {
    this.balanceAfter = balanceAfter;
  }

  public Account getAccount() {
    return account;
  }

  public void setAccount(final Account account) {
    this.account = account;
  }

  public Account getAccountFrom() {
    return accountFrom;
  }

  public void setAccountFrom(final Account accountFrom) {
    this.accountFrom = accountFrom;
  }

  public Account getAccountRecipient() {
    return accountRecipient;
  }

  public void setAccountRecipient(final Account accountRecipient) {
    this.accountRecipient = accountRecipient;
  }

  public Client getClient() {
    return client;
  }

  public void setClient(final Client client) {
    this.client = client;
  }

  public ServiceInitiator getService() {
    return service;
  }

  public void setService(final ServiceInitiator service) {
    this.service = service;
  }

  public OperationType getOperationType() {
    return operationType;
  }

  public void setOperationType(final OperationType operationType) {
    this.operationType = operationType;
  }

  public int getHistoryNumber() {
    return historyNumber;
  }

  public void setHistoryNumber(final int historyNumber) {
    this.historyNumber = historyNumber;
  }

  private void defineBalanceBefore(final OperationType operationType,
                                   final int amount, final int balanceAfter) {
    switch (operationType) {
      case DEPOSIT:
        this.setBalanceBefore(balanceAfter - amount);
        break;
      case WITHDRAW:
        this.setBalanceBefore(balanceAfter + Math.abs(amount));
        break;
      default:
        break;
    }
  }
}
