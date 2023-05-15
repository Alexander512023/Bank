package com.goryaninaa.web.bank.domain.service.operation;

import com.goryaninaa.web.bank.domain.model.account.Account;
import com.goryaninaa.web.bank.domain.model.account.AccountOpenRequisites;
import com.goryaninaa.web.bank.domain.model.operation.Operation;
import com.goryaninaa.web.bank.domain.model.operation.OperationRequisites;
import com.goryaninaa.web.bank.domain.service.account.RequisiteServiceAccount;
import com.goryaninaa.web.bank.exception.AccountDepositException;
import com.goryaninaa.web.bank.exception.AccountOpenException;
import com.goryaninaa.web.bank.exception.AccountWithdrawException;

/**
 * This is simple implementation of {@link OperationService} interface. It operates
 * {@link RequisiteServiceAccount} in order to decouple corresponding domain logic. Results of
 * performed operations are stored in the {@link OperationRepository}.
 */
public class OperationServicePojo implements OperationService {

  private final RequisiteServiceOperation requisiteService;
  private final OperationRepository operationRepo;

  public OperationServicePojo(final RequisiteServiceOperation requisiteService,
                              final OperationRepository operationRepo) {
    this.requisiteService = requisiteService;
    this.operationRepo = operationRepo;
  }

  @Override
  public void processAccountOpen(final Account account, final AccountOpenRequisites requisites)
      throws AccountOpenException {
    final OperationRequisites completedReq =
        requisiteService.prepareAccountOpenOperationRequisites(account, requisites);
    final Operation open = new Operation(completedReq);
    operationRepo.save(open);
  }

  @Override
  public void processDeposit(final Account account, final OperationRequisites requisites)
      throws AccountDepositException {
    final OperationRequisites completedReq =
        requisiteService.prepareDepositOperationRequisites(account, requisites);
    final Operation deposit = new Operation(completedReq);
    operationRepo.save(deposit);
  }

  @Override
  public void processWithdraw(final Account account, final OperationRequisites requisites)
      throws AccountWithdrawException {
    final OperationRequisites completedReq =
        requisiteService.prepareWithdrawOperationRequisites(account, requisites);
    final Operation withdraw = new Operation(completedReq);
    operationRepo.save(withdraw);
  }
}
