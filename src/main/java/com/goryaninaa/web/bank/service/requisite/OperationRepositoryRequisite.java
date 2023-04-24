package com.goryaninaa.web.bank.service.requisite;

import com.goryaninaa.web.bank.model.account.Account;
import com.goryaninaa.web.bank.model.operation.Operation;
import java.util.Optional;

public interface OperationRepositoryRequisite {
  Optional<Operation> getTopOp(Account account);
}
