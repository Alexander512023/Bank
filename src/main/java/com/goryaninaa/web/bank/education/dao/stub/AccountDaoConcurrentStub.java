package com.goryaninaa.web.bank.education.dao.stub;

import com.goryaninaa.web.bank.domain.model.account.Account;
import com.goryaninaa.web.bank.education.winter.repository.account.AccountDao;
import com.sun.jdi.request.DuplicateRequestException;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.atomic.AtomicInteger;


@SuppressWarnings("unused")
public class AccountDaoConcurrentStub implements AccountDao {

  private static final AtomicInteger idCounter = new AtomicInteger(0);
  private final List<Account> accounts = new CopyOnWriteArrayList<>();

  @Override
  public void save(Account account) {
    for (Account savedEarlierAccount : accounts) {
      if (savedEarlierAccount.equals(account)) {
        throw new DuplicateRequestException("This account already exists");
      }
    }
    account.setAccountId(idCounter.addAndGet(1));
    accounts.add(account);
  }

  @Override
  public Optional<Account> getOneByNumber(int number) {
    return Optional.of(accounts.stream()
        .filter(acc -> acc.getNumber() == number).findFirst().orElseThrow());
  }

  @Override
  public void update(Account account) {
    accounts.remove(account);
    accounts.add(account);
  }

}
