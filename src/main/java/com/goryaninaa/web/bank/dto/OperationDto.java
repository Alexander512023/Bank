package com.goryaninaa.web.bank.dto;

import com.goryaninaa.web.bank.domain.model.account.Account;
import com.goryaninaa.web.bank.domain.model.client.Client;
import com.goryaninaa.web.bank.domain.model.operation.Operation;
import com.goryaninaa.web.bank.domain.model.operation.OperationRequisites;
import com.goryaninaa.web.bank.domain.model.operation.OperationType;
import com.goryaninaa.web.bank.domain.model.operation.ServiceInitiator;
import java.time.LocalDateTime;
import java.util.Objects;
import java.util.Optional;

/**
 * OperationDto is data transfer object for Operation entity.
 */
@SuppressWarnings("unused")
public class OperationDto implements Comparable<OperationDto> { //NOPMD - suppressed DataClass -
  // data transfer object

  private int amount;
  private int balanceBefore;
  private int balanceAfter;
  private LocalDateTime performedAt;
  private Integer accFromNum;
  private Integer accRecipientNum;
  private ClientDto clientDto;
  private ServiceInitiator service;
  private OperationType operationType;
  private int historyNumber;

  public OperationDto() {
    // Default constructor
  }

  /**
   * Constructor which should be used to create OperationDTO from {@link Operation} and
   * {@link ClientDto} objects.
   *
   * @param operation - operation which will be included in DTO
   * @param clientDto - clientDto which Will be included in DTO
   */
  public OperationDto(final Operation operation, final ClientDto clientDto) {
    this.amount = operation.getAmount();
    this.performedAt = operation.getPerformedAt();
    this.historyNumber = operation.getHistoryNumber();
    this.operationType = operation.getOperationType();
    this.clientDto = clientDto;
    this.service = operation.getService();
    this.balanceBefore = operation.getBalanceBefore();
    this.balanceAfter = operation.getBalanceAfter();
    final Optional<Account> accountFrom = Optional.ofNullable(operation.getAccountFrom());
    if (accountFrom.isPresent()) {
      this.accFromNum = operation.getAccountFrom().getNumber();
    }
    final Optional<Account> accountRecipient = Optional.ofNullable(operation.getAccountRecipient());
    if (accountRecipient.isPresent()) {
      this.accRecipientNum = operation.getAccountRecipient().getNumber();
    }
  }

  /**
   * Method which could be used to extract corresponding {@link OperationRequisites} from the
   * instance of this class.
   *
   * @return - returns corresponding {@link OperationRequisites}
   */
  public OperationRequisites extractOperationRequisites() {
    final OperationRequisites requisites = new OperationRequisites();
    requisites.setAmount(amount);
    final Optional<Integer> accountFrom = Optional.ofNullable(accFromNum);
    accountFrom.ifPresent(integer -> requisites.setAccountFrom(new Account(integer)));
    final Optional<Integer> accountRecipient = Optional.ofNullable(accRecipientNum);
    accountRecipient.ifPresent(integer -> requisites.setAccountRecipient(new Account(integer)));
    requisites.setClient(new Client(clientDto.getPassport()));
    requisites.setService(service);
    requisites.setOperationType(operationType);
    requisites.setHistoryNumber(historyNumber);
    return requisites;
  }

  @Override
  public int compareTo(final OperationDto that) {
    return Integer.compare(that.historyNumber, this.historyNumber);
  }

  @Override
  public boolean equals(final Object that) {
    boolean result;
    if (this == that) {
      result = true;
    } else if (that == null || getClass() != that.getClass()) {
      result = false;
    } else {
      final OperationDto operationDto = (OperationDto) that;
      result = performedAt.equals(operationDto.performedAt)
          && Objects.equals(accFromNum, operationDto.accFromNum)
          && Objects.equals(accRecipientNum, operationDto.accRecipientNum);
    }
    return result;
  }

  @Override
  public int hashCode() {
    int result = performedAt.hashCode();
    result = 31 * result + (accFromNum != null ? accFromNum.hashCode() : 0);
    result = 31 * result + (accRecipientNum != null ? accRecipientNum.hashCode() : 0);
    return result;
  }

  public int getAmount() {
    return amount;
  }

  public void setAmount(final int amount) {
    this.amount = amount;
  }

  public LocalDateTime getPerformedAt() {
    return performedAt;
  }

  public void setPerformedAt(final LocalDateTime performedAt) {
    this.performedAt = performedAt;
  }


  public Integer getAccFromNum() {
    return accFromNum;
  }

  public void setAccFromNum(final Integer accFromNum) {
    this.accFromNum = accFromNum;
  }

  public Integer getAccRecipientNum() {
    return accRecipientNum;
  }

  public void setAccRecipientNum(final Integer accRecipientNum) {
    this.accRecipientNum = accRecipientNum;
  }

  public ClientDto getClientDto() {
    return clientDto;
  }

  public void setClientDto(final ClientDto clientDto) {
    this.clientDto = clientDto;
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

  public int getBalanceBefore() {
    return balanceBefore;
  }

  public int getBalanceAfter() {
    return balanceAfter;
  }

}
