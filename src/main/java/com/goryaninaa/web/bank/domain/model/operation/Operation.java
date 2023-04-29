package com.goryaninaa.web.bank.domain.model.operation;

import com.goryaninaa.web.bank.domain.model.account.Account;
import com.goryaninaa.web.bank.domain.model.client.Client;
import java.time.LocalDateTime;
import java.util.Objects;

/**
 * Bank account operation entity class.
 */
@SuppressWarnings("unused")
public class Operation implements Comparable<Operation> { //NOPMD - suppressed DataClass - entity

  private int operationId;
  private int amount;
  private int balanceBefore;
  private int balanceAfter;
  private LocalDateTime performedAt;
  private Account account;
  private Account accountFrom;
  private Account accountRecipient;
  private Client client;
  private ServiceInitiator service;
  private OperationType operationType;
  private int historyNumber;

  public Operation() {
    // Default constructor
  }

  /**
   * Constructor, that should be used to perform account open operation.
   *
   * @param amount - amount of funds
   * @param client - initiator
   * @param service - source of operation
   */
  public Operation(final int amount, final Client client, final ServiceInitiator service) {
    this.amount = amount;
    this.balanceBefore = 0;
    this.balanceAfter = amount;
    this.performedAt = LocalDateTime.now();
    this.client = client;
    this.service = service;
    this.operationType = OperationType.DEPOSIT;
    this.historyNumber = 1;
  }

  /**
   * Standard operation constructor, that should be used to create enriched instance of this class.
   *
   * @param requisites - operation requisites, see {@link OperationRequisites} for details
   */
  public Operation(final OperationRequisites requisites) {
    this.amount = requisites.getAmount();
    this.balanceBefore = requisites.getBalanceBefore();
    this.balanceAfter = requisites.getBalanceAfter();
    this.performedAt = LocalDateTime.now();
    this.client = requisites.getClient();
    this.service = requisites.getService();
    this.operationType = requisites.getOperationType();
    this.historyNumber = requisites.getHistoryNumber();
    defineAccounts(requisites);
  }

  @Override
  public int compareTo(final Operation that) {
    int result;
    if (this.historyNumber > that.historyNumber) {
      result = 1;
    } else if (this.equals(that)) {
      result = 0;
    } else {
      result = -1;
    }
    return result;
  }

  @SuppressWarnings("unused")
  public int getOperationId() {
    return operationId;
  }

  public void setOperationId(final int operationId) {
    this.operationId = operationId;
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

  public LocalDateTime getPerformedAt() {
    return performedAt;
  }

  public void setPerformedAt(final LocalDateTime performedAt) {
    this.performedAt = performedAt;
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

  @Override
  public int hashCode() {
    return Objects.hash(operationId, amount, historyNumber, account);
  }

  @Override
  public boolean equals(final Object obj) {
    boolean result;
    if (this == obj) {
      result = true;
    } else if (obj == null) {
      result = false;
    } else if (getClass() != obj.getClass()) {
      result = false;
    } else {
      final Operation other = (Operation) obj;
      result = operationId == other.operationId && amount == other.amount
          && historyNumber == other.historyNumber && Objects.equals(account, other.account);
    }
    return result;
  }

  private void defineAccounts(final OperationRequisites requisites) {
    this.account = requisites.getAccount();
    switch (operationType) {
      case DEPOSIT:
        this.accountRecipient = requisites.getAccountRecipient();
        break;
      case WITHDRAW:
        this.accountFrom = requisites.getAccountFrom();
        break;
      case TRANSFER:
        this.accountFrom = requisites.getAccountFrom();
        this.accountRecipient = requisites.getAccountRecipient();
        break;
      default:
        break;
    }
  }
}
