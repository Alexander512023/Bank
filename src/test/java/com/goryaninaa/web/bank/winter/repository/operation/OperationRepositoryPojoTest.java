package com.goryaninaa.web.bank.winter.repository.operation;

import static org.junit.jupiter.api.Assertions.assertTrue;

import com.goryaninaa.web.bank.education.winter.repository.operation.OperationDao;
import com.goryaninaa.web.bank.education.winter.repository.operation.OperationRepositoryPojo;
import com.goryaninaa.web.bank.domain.model.operation.Operation;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class OperationRepositoryPojoTest {

  private static OperationRepositoryPojo transactionRepository;
  private static OperationDao operationDAO;

  @BeforeEach
  void init() {
    operationDAO = new OperationDaoStub();
    transactionRepository = new OperationRepositoryPojo(operationDAO, new AccountDaoStub());
  }

  @Test
  void saveShouldSaveToDAO() {
    transactionRepository.save(new Operation());
    assertTrue(((OperationDaoStub) operationDAO).isSaveInvoked());
  }

  @Test
  void findOperationsOfAccountShouldFindInDAO() {
    operationDAO.findOperationsOfAccount(1);
    assertTrue(((OperationDaoStub) operationDAO).isFindInvoked());
  }
}