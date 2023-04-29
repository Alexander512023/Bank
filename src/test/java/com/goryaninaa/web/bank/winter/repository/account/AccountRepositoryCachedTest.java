package com.goryaninaa.web.bank.winter.repository.account;

import static org.junit.jupiter.api.Assertions.assertTrue;

import com.goryaninaa.web.bank.domain.model.account.Account;
import com.goryaninaa.web.bank.domain.model.client.Client;
import com.goryaninaa.web.bank.domain.model.operation.Operation;
import com.goryaninaa.web.bank.education.winter.repository.account.AccountDao;
import com.goryaninaa.web.bank.education.winter.repository.account.AccountRepositoryCached;
import com.goryaninaa.web.bank.education.winter.repository.client.ClientDao;
import com.goryaninaa.web.bank.education.winter.repository.operation.OperationDao;
import com.goryaninaa.winter.cache.Cache;
import com.goryaninaa.winter.cache.CacheKeyFactory;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class AccountRepositoryCachedTest {

  private static AccountRepositoryCached accountRepository;
  private static CacheKeyFactory cacheKeyFactory;
  private static Cache<Account> accountCache;
  private static AccountDao accountDAO;
  private static OperationDao operationDao;
  private static ClientDao clientDao;

  @BeforeEach
  void init() {
    cacheKeyFactory = new AccountCacheKeyFactoryStub();
    accountCache = new AccountCacheStub();
    accountDAO = new AccountDaoStub();
    operationDao = new OperationDaoStub();
    clientDao = new ClientDaoStub();
    accountRepository = new AccountRepositoryCached(accountCache, accountDAO,
        operationDao, clientDao, cacheKeyFactory);
  }

  @Test
  void saveShouldSaveCorrectly() {
    Account acc = new Account();
    acc.setNumber(1);
    accountRepository.save(acc);
    final boolean testPassed =
        ((AccountCacheKeyFactoryStub) cacheKeyFactory).isGenerateAllInvoked()
            && ((AccountCacheStub) accountCache).isRemoveInvoked()
            && ((AccountDaoStub) accountDAO).isSaveInvoked();
    assertTrue(testPassed);
  }

  @Test
  void findByNumberShouldCorrectlyReturnAccount() {
    final Account expectedAccount = new Account();
    final Client cFromDao = new Client();
    cFromDao.setClientId(1);
    expectedAccount.setOwner(cFromDao);
    final List<Operation> expectedOperationList = new ArrayList<>();
    final Client expectedOwner = new Client();
    expectedOwner.setClientId(1);
    ((AccountCacheStub) accountCache).setAccount(expectedAccount);
    ((OperationDaoStub) operationDao).setOperationList(expectedOperationList);
    ((ClientDaoStub)clientDao).setClient(expectedOwner);
    final Account actualAccount = accountRepository.findByNumber(1).orElseThrow();
    final List<Operation> actualOperationList = actualAccount.getHistory();
    final Client actualOwner = actualAccount.getOwner();
    final boolean testPassed = expectedAccount.equals(actualAccount)
        && expectedOperationList.equals(actualOperationList)
        && expectedOwner.equals(actualOwner);
    assertTrue(testPassed);
  }

  @Test
  void updateShouldUpdateCorrectly() {
    accountRepository.update(new Account());
    final boolean testPassed =
        ((AccountCacheKeyFactoryStub) cacheKeyFactory).isGenerateAllInvoked()
            && ((AccountCacheStub) accountCache).isRemoveInvoked()
            && ((AccountDaoStub) accountDAO).isUpdateInvoked();
    assertTrue(testPassed);
  }
}