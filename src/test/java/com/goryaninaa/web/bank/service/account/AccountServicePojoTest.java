package com.goryaninaa.web.bank.service.account;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.goryaninaa.web.bank.exception.AccountDepositException;
import com.goryaninaa.web.bank.exception.AccountFindException;
import com.goryaninaa.web.bank.exception.AccountOpenException;
import com.goryaninaa.web.bank.exception.AccountWithdrawException;
import com.goryaninaa.web.bank.model.account.Account;
import com.goryaninaa.web.bank.model.account.AccountOpenRequisites;
import com.goryaninaa.web.bank.model.operation.OperationRequisites;
import com.goryaninaa.web.bank.service.util.RequisitesGenerator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class AccountServicePojoTest {

  private static AccountServicePojo accountService;
  private static AccountRepository accountRepository;
  private static NumberCapacityRepository numberCapacityRepository;
  private static OperationServiceAccount operationServiceAccount;


  @BeforeEach
  void initEnv() {
    accountRepository = new AccountRepositoryStub();
    numberCapacityRepository = new NumberCapacityRepositoryStub();
    operationServiceAccount = new OperationServiceAccountStub();
    RequisiteServiceAccount requisiteServiceAccount = new RequisiteServiceAccountStub();
    accountService = new AccountServicePojo(accountRepository, operationServiceAccount,
        numberCapacityRepository, requisiteServiceAccount);
  }

  @Test
  void openShouldSaveAndProcessNewAccount() throws AccountOpenException {
    AccountOpenRequisites requisites = RequisitesGenerator.defineOpenRequisites();
    accountService.open(requisites);
    final boolean testPassed = ((AccountRepositoryStub) accountRepository).isSaveInvoked()
        && ((NumberCapacityRepositoryStub) numberCapacityRepository).isGetNumberInvoked()
        && ((OperationServiceAccountStub) operationServiceAccount).isProcessAccountOpenInvoked();
    assertTrue(testPassed);
  }

  @Test
  void depositShouldReflectOnAccountAndProcess()
      throws AccountDepositException, AccountFindException {
    OperationRequisites operationRequisites = RequisitesGenerator.defineOperationRequisites();
    int balanceBefore = accountRepository.findByNumber(2).orElseThrow().getBalance();
    accountService.deposit(operationRequisites);
    int balanceAfter = accountRepository.findByNumber(2).orElseThrow().getBalance();
    boolean isDepositCompleted = balanceBefore == 10 && balanceAfter == 15;
    final boolean testPassed = isDepositCompleted
        && ((OperationServiceAccountStub) operationServiceAccount).isProcessDepositInvoked();
    assertTrue(testPassed);
  }

  @Test
  void withdrawShouldReflectOnAccountAndProcess()
      throws AccountFindException, AccountWithdrawException {
    OperationRequisites operationRequisites = RequisitesGenerator.defineOperationRequisites();
    int balanceBefore = accountRepository.findByNumber(1).orElseThrow().getBalance();
    accountService.withdraw(operationRequisites);
    int balanceAfter = accountRepository.findByNumber(1).orElseThrow().getBalance();
    boolean isDepositCompleted = balanceBefore == 10 && balanceAfter == 5;
    final boolean testPassed = isDepositCompleted
        && ((OperationServiceAccountStub) operationServiceAccount).isProcessWithdrawInvoked();
    assertTrue(testPassed);
  }

  @Test
  void transferShouldReflectOnAccountAndProcess()
      throws AccountWithdrawException, AccountDepositException, AccountFindException {
    OperationRequisites operationRequisites = RequisitesGenerator.defineOperationRequisites();
    int balanceFromBefore = accountRepository.findByNumber(1).orElseThrow().getBalance();
    int balanceRecipientBefore = accountRepository.findByNumber(2).orElseThrow().getBalance();
    accountService.transfer(operationRequisites);
    int balanceFromAfter = accountRepository.findByNumber(1).orElseThrow().getBalance();
    int balanceRecipientAfter = accountRepository.findByNumber(2).orElseThrow().getBalance();
    boolean isTransferCompleted = balanceFromBefore == 10
        && balanceFromAfter == 5
        && balanceRecipientBefore == 10
        && balanceRecipientAfter == 15;
    final boolean testPassed = isTransferCompleted
        && ((OperationServiceAccountStub) operationServiceAccount).isProcessDepositInvoked()
        && ((OperationServiceAccountStub) operationServiceAccount).isProcessWithdrawInvoked();
    assertTrue(testPassed);
  }

  @Test
  void findByNumberShouldFindAccountWithSuchNumber() throws AccountFindException {
    Account account = accountService.findByNumber(1);
    int expected = 1;
    int actual = account.getNumber();
    assertEquals(expected, actual);
  }

}