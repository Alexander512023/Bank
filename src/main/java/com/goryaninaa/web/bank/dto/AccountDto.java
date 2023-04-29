package com.goryaninaa.web.bank.dto;

import com.goryaninaa.web.bank.domain.model.account.Account;
import com.goryaninaa.web.bank.domain.model.account.AccountType;
import com.goryaninaa.web.bank.domain.model.account.State;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

/**
 * AccountDto is data transfer object for Account entity.
 *
 */
@SuppressWarnings("unused")
public class AccountDto { //NOPMD - suppressed DataClass - this is data transfer object for Account
  // entity

  private int balance;
  private int number;
  private State state;
  private LocalDateTime openedAt;
  private LocalDateTime closedAt;
  private ClientDto owner;
  private List<OperationDto> historyDto;
  private AccountType type;
  private int term;
  private LocalDate prolongationDate;

  public AccountDto() {
    // Default constructor
  }

  public AccountDto(final Account account) {
    this.number = account.getNumber();
  }

  /**
   * Constructor that generates fully enriched object.
   *
   * @param account - see {@link  Account}
   * @param historyDto - list of Operation DTO's
   * @param owner - client-owner of this account
   */
  public AccountDto(final Account account, final List<OperationDto> historyDto,
                    final ClientDto owner) {
    this.balance = account.getBalance();
    this.number = account.getNumber();
    this.state = account.getState();
    this.openedAt = account.getOpenedAt();
    this.closedAt = account.getClosedAt();
    this.owner = owner;
    this.historyDto = historyDto;
    this.type = account.getType();
    this.term = account.getTerm();
    this.prolongationDate = account.getProlongationDate();
  }

  public int getBalance() {
    return balance;
  }

  public void setBalance(final int balance) {
    this.balance = balance;
  }

  public int getNumber() {
    return number;
  }

  public void setNumber(final int number) {
    this.number = number;
  }

  public State getState() {
    return state;
  }

  public void setState(final State state) {
    this.state = state;
  }

  public LocalDateTime getOpenedAt() {
    return openedAt;
  }

  public void setOpenedAt(final LocalDateTime openedAt) {
    this.openedAt = openedAt;
  }

  public LocalDateTime getClosedAt() {
    return closedAt;
  }

  public void setClosedAt(final LocalDateTime closedAt) {
    this.closedAt = closedAt;
  }

  public ClientDto getOwner() {
    return owner;
  }

  public void setOwner(final ClientDto owner) {
    this.owner = owner;
  }

  public List<OperationDto> getHistoryDto() {
    return historyDto;
  }

  public void setHistoryDto(final List<OperationDto> historyDto) {
    this.historyDto = historyDto;
  }

  public AccountType getType() {
    return type;
  }

  public void setType(final AccountType type) {
    this.type = type;
  }

  public int getTerm() {
    return term;
  }

  public void setTerm(final int term) {
    this.term = term;
  }

  public LocalDate getProlongationDate() {
    return prolongationDate;
  }

  public void setProlongationDate(final LocalDate prolongationDate) {
    this.prolongationDate = prolongationDate;
  }

}
