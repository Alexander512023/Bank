package com.goryaninaa.web.bank.domain.service.requisite;

import com.goryaninaa.web.bank.domain.model.account.Account;
import com.goryaninaa.web.bank.domain.model.operation.Operation;
import java.util.Optional;

public interface OperationRepositoryRequisite {
  Optional<Operation> getTopOp(Account account);
}
