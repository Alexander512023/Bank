package com.goryaninaa.web.bank.service.account;

import com.goryaninaa.web.bank.exception.AccountDepositException;
import com.goryaninaa.web.bank.exception.AccountFindException;
import com.goryaninaa.web.bank.exception.AccountOpenException;
import com.goryaninaa.web.bank.exception.AccountWithdrawException;
import com.goryaninaa.web.bank.model.account.Account;
import com.goryaninaa.web.bank.model.account.AccountOpenRequisites;
import com.goryaninaa.web.bank.model.operation.OperationRequisites;
import java.util.Optional;

/**
 * This is simple implementation of {@link AccountService} interface. It operates
 * {@link RequisiteServiceAccount} and {@link OperationServiceAccount} in order to decouple
 * corresponding domain logic. Results of performed operations are stored in the
 * {@link AccountRepository}. New account numbers are being taken from
 * {@link NumberCapacityRepository}.
 */
public class AccountServicePojo implements AccountService {

  private final AccountRepository accountRepository;
  private final OperationServiceAccount operationService;
  private final NumberCapacityRepository numCapacityRep;
  private final RequisiteServiceAccount requisiteService;

  /**
   * This class has only one constructor, that receives all fields of this class.
   *
   * @param accountRepository - to store results of performed operations
   * @param operationService - provide functionality to process corresponding operations
   * @param numCapacityRep - provide functionality to request new account numbers
   * @param requisiteService - provide functionality to complete and enrich corresponding requisites
   */
  public AccountServicePojo(final AccountRepository accountRepository,
                            final OperationServiceAccount operationService,
                            final NumberCapacityRepository numCapacityRep,
                            final RequisiteServiceAccount requisiteService) {
    this.accountRepository = accountRepository;
    this.operationService = operationService;
    this.numCapacityRep = numCapacityRep;
    this.requisiteService = requisiteService;
  }

  @Override
  public void open(final AccountOpenRequisites requisites) throws AccountOpenException {
    final Account account = openAccount(requisites);
    accountRepository.save(account);
    operationService.processAccountOpen(account, requisites);
  }

  @Override
  public void deposit(final OperationRequisites requisites)
      throws AccountFindException, AccountDepositException {
    final Account account = findByNumber(requisites.getAccountRecipient().getNumber());
    account.deposit(requisites.getAmount());
    accountRepository.update(account);
    operationService.processDeposit(account, requisites);
  }

  @Override
  public void withdraw(final OperationRequisites requisites)
      throws AccountFindException, AccountWithdrawException {
    final Account account = findByNumber(requisites.getAccountFrom().getNumber());
    account.withdraw(requisites.getAmount());
    accountRepository.update(account);
    operationService.processWithdraw(account, requisites);
  }

  @Override
  public void transfer(final OperationRequisites requisites)
      throws AccountFindException, AccountDepositException, AccountWithdrawException {
    deposit(requisites);
    withdraw(requisites);
  }

  @Override
  public Account findByNumber(final int number) throws AccountFindException {
    return findAccount(number);
  }

  private Account openAccount(final AccountOpenRequisites requisites) throws AccountOpenException {
    final AccountOpenRequisites completedReq =
        requisiteService.prepareAccountOpenRequisites(requisites);
    final int accountNumber = numCapacityRep.getNumber();
    return new Account(completedReq, accountNumber);
  }

  private Account findAccount(final int number) throws AccountFindException {
    final Optional<Account> account = accountRepository.findByNumber(number);
    if (account.isPresent()) {
      return account.get();
    } else {
      throw new AccountFindException("There is no account with such number",
          new IllegalArgumentException());
    }
  }
}