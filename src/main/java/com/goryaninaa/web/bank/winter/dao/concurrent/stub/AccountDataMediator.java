package com.goryaninaa.web.bank.winter.dao.concurrent.stub;

import com.goryaninaa.web.bank.model.account.Account;
import com.goryaninaa.winter.cache.DataAccessStrategy;
import com.goryaninaa.winter.cache.DataMediator;
import java.util.Map;

public class AccountDataMediator extends DataMediator<Account> {
    public AccountDataMediator(Map<String, DataAccessStrategy<Account>> dataAccesses) {
        super(dataAccesses);
    }
}
