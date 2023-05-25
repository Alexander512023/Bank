package com.goryaninaa.web.bank.domain.service.account.authorization;

import com.goryaninaa.web.bank.domain.model.account.AccountOpenRequisites;
import com.goryaninaa.web.bank.domain.model.client.Client;
import com.goryaninaa.web.bank.domain.model.operation.OperationRequisites;
import com.goryaninaa.web.bank.domain.service.account.AccountRepository;
import com.goryaninaa.web.bank.domain.service.requisite.ClientRepository;
import com.goryaninaa.web.bank.exception.AuthorizationException;
import com.goryaninaa.web.bank.exception.BankRuntimeException;
import com.goryaninaa.winter.web.http.server.entity.Authentication;

public class AccountAuthorizationServiceBasic implements AccountAuthorizationService {

  private final ClientRepository clientRepository;
  private final AccountRepository accountRepository;

  public AccountAuthorizationServiceBasic(ClientRepository clientRepository,
                                          AccountRepository accountRepository) {
    this.clientRepository = clientRepository;
    this.accountRepository = accountRepository;
  }

  @Override
  public void authorizeOpen(Authentication auth, AccountOpenRequisites accountRequisites) {
    checkInitiator(auth, accountRequisites.getOperRequisites().getClient().getPassport());
  }

  @Override
  public void authorizeDeposit(Authentication auth, OperationRequisites requisites) {
    checkInitiator(auth, requisites.getClient().getPassport());
  }

  @Override
  public void authorizeWithdraw(Authentication auth, OperationRequisites requisites) {
    int accNum = requisites.getAccount().getNumber();
    checkInitiator(auth, requisites.getClient().getPassport());
    checkOwner(auth, accNum);
  }

  @Override
  public void authorizeTransfer(Authentication auth, OperationRequisites requisites) {
    checkInitiator(auth, requisites.getClient().getPassport());
  }

  @Override
  public void authorizeView(Authentication auth, String accNumStr) {
    int accNum = Integer.parseInt(accNumStr);
    checkOwner(auth, accNum);
  }

  private void checkInitiator(Authentication auth, String initiatorPassp) {
    String authPassp = auth.getLogin().trim().replace("%20", " ");
    if (!authPassp.equals(initiatorPassp)) {
      throw new AuthorizationException("This authentication belongs to another user");
    }
  }

  private void checkOwner(Authentication auth, int accNum) {
    Client initiator = clientRepository.findByPassport(auth.getLogin().trim().
            replace("%20", " ")).
        orElseThrow(() -> new BankRuntimeException("No such client"));
    Client owner = accountRepository.findByNumber(accNum).
        orElseThrow(() -> new BankRuntimeException("No such account")).getOwner();
    if (!initiator.equals(owner)) {
      throw new AuthorizationException("This operation only authorized for owner");
    }
  }
}
