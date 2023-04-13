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

/**
 * OperationDto is data transfer object for Operation entity.
 */
@SuppressWarnings("unused")
public class OperationDto implements Comparable<OperationDto> {

  private int amount;
  private int balanceBefore;
  private int balanceAfter;
  private LocalDateTime performedAt;
  private Integer accountFromNumber;
  private Integer accountRecipientNumber;
  private ClientDto clientDto;
  private ServiceInitiator service;
  private OperationType operationType;
  private int historyNumber;

  public OperationDto() {
  }

  /**
   * Constructor which should be used to create OperationDTO from {@link Operation} and
   * {@link ClientDto} objects.
   *
   * @param operation - operation which will be included in DTO
   * @param clientDto - clientDto which Will be included in DTO
   */
  public OperationDto(Operation operation, ClientDto clientDto) {
    this.amount = operation.getAmount();
    this.performedAt = operation.getPerformedAt();
    this.historyNumber = operation.getHistoryNumber();
    this.operationType = operation.getOperationType();
    this.clientDto = clientDto;
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

  /**
   * Method which could be used to extract corresponding {@link OperationRequisites} from the
   * instance of this class.
   *
   * @return - returns corresponding {@link OperationRequisites}
   */
  public OperationRequisites extractOperationRequisites() {
    OperationRequisites requisites = new OperationRequisites();
    requisites.setAmount(amount);
    Optional<Integer> accountFrom = Optional.ofNullable(accountFromNumber);
    accountFrom.ifPresent(integer -> requisites.setAccountFrom(new Account(integer)));
    Optional<Integer> accountRecipient = Optional.ofNullable(accountRecipientNumber);
    accountRecipient.ifPresent(integer -> requisites.setAccountRecipient(new Account(integer)));
    requisites.setClient(new Client(clientDto.getPassport()));
    requisites.setService(service);
    requisites.setOperationType(operationType);
    requisites.setHistoryNumber(historyNumber);
    return requisites;
  }

  @Override
  public int compareTo(OperationDto that) {
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
    OperationDto that = (OperationDto) o;
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

  public ClientDto getClientDto() {
    return clientDto;
  }

  public void setClientDto(ClientDto clientDto) {
    this.clientDto = clientDto;
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
