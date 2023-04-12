package com.goryaninaa.web.bank.dto;

import com.goryaninaa.web.bank.model.account.Account;
import com.goryaninaa.web.bank.model.client.Client;
import com.goryaninaa.web.bank.model.operation.Operation;
import com.goryaninaa.web.bank.model.operation.OperationRequisites;
import com.goryaninaa.web.bank.model.operation.OperationType;
import com.goryaninaa.web.bank.model.operation.ServiceInitiator;
import java.time.LocalDateTime;
import java.util.Objects;
import java.util.Optional;

@SuppressWarnings("unused")
public class OperationDTO implements Comparable<OperationDTO> {

  private int amount;
  private int balanceBefore;
  private int balanceAfter;
  private LocalDateTime performedAt;
  private Integer accountFromNumber;
  private Integer accountRecipientNumber;
  private ClientDTO clientDTO;
  private ServiceInitiator service;
  private OperationType operationType;
  private int historyNumber;

  public OperationDTO() {
  }

  public OperationDTO(Operation operation, ClientDTO clientDTO) {
    this.amount = operation.getAmount();
    this.performedAt = operation.getPerformedAt();
    this.historyNumber = operation.getHistoryNumber();
    this.operationType = operation.getOperationType();
    this.clientDTO = clientDTO;
    this.service = operation.getService();
    this.balanceBefore = operation.getBalanceBefore();
    this.balanceAfter = operation.getBalanceAfter();
    Optional<Account> accountFrom = Optional.ofNullable(operation.getAccountFrom());
    if (accountFrom.isPresent()) {
      this.accountFromNumber = operation.getAccountFrom().getNumber();
    }
    Optional<Account> accountRecipient = Optional.ofNullable(operation.getAccountRecipient());
    if (accountRecipient.isPresent()) {
      this.accountRecipientNumber = operation.getAccountRecipient().getNumber();
    }
  }

  public OperationRequisites extractOperationRequisites() {
    OperationRequisites requisites = new OperationRequisites();
    requisites.setAmount(amount);
    Optional<Integer> accountFrom = Optional.ofNullable(accountFromNumber);
    accountFrom.ifPresent(integer -> requisites.setAccountFrom(new Account(integer)));
    Optional<Integer> accountRecipient = Optional.ofNullable(accountRecipientNumber);
    accountRecipient.ifPresent(integer -> requisites.setAccountRecipient(new Account(integer)));
    requisites.setClient(new Client(clientDTO.getPassport()));
    requisites.setService(service);
    requisites.setOperationType(operationType);
    requisites.setHistoryNumber(historyNumber);
    return requisites;
  }

  @Override
  public int compareTo(OperationDTO that) {
    return Integer.compare(that.historyNumber, this.historyNumber);
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    OperationDTO that = (OperationDTO) o;
    if (amount != that.amount) {
      return false;
    }
    if (balanceBefore != that.balanceBefore) {
      return false;
    }
    if (balanceAfter != that.balanceAfter) {
      return false;
    }
    if (historyNumber != that.historyNumber) {
      return false;
    }
    if (!performedAt.equals(that.performedAt)) {
      return false;
    }
    if (!Objects.equals(accountFromNumber, that.accountFromNumber)) {
      return false;
    }
    return Objects.equals(accountRecipientNumber, that.accountRecipientNumber);
  }

  @Override
  public int hashCode() {
    int result = amount;
    result = 31 * result + balanceBefore;
    result = 31 * result + balanceAfter;
    result = 31 * result + performedAt.hashCode();
    result = 31 * result + (accountFromNumber != null ? accountFromNumber.hashCode() : 0);
    result = 31 * result + (accountRecipientNumber != null ? accountRecipientNumber.hashCode() : 0);
    result = 31 * result + historyNumber;
    return result;
  }

  public int getAmount() {
    return amount;
  }

  public void setAmount(int amount) {
    this.amount = amount;
  }

  public LocalDateTime getPerformedAt() {
    return performedAt;
  }

  public void setPerformedAt(LocalDateTime performedAt) {
    this.performedAt = performedAt;
  }


  public Integer getAccountFromNumber() {
    return accountFromNumber;
  }

  public void setAccountFromNumber(Integer accountFromNumber) {
    this.accountFromNumber = accountFromNumber;
  }

  public Integer getAccountRecipientNumber() {
    return accountRecipientNumber;
  }

  public void setAccountRecipientNumber(Integer accountRecipientNumber) {
    this.accountRecipientNumber = accountRecipientNumber;
  }

  public ClientDTO getClientDTO() {
    return clientDTO;
  }

  public void setClientDTO(ClientDTO clientDTO) {
    this.clientDTO = clientDTO;
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

  public int getBalanceBefore() {
    return balanceBefore;
  }

  public int getBalanceAfter() {
    return balanceAfter;
  }

}
