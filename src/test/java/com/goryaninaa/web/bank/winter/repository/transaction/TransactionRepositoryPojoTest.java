package com.goryaninaa.web.bank.winter.repository.transaction;

import static org.junit.jupiter.api.Assertions.assertTrue;

import com.goryaninaa.web.bank.model.operation.Operation;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class TransactionRepositoryPojoTest {

  private static TransactionRepositoryPojo transactionRepository;
  private static TransactionDao transactionDAO;

  @BeforeEach
  void init() {
    transactionDAO = new TransactionDaoStub();
    transactionRepository = new TransactionRepositoryPojo(transactionDAO);
  }

  @Test
  void saveShouldSaveToDAO() {
    transactionRepository.save(new Operation());
    assertTrue(((TransactionDaoStub) transactionDAO).isSaveInvoked());
  }

  @Test
  void findOperationsOfAccountShouldFindInDAO() {
    transactionRepository.findOperationsOfAccount(1);
    assertTrue(((TransactionDaoStub) transactionDAO).isFindInvoked());
  }
}