package com.goryaninaa.web.bank.winter.repository.account;

import com.goryaninaa.web.bank.model.account.Account;
import com.goryaninaa.web.bank.model.operation.Operation;
import com.goryaninaa.web.bank.service.operation.OperationRepository;
import com.goryaninaa.winter.cache.Cache;
import com.goryaninaa.winter.cache.CacheKeyFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class AccountRepositoryCachedTest {

    private static AccountRepositoryCached accountRepository;
    private static CacheKeyFactory cacheKeyFactory;
    private static Cache<Account> accountCache;
    private static AccountDAO accountDAO;
    private static OperationRepository operationRepository;
    @BeforeEach
    void init() {
        cacheKeyFactory = new AccountCacheKeyFactoryStub();
        accountCache = new AccountCacheStub();
        accountDAO = new AccountDAOStub();
        operationRepository = new OperationRepositoryStub();
        accountRepository = new AccountRepositoryCached(accountCache, accountDAO,
                 operationRepository, cacheKeyFactory);
    }

    @Test
    void saveShouldSaveCorrectly() {
        accountRepository.save(new Account());
        final boolean testPassed =
                ((AccountCacheKeyFactoryStub) cacheKeyFactory).isGenerateAllInvoked()
                && ((AccountCacheStub) accountCache).isRemoveInvoked()
                && ((AccountDAOStub) accountDAO).isSaveInvoked();
        assertTrue(testPassed);
    }

    @Test
    void findByNumberShouldCorrectlyReturnAccount() {
        final Account expectedAccount = new Account();
        final List<Operation> expectedOperationList = new ArrayList<>();
        ((AccountCacheStub) accountCache).setAccount(expectedAccount);
        ((OperationRepositoryStub) operationRepository).setOperationList(expectedOperationList);
        final Account actualAccount = accountRepository.findByNumber(1).orElseThrow();
        final List<Operation> actualOperationList = actualAccount.getHistory();
        final boolean testPassed = expectedAccount.equals(actualAccount)
                && expectedOperationList.equals(actualOperationList);
        assertTrue(testPassed);
    }

    @Test
    void updateShouldUpdateCorrectly() {
        accountRepository.update(new Account());
        final boolean testPassed =
                ((AccountCacheKeyFactoryStub) cacheKeyFactory).isGenerateAllInvoked()
                        && ((AccountCacheStub) accountCache).isRemoveInvoked()
                        && ((AccountDAOStub) accountDAO).isUpdateInvoked();
        assertTrue(testPassed);
    }
}