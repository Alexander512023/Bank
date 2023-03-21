package com.goryaninaa.web.bank.winter.dao.concurrent.stub;

import com.goryaninaa.web.bank.model.account.Account;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;


public class AccountDAO {
	
	private static final AtomicInteger idCounter = new AtomicInteger(0);
	private final List<Account> accounts = new CopyOnWriteArrayList<>();

	public void save(Account account) {
		for (Account savedEarlierAccount : accounts) {
			if (savedEarlierAccount.equals(account)) {
				throw new RuntimeException("This account already exists");
			}
		}
		account.setId(idCounter.addAndGet(1));
		accounts.add(account);
	}

	public Account getOneByNumber(int number) {
		return accounts.stream()
				.filter(acc -> acc.getNumber() == number).findFirst().orElseThrow();
	}

//	public Optional<Account> findByNumber(int number) {
//		Optional<Account> desiredAccount = Optional.empty();
//		for (Account account : super.getDataList()) {
//			if (account.getNumber() == number) {
//				desiredAccount = Optional.ofNullable(account);
//				break;
//			}
//		}
//		return desiredAccount;
//	}

	public void update(Account account) {
		accounts.stream().filter(a -> a.getId() != account.getId()).collect(Collectors.toList());
		accounts.add(account);
	}

}
