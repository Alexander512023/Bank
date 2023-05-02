package com.goryaninaa.web.bank.domain.model.account;

import com.goryaninaa.web.bank.domain.model.operation.Operation;
import com.goryaninaa.web.bank.exception.AccountWithdrawException;
import com.goryaninaa.web.bank.domain.model.client.Client;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * This is Account model class. It encapsulates corresponding fields and logic - deposit and
 * withdraw methods.
 */
@SuppressWarnings("unused")
public class Account implements Comparable<Account> { //NOPMD - suppressed DataClass - it's not
  // DataClass - it's model class! =)

  private int accountId;
  private int balance;
  private int number;
  private State state;
  private LocalDateTime openedAt;
  private LocalDateTime closedAt;
  private Client owner;
  private List<Operation> history = new ArrayList<>();
  private AccountType type;
  private int term;
  private LocalDate prolongationDate;

  public Account() {
    // Default constructor
  }

  /**
   * Constructor, which should be used when creating Account class instance when number is known.
   *
   * @param number - account number
   */
  public Account(final int number) {
    this.number = number;
  }

  /**
   * Constructor, which should be used when creating Account class instance when requisites and
   * number are known.
   *
   * @param requisites - see {@link AccountOpenRequisites} for details
   * @param number - account number
   */
  public Account(final AccountOpenRequisites requisites, final int number) {
    setBalance(requisites.getOperRequisites().getAmount());
    setNumber(number);
    setState(State.OPENED);
    setOpenedAt(LocalDateTime.now());
    setOwner(requisites.getOperRequisites().getClient());
    setType(requisites.getAccountType());
    setTerm(requisites.getTerm());
    setProlongationDate(openedAt.toLocalDate().plusMonths(term));
  }

  /**
   * Use this method if you need to perform deposit operation on this account object.
   *
   * @param amount - how many funds you want to deposit on this account
   */
  public void deposit(final int amount) {
    balance += Math.abs(amount);
  }

  /**
   * Use this method if you need to perform withdraw operation on this account object.
   *
   * @param amount - how many funds you want to withdraw from this account
   * @throws AccountWithdrawException - thrown in case of insufficient funds
   */
  public void withdraw(final int amount) throws AccountWithdrawException {
    if (balance - Math.abs(amount) > 0) {
      balance -= Math.abs(amount);
    } else {
      throw new AccountWithdrawException("Insufficient funds");
    }
  }

  @SuppressWarnings("NullableProblems")
  @Override
  public int compareTo(final Account that) {
    int result;
    if (this.equals(that)) {
      result = 0;
    } else if (this.state.equals(that.state) && this.openedAt.isAfter(that.openedAt)
        || this.state.equals(State.OPENED) && that.state.equals(State.CLOSED)
    ) {
      result = 1;
    } else {
      result = -1;
    }
    return result;
  }

  public int getAccountId() {
    return accountId;
  }

  public void setAccountId(final int accountId) {
    this.accountId = accountId;
  }

  public int getBalance() {
    return balance;
  }

  public final void setBalance(final int balance) {
    this.balance = balance;
  }

  public int getNumber() {
    return number;
  }

  public final void setNumber(final int number) {
    this.number = number;
  }

  public State getState() {
    return state;
  }

  public final void setState(final State state) {
    this.state = state;
  }

  public LocalDateTime getOpenedAt() {
    return openedAt;
  }

  public final void setOpenedAt(final LocalDateTime openedAt) {
    this.openedAt = openedAt;
  }

  public LocalDateTime getClosedAt() {
    return closedAt;
  }

  public void setClosedAt(final LocalDateTime closedAt) {
    this.closedAt = closedAt;
  }

  public Client getOwner() {
    return owner;
  }

  public final void setOwner(final Client owner) {
    this.owner = owner;
  }

  public List<Operation> getHistory() {
    return history;
  }

  public void setHistory(final List<Operation> history) {
    this.history = history;
  }

  public AccountType getType() {
    return type;
  }

  public final void setType(final AccountType type) {
    this.type = type;
  }

  public int getTerm() {
    return term;
  }

  public final void setTerm(final int term) {
    this.term = term;
  }

  public LocalDate getProlongationDate() {
    return prolongationDate;
  }

  public final void setProlongationDate(final LocalDate prolongationDate) {
    this.prolongationDate = prolongationDate;
  }

  public void addTransaction(final Operation transaction) {
    history.add(transaction);
    history.sort(Comparator.comparing(Operation::getHistoryNumber));
  }

  @Override
  public boolean equals(final Object that) {
    boolean result;
    if (this == that) {
      result = true;
    } else if (that == null || getClass() != that.getClass()) {
      result = false;
    } else {
      final Account account = (Account) that;
      result = number == account.number && openedAt.equals(account.openedAt);
    }
    return result;
  }

  @Override
  public int hashCode() {
    int result = number;
    result = 31 * result + openedAt.hashCode();
    return result;
  }

  @Override
  public String toString() {
    return "Account{" +
        "accountId=" + accountId +
        ", balance=" + balance +
        ", number=" + number +
        ", state=" + state +
        ", openedAt=" + openedAt +
        ", closedAt=" + closedAt +
        ", owner=" + owner +
        ", history=" + history +
        ", type=" + type +
        ", term=" + term +
        ", prolongationDate=" + prolongationDate +
        '}';
  }
}
