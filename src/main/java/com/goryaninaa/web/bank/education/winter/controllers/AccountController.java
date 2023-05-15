package com.goryaninaa.web.bank.education.winter.controllers;

import com.goryaninaa.web.bank.domain.model.account.Account;
import com.goryaninaa.web.bank.domain.model.account.AccountOpenRequisites;
import com.goryaninaa.web.bank.domain.model.operation.Operation;
import com.goryaninaa.web.bank.domain.model.operation.OperationRequisites;
import com.goryaninaa.web.bank.domain.service.account.AccountService;
import com.goryaninaa.web.bank.dto.AccountDto;
import com.goryaninaa.web.bank.dto.AccountOpenRequisitesDto;
import com.goryaninaa.web.bank.dto.ClientDto;
import com.goryaninaa.web.bank.dto.ErrorDto;
import com.goryaninaa.web.bank.dto.OperationDto;
import com.goryaninaa.winter.logger.mech.Logger;
import com.goryaninaa.winter.logger.mech.LoggingMech;
import com.goryaninaa.winter.logger.mech.StackTraceString;
import com.goryaninaa.winter.web.http.server.Controller;
import com.goryaninaa.winter.web.http.server.HttpResponseCode;
import com.goryaninaa.winter.web.http.server.Response;
import com.goryaninaa.winter.web.http.server.annotation.HttpMethod;
import com.goryaninaa.winter.web.http.server.annotation.Mapping;
import com.goryaninaa.winter.web.http.server.annotation.RequestMapping;
import com.goryaninaa.winter.web.http.server.entity.HttpRequest;
import com.goryaninaa.winter.web.http.server.entity.HttpResponse;
import com.goryaninaa.winter.web.http.server.util.Synchronizer;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

/**
 * Implementation of Winter framework controller. Works somehow similar to Spring framework
 * controllers. Point your mapping, get incoming JSON objects and send HTTP responses. You should
 * pass service class, that will handle requests from the point of view of domain logic. Also, this
 * implementation is responsible for synchronization duties. Objects-locks are taken from
 * special {@link Synchronizer} class, that you should also correctly configure to ensure
 * operability.
 */
@SuppressWarnings("unused")
@RequestMapping("/account")
public class AccountController implements Controller {

  private final AccountService accountService;
  private final Synchronizer<Integer> accSynchronizer;
  private static final Logger LOG =
      LoggingMech.getLogger(AccountController.class.getCanonicalName());

  public AccountController(
      final AccountService accountService, final Synchronizer<Integer> accSynchronizer) {
    this.accountService = accountService;
    this.accSynchronizer = accSynchronizer;
  }

  /**
   * This method is responsible for handling /account/open mapping with HTTP POST method. So it
   * get account open requisites DTO from client's side and should return appropriate HTTP response
   * after further handling. If handling fails for some reason, response with code 404 or 500 and
   * error text in it will be sent.
   *
   * @param request - {@link HttpRequest} is an object representation of incoming request
   * @param requisitesDto - requisites object that was sent by client's side and deserialized from
   *                      JSON
   * @return - HTTP response that will be sent to client's side
   */
  @Mapping(value = "/open", httpMethod = HttpMethod.POST)
  public Response open(final HttpRequest request, final AccountOpenRequisitesDto requisitesDto) {
    final AccountOpenRequisites accountRequisites = requisitesDto.extractAccountRequisites();
    try {
      accountService.open(accountRequisites);
      return new HttpResponse(HttpResponseCode.OK); //NOPMD - suppressed OnlyOneReturn - errors
      // turned to objects on this level
    } catch (Exception e) { //NOPMD - suppressed AvoidCatchingGenericException - top level, correct
      LOG.error(StackTraceString.get(e)); //NOPMD - suppressed GuardLogStatement - no concatenation
      // - no need in check
      return prepareResponseOnException(e);
    }
  }

  /**
   * This method is responsible for handling /account/deposit mapping with HTTP POST method. So it
   * get operation requisites DTO from client's side and should return appropriate HTTP response
   * after further handling. If handling fails for some reason, response with code 404 or 500 and
   * error text in it will be sent.
   *
   * @param request - {@link HttpRequest} is an object representation of incoming request
   * @param operationDto - requisites object that was sent by client's side and deserialized from
   *                     JSON
   * @return - HTTP response that will be sent to client's side
   */
  @Mapping(value = "/deposit", httpMethod = HttpMethod.POST)
  public Response deposit(final HttpRequest request, final OperationDto operationDto) {
    final OperationRequisites requisites = operationDto.extractOperationRequisites();
    try {
      synchronized (accSynchronizer.getLock(requisites.getAccountRecipient().getNumber())) {
        accountService.deposit(requisites);
        return new HttpResponse(HttpResponseCode.OK); //NOPMD - suppressed OnlyOneReturn - errors
        // turned to objects on this level
      }
    } catch (Exception e) { //NOPMD - suppressed AvoidCatchingGenericException - top level, correct
      LOG.error(StackTraceString.get(e)); //NOPMD - suppressed GuardLogStatement - no concatenation
      // - no need in check
      return prepareResponseOnException(e);
    }
  }

  /**
   * This method is responsible for handling /account/withdraw mapping with HTTP POST method. So it
   * get operation requisites DTO from client's side and should return appropriate HTTP response
   * after further handling. If handling fails for some reason, response with code 404 or 500 and
   * error text in it will be sent.
   *
   * @param request - {@link HttpRequest} is an object representation of incoming request
   * @param operationDto - requisites object that was sent by client's side and deserialized from
   *                     JSON
   * @return - HTTP response that will be sent to client's side
   */
  @Mapping(value = "/withdraw", httpMethod = HttpMethod.POST)
  public Response withdraw(final HttpRequest request, final OperationDto operationDto) {
    final OperationRequisites requisites = operationDto.extractOperationRequisites();
    try {
      synchronized (accSynchronizer.getLock(requisites.getAccountFrom().getNumber())) {
        accountService.withdraw(requisites);
        return new HttpResponse(HttpResponseCode.OK); //NOPMD - suppressed OnlyOneReturn - errors
        // turned to objects on this level
      }
    } catch (Exception e) { //NOPMD - suppressed AvoidCatchingGenericException - top level, correct
      LOG.error(StackTraceString.get(e)); //NOPMD - suppressed GuardLogStatement - no concatenation
      // - no need in check
      return prepareResponseOnException(e);
    }
  }

  /**
   * This method is responsible for handling /account/transfer mapping with HTTP POST method. So it
   * get operation requisites DTO from client's side and should return appropriate HTTP response
   * after further handling. If handling fails for some reason, response with code 404 or 500 and
   * error text in it will be sent.
   *
   * @param request - {@link HttpRequest} is an object representation of incoming request
   * @param operationDto - requisites object that was sent by client's side and deserialized from
   *                     JSON
   * @return - HTTP response that will be sent to client's side
   */
  @Mapping(value = "/transfer", httpMethod = HttpMethod.POST)
  public Response transfer(final HttpRequest request, final OperationDto operationDto) {
    final OperationRequisites requisites = operationDto.extractOperationRequisites();
    try {
      synchronized (accSynchronizer.getLock(requisites.getAccountFrom().getNumber())) {
        accountService.transfer(requisites);
        return new HttpResponse(HttpResponseCode.OK); //NOPMD - suppressed OnlyOneReturn - errors
        // turned to objects on this level
      }
    } catch (Exception e) { //NOPMD - suppressed AvoidCatchingGenericException - top level, correct
      LOG.error(StackTraceString.get(e)); //NOPMD - suppressed GuardLogStatement - no concatenation
      // - no need in check
      return prepareResponseOnException(e);
    }
  }

  /**
   * This method is responsible for handling /account/view mapping with HTTP GET method. So it
   * get parametrized request from client's side and should return appropriate HTTP response
   * after further handling. If handling fails for some reason, response with code 404 or 500 and
   * error text in it will be sent.
   *
   * @param request - {@link HttpRequest} is an object representation of incoming request
   * @return - HTTP response that will be sent to client's side
   */
  @Mapping(value = "/view", httpMethod = HttpMethod.GET)
  public Response view(final HttpRequest request) {
    final Optional<String> accNumString = request.getParameterByName("number");
    try {
      if (accNumString.isPresent()) {
        synchronized (accSynchronizer.getLock(Integer.parseInt(accNumString.get()))) {
          final Account account =
              accountService.findByNumber(Integer.parseInt(accNumString.get()));
          final AccountDto responseAccDto = prepareAccountDto(account);
          return new HttpResponse(HttpResponseCode.OK, responseAccDto); //NOPMD - suppressed
          // OnlyOneReturn - errors turned to objects on this level
        }
      } else {
        throw new NoSuchElementException("Request format for account incorrect");
      }
    } catch (Exception e) { //NOPMD - suppressed AvoidCatchingGenericException - top level, correct
      LOG.error(StackTraceString.get(e)); //NOPMD - suppressed GuardLogStatement - no concatenation
      // - no need in check
      return prepareResponseOnException(e);
    }
  }

  private HttpResponse prepareResponseOnException(final Throwable throwable) {
    final Throwable cause = throwable.getCause();
    if (cause instanceof IllegalArgumentException) {
      final ErrorDto errorDto = new ErrorDto(404, throwable.getMessage());
      return new HttpResponse(HttpResponseCode.NOTFOUND, errorDto); //NOPMD - suppressed
      // OnlyOneReturn - errors turned to objects on this level
    } else {
      final ErrorDto errorDto = new ErrorDto(500, throwable.getMessage());
      return new HttpResponse(HttpResponseCode.INTERNALSERVERERROR, errorDto);
    }
  }

  private AccountDto prepareAccountDto(final Account account) {
    final List<Operation> operations = account.getHistory();
    final List<OperationDto> operationsDto = new ArrayList<>();
    final ClientDto clientDto = new ClientDto(account.getOwner());
    for (final Operation operation : operations) {
      operationsDto.add(new OperationDto(operation, clientDto));
    }
    operationsDto.sort(Comparator.comparing(OperationDto::getHistoryNumber).reversed());
    return new AccountDto(account, operationsDto, clientDto);
  }
}
