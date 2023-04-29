package com.goryaninaa.web.bank.education.winter.repository.account;

import com.goryaninaa.web.bank.domain.model.account.Account;
import com.goryaninaa.web.bank.domain.model.client.Client;
import com.goryaninaa.web.bank.domain.model.operation.Operation;
import com.goryaninaa.web.bank.domain.service.account.AccountRepository;
import com.goryaninaa.web.bank.education.winter.repository.client.ClientDao;
import com.goryaninaa.web.bank.education.winter.repository.operation.OperationDao;
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
  private final OperationDao operDao;
  private final ClientDao clientDao;

  /**
   * Create class with this constructor, which receives all fields.
   *
   * @param cache - cache, where all demanded data can be saved in RAM.
   * @param accountDao - is {@link Account} data access object
   * @param operDao - repository, where operations are stored
   * @param cacheKeyFactory - factory class, that allows generation of keys, by which data could
   *      be extracted from cache
   */
  public AccountRepositoryCached(final Cache<Account> cache, final AccountDao accountDao,
                                 final OperationDao operDao,
                                 final ClientDao clientDao,
                                 final CacheKeyFactory cacheKeyFactory) {
    this.cache = cache;
    this.cacheKeyFactory = cacheKeyFactory;
    this.accountDao = accountDao;
    this.operDao = operDao;
    this.clientDao = clientDao;
  }

  @Override
  public Account save(final Account account) {
    final List<CacheKey> cacheKeys = cacheKeyFactory.generateAllCacheKeys(account);
    cache.remove(cacheKeys);
    accountDao.save(account);
    final int accNum = account.getNumber();
    return accountDao.getOneByNumber(accNum).orElseThrow();
  }

  @Override
  public Optional<Account> findByNumber(final int number) {
    final CacheKey cacheKey =
        cacheKeyFactory.generateCacheKey(number, AccountAccessStrategyType.NUMBER);
    final Optional<Account> account = cache.getData(cacheKey);
    account.ifPresent(this::enrichAccount);
    return account;
  }

  @Override
  public void update(final Account account) {
    final List<CacheKey> cacheKeys = cacheKeyFactory.generateAllCacheKeys(account);
    cache.remove(cacheKeys);
    accountDao.update(account);
  }

  private void enrichAccount(Account account) {
    final List<Operation> transactions =
        operDao.findOperationsOfAccount(account.getAccountId());
    transactions.sort(Comparator.comparing(Operation::getHistoryNumber));
    account.setHistory(transactions);
    final Client owner = clientDao.findById(account.getOwner().getClientId()).orElseThrow();
    account.setOwner(owner);
  }

}
