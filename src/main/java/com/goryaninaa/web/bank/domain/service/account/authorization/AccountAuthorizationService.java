package com.goryaninaa.web.bank.domain.service.account.authorization;

import com.goryaninaa.web.bank.domain.model.account.AccountOpenRequisites;
import com.goryaninaa.web.bank.domain.model.operation.OperationRequisites;
import com.goryaninaa.web.bank.exception.AuthorizationException;
import com.goryaninaa.winter.web.http.server.entity.Authentication;

public interface AccountAuthorizationService {

  void authorizeOpen(Authentication auth, AccountOpenRequisites accountRequisites) throws
      AuthorizationException;
  void authorizeDeposit(Authentication auth, OperationRequisites requisites) throws
      AuthorizationException;

  void authorizeWithdraw(Authentication auth, OperationRequisites requisites) throws
      AuthorizationException;

  void authorizeTransfer(Authentication auth, OperationRequisites requisites) throws
      AuthorizationException;

  void authorizeView(Authentication auth, String accNumStr) throws
      AuthorizationException;

}
