package com.goryaninaa.web.bank.service.account;

import java.util.Optional;

import com.goryaninaa.web.bank.model.account.Account;
import com.goryaninaa.web.bank.model.account.AccountOpenRequisites;
import com.goryaninaa.web.bank.model.operation.OperationRequisites;
import com.goryaninaa.web.bank.exception.AccountDepositException;
import com.goryaninaa.web.bank.exception.AccountFindException;
import com.goryaninaa.web.bank.exception.AccountOpenException;
import com.goryaninaa.web.bank.exception.AccountWithdrawException;

public class AccountServicePojo implements AccountService {

	private final AccountRepository accountRepository;
	private final OperationServiceAccount operationService;
	private final NumberCapacityRepository numberCapacityRepository;
	private final RequisiteServiceAccount requisiteService;

	public AccountServicePojo(AccountRepository accountRepository, OperationServiceAccount operationService,
							  NumberCapacityRepository numberCapacityRepository, RequisiteServiceAccount requisiteService) {
		this.accountRepository = accountRepository;
		this.operationService = operationService;
		this.numberCapacityRepository = numberCapacityRepository;
		this.requisiteService = requisiteService;
	}

	@Override
	public void open(AccountOpenRequisites requisites) throws AccountOpenException {
		Account account = openAccount(requisites);
		accountRepository.save(account);
		operationService.processAccountOpen(account, requisites);
	}
	
	@Override
	public void deposit(OperationRequisites requisites) throws AccountFindException, AccountDepositException {
		Account account = findByNumber(requisites.getAccountRecipient().getNumber());
		account.deposit(requisites.getAmount());
		accountRepository.update(account);
		operationService.processDeposit(account, requisites);
	}
	
	@Override
	public void withdraw(OperationRequisites requisites) throws AccountFindException, AccountWithdrawException {
		Account account = findByNumber(requisites.getAccountFrom().getNumber());
		account.withdraw(requisites.getAmount());
		accountRepository.update(account);
		operationService.processWithdraw(account, requisites);
	}

	@Override
	public void transfer(OperationRequisites requisites)
			throws AccountFindException, AccountDepositException, AccountWithdrawException  {
		deposit(requisites);
		withdraw(requisites);
	}
	
	@Override
	public Account findByNumber(int number) throws AccountFindException {
		return findAccount(number);
	}

	private Account openAccount(AccountOpenRequisites requisites) throws AccountOpenException {
		AccountOpenRequisites completedRequisites = requisiteService.prepareAccountOpenRequisites(requisites);
		int accountNumber = numberCapacityRepository.getNumber();
		return new Account(completedRequisites, accountNumber);
	}

	private Account findAccount(int number) throws AccountFindException {
		Optional<Account> account = accountRepository.findByNumber(number);
		if (account.isPresent()) {
			return account.get();
		} else {
			throw new AccountFindException("There is no account with such number", new IllegalArgumentException());
		}
	}
}