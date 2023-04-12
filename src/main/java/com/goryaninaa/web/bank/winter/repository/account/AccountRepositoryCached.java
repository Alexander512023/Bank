package com.goryaninaa.web.bank.winter.repository.account;

import com.goryaninaa.web.bank.model.account.Account;
import com.goryaninaa.web.bank.model.operation.Operation;
import com.goryaninaa.web.bank.service.account.AccountRepository;
import com.goryaninaa.web.bank.service.operation.OperationRepository;
import com.goryaninaa.winter.cache.Cache;
import com.goryaninaa.winter.cache.CacheKey;
import com.goryaninaa.winter.cache.CacheKeyFactory;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

public class AccountRepositoryCached implements AccountRepository {

  private final Cache<Account> cache;
  private final CacheKeyFactory cacheKeyFactory;
  private final AccountDAO accountDAO;
  private final OperationRepository operationRepository;

  public AccountRepositoryCached(Cache<Account> cache, AccountDAO accountDAO,
                                 OperationRepository operationRepository,
                                 CacheKeyFactory cacheKeyFactory) {
    this.cache = cache;
    this.cacheKeyFactory = cacheKeyFactory;
    this.accountDAO = accountDAO;
    this.operationRepository = operationRepository;
  }

  @Override
  public void save(Account account) {
    List<CacheKey> cacheKeys = cacheKeyFactory.generateAllCacheKeys(account);
    cache.remove(cacheKeys);
    accountDAO.save(account);
  }

  @Override
  public Optional<Account> findByNumber(int number) {
    CacheKey cacheKey = cacheKeyFactory.generateCacheKey(number, AccountAccessStrategyType.NUMBER);
    Optional<Account> account = cache.getData(cacheKey);
    if (account.isPresent()) {
      List<Operation> transactions =
          operationRepository.findOperationsOfAccount(account.get().getId());
      transactions.sort(Comparator.comparing(Operation::getHistoryNumber));
      account.get().setHistory(transactions);
    }
    return account;
  }

  @Override
  public void update(Account account) {
    List<CacheKey> cacheKeys = cacheKeyFactory.generateAllCacheKeys(account);
    cache.remove(cacheKeys);
    accountDAO.update(account);
  }
}
