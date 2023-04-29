package com.goryaninaa.web.bank.model.account;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.goryaninaa.web.bank.domain.model.account.Account;
import com.goryaninaa.web.bank.domain.model.account.State;
import com.goryaninaa.web.bank.exception.AccountWithdrawException;
import java.time.LocalDateTime;
import org.junit.jupiter.api.Test;

class AccountTest {

  @Test
  void depositShouldIncreaseBalance() {
    Account account = new Account();
    boolean balanceBefore0 = account.getBalance() == 0;
    account.deposit(100);
    boolean balanceAfter100 = account.getBalance() == 100;
    boolean testPass = balanceBefore0 && balanceAfter100;
    assertTrue(testPass);
  }

  @Test
  void withdrawShouldDecreaseBalance() throws AccountWithdrawException {
    Account account = new Account();
    account.setBalance(100);
    boolean balanceBefore100 = account.getBalance() == 100;
    account.withdraw(50);
    boolean balanceAfter50 = account.getBalance() == 50;
    boolean testPass = balanceBefore100 && balanceAfter50;
    assertTrue(testPass);
  }

  @Test
  void withdrawShouldThrowExceptionOnInsufficientFunds() {
    Account account = new Account();
    account.setBalance(0);
    assertThrows(AccountWithdrawException.class, () -> account.withdraw(50));
  }

  @Test
  void compareToShouldDefineEquality() {
    Account account1 = new Account();
    Account account2 = new Account();
    LocalDateTime now = LocalDateTime.now();
    account1.setOpenedAt(now);
    account2.setOpenedAt(now);
    account1.setNumber(1);
    account2.setNumber(1);
    assertEquals(0, account1.compareTo(account2));
  }

  @Test
  void compareToShouldDefineGreatest() {
    Account account1 = new Account();
    Account account2 = new Account();
    account1.setOpenedAt(LocalDateTime.now());
    account2.setOpenedAt(LocalDateTime.now());
    account1.setState(State.OPENED);
    account2.setState(State.OPENED);
    boolean compByDateIsC = account2.compareTo(account1) > 0;
    account2.setState(State.CLOSED);
    boolean compByStateIsC = account1.compareTo(account2) > 0;
    boolean testPass = compByDateIsC && compByStateIsC;
    assertTrue(testPass);
  }

  @Test
  void compareToShouldDefineLeast() {
    Account account1 = new Account();
    Account account2 = new Account();
    account1.setOpenedAt(LocalDateTime.now());
    account2.setOpenedAt(LocalDateTime.now());
    account1.setState(State.OPENED);
    account2.setState(State.OPENED);
    boolean compByDateIsC = account1.compareTo(account2) < 0;
    account2.setState(State.CLOSED);
    boolean compByStateIsC = account2.compareTo(account1) < 0;
    boolean testPass = compByDateIsC && compByStateIsC;
    assertTrue(testPass);
  }

  @Test
  void equalsShouldDefineEquality() {
    Account account1 = new Account();
    Account account2 = new Account();
    LocalDateTime now = LocalDateTime.now();
    account1.setOpenedAt(now);
    account2.setOpenedAt(now);
    account1.setNumber(1);
    account2.setNumber(1);
    assertEquals(0, account1.compareTo(account2));
  }

  @Test
  void testHashCode() {
    Account account = new Account();
    int number = 1;
    LocalDateTime now = LocalDateTime.now();
    int result = number;
    result = 31 * result + now.hashCode();
    account.setNumber(number);
    account.setOpenedAt(now);
    assertEquals(result, account.hashCode());
  }
}