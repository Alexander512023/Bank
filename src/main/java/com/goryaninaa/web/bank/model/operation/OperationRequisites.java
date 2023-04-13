package com.goryaninaa.web.bank.model.operation;

import com.goryaninaa.web.bank.model.account.Account;
import com.goryaninaa.web.bank.model.client.Client;

public class OperationRequisites {

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

  }

  public OperationRequisites(int amount, ServiceInitiator service, Client client) {
    this.amount = amount;
    this.service = service;
    this.client = client;
  }

  public void enrich(Account account, Client client, OperationType operationType) {
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

  public void setAmount(int amount) {
    this.amount = amount;
  }

  public int getBalanceBefore() {
    return balanceBefore;
  }

  public void setBalanceBefore(int balanceBefore) {
    this.balanceBefore = balanceBefore;
  }

  public int getBalanceAfter() {
    return balanceAfter;
  }

  public void setBalanceAfter(int balanceAfter) {
    this.balanceAfter = balanceAfter;
  }

  public Account getAccount() {
    return account;
  }

  public void setAccount(Account account) {
    this.account = account;
  }

  public Account getAccountFrom() {
    return accountFrom;
  }

  public void setAccountFrom(Account accountFrom) {
    this.accountFrom = accountFrom;
  }

  public Account getAccountRecipient() {
    return accountRecipient;
  }

  public void setAccountRecipient(Account accountRecipient) {
    this.accountRecipient = accountRecipient;
  }

  public Client getClient() {
    return client;
  }

  public void setClient(Client client) {
    this.client = client;
  }

  public ServiceInitiator getService() {
    return service;
  }

  public void setService(ServiceInitiator service) {
    this.service = service;
  }

  public OperationType getOperationType() {
    return operationType;
  }

  public void setOperationType(OperationType operationType) {
    this.operationType = operationType;
  }

  public int getHistoryNumber() {
    return historyNumber;
  }

  public void setHistoryNumber(int historyNumber) {
    this.historyNumber = historyNumber;
  }

  private void defineBalanceBefore(OperationType operationType, int amount, int balanceAfter) {
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
