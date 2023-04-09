package com.goryaninaa.web.bank.winter.repository.transaction;

import com.goryaninaa.web.bank.model.operation.Operation;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TransactionRepositoryPOJOTest {

    private static TransactionRepositoryPOJO transactionRepository;
    private static TransactionDAO transactionDAO;

    @BeforeEach
    void init() {
        transactionDAO = new TransactionDAOStub();
        transactionRepository = new TransactionRepositoryPOJO(transactionDAO);
    }

    @Test
    void saveShouldSaveToDAO() {
        transactionRepository.save(new Operation());
        assertTrue(((TransactionDAOStub)transactionDAO).isSaveInvoked());
    }

    @Test
    void findOperationsOfAccountShouldFindInDAO() {
        transactionRepository.findOperationsOfAccount(1);
        assertTrue(((TransactionDAOStub)transactionDAO).isFindInvoked());
    }
}