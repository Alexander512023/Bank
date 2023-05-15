package com.goryaninaa.web.bank.domain.service.requisite;

import com.goryaninaa.web.bank.domain.model.account.Account;
import com.goryaninaa.web.bank.domain.model.operation.Operation;
import java.util.Optional;

/**
 * This is interface, that should provide functionality of finding operation data for requisite
 * completion.
 */
public interface OperationRepositoryRequisite {
  Optional<Operation> getTopOp(Account account);
}
