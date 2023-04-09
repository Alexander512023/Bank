package com.goryaninaa.web.bank.service.account;

public class NumberCapacityRepositoryStub implements NumberCapacityRepository {

    private boolean getNumberInvoked;
    @Override
    public int getNumber() {
        getNumberInvoked = true;
        return 10;
    }

    public boolean isGetNumberInvoked() {
        return getNumberInvoked;
    }
}
