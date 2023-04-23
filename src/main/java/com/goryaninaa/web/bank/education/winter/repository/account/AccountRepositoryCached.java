package com.goryaninaa.web.bank.education.winter.repository.account;

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

/**
 * Implementation of {@link AccountRepository} that supports caching.
 */
public class AccountRepositoryCached implements AccountRepository {

  private final Cache<Account> cache;
  private final CacheKeyFactory cacheKeyFactory;
  private final AccountDao accountDao;
  private final OperationRepository operRepository;

  /**
   * Create class with this constructor, which receives all fields.
   *
   * @param cache - cache, where all demanded data can be saved in RAM.
   * @param accountDao - is {@link Account} data access object
   * @param operRepository - repository, where operations are stored
   * @param cacheKeyFactory - factory class, that allows generation of keys, by which data could
   *      be extracted from cache
   */
  public AccountRepositoryCached(final Cache<Account> cache, final AccountDao accountDao,
                                 final OperationRepository operRepository,
                                 final CacheKeyFactory cacheKeyFactory) {
    this.cache = cache;
    this.cacheKeyFactory = cacheKeyFactory;
    this.accountDao = accountDao;
    this.operRepository = operRepository;
  }

  @Override
  public void save(final Account account) {
    final List<CacheKey> cacheKeys = cacheKeyFactory.generateAllCacheKeys(account);
    cache.remove(cacheKeys);
    accountDao.save(account);
  }

  @Override
  public Optional<Account> findByNumber(final int number) {
    final CacheKey cacheKey =
        cacheKeyFactory.generateCacheKey(number, AccountAccessStrategyType.NUMBER);
    final Optional<Account> account = cache.getData(cacheKey);
    if (account.isPresent()) {
      final List<Operation> transactions =
          operRepository.findOperationsOfAccount(account.get().getAccountId());
      transactions.sort(Comparator.comparing(Operation::getHistoryNumber));
      account.get().setHistory(transactions);
    }
    return account;
  }

  @Override
  public void update(final Account account) {
    final List<CacheKey> cacheKeys = cacheKeyFactory.generateAllCacheKeys(account);
    cache.remove(cacheKeys);
    accountDao.update(account);
  }
}
