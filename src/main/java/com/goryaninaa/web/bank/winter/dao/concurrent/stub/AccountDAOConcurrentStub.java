package com.goryaninaa.web.bank.winter.dao.concurrent.stub;

import com.goryaninaa.web.bank.model.account.Account;
import com.goryaninaa.web.bank.winter.repository.account.AccountDAO;
import com.sun.jdi.request.DuplicateRequestException;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.atomic.AtomicInteger;


public class AccountDAOConcurrentStub implements AccountDAO {
	
	private static final AtomicInteger idCounter = new AtomicInteger(0);
	private final List<Account> accounts = new CopyOnWriteArrayList<>();

	@Override
	public void save(Account account) {
		for (Account savedEarlierAccount : accounts) {
			if (savedEarlierAccount.equals(account)) {
				throw new DuplicateRequestException("This account already exists");
			}
		}
		account.setId(idCounter.addAndGet(1));
		accounts.add(account);
	}

	public Account getOneByNumber(int number) {
		return accounts.stream()
				.filter(acc -> acc.getNumber() == number).findFirst().orElseThrow();
	}

	@Override
	public void update(Account account) {
		accounts.remove(account);
		accounts.add(account);
	}

}
