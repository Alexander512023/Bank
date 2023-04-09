package com.goryaninaa.web.bank.winter.repository.account;

import com.goryaninaa.winter.cache.CacheKey;
import com.goryaninaa.winter.cache.CacheKeyFactory;
import java.util.List;

public class AccountCacheKeyFactoryStub implements CacheKeyFactory {

    private boolean generateAllInvoked;

    @Override
    public CacheKey generateCacheKey(Object o, String s) {
        return null;
    }

    @Override
    public List<CacheKey> generateAllCacheKeys(Object o) {
        generateAllInvoked = true;
        return null;
    }

    public boolean isGenerateAllInvoked() {
        return generateAllInvoked;
    }
}
