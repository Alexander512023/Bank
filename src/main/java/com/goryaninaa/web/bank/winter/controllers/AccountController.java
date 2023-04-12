package com.goryaninaa.web.bank.winter.controllers;

import com.goryaninaa.web.bank.dto.AccountDTO;
import com.goryaninaa.web.bank.dto.AccountOpenRequisitesDTO;
import com.goryaninaa.web.bank.dto.ClientDTO;
import com.goryaninaa.web.bank.dto.ErrorDTO;
import com.goryaninaa.web.bank.dto.OperationDTO;
import com.goryaninaa.web.bank.model.account.Account;
import com.goryaninaa.web.bank.model.account.AccountOpenRequisites;
import com.goryaninaa.web.bank.model.operation.Operation;
import com.goryaninaa.web.bank.model.operation.OperationRequisites;
import com.goryaninaa.web.bank.service.account.AccountService;
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

@SuppressWarnings("unused")
@RequestMapping("/account")
public class AccountController implements Controller {

	private final AccountService accountService;
	private final Synchronizer<Integer> accountSynchronizer;
	
	public AccountController(AccountService accountService, Synchronizer<Integer> accountSynchronizer) {
		this.accountService = accountService;
		this.accountSynchronizer = accountSynchronizer;
	}
	
	@Mapping(value = "/open", httpMethod = HttpMethod.POST)
	public Response open(HttpRequest request, AccountOpenRequisitesDTO requisitesDTO) {
		AccountOpenRequisites accountRequisites = requisitesDTO.extractAccountRequisites();
		try {
			accountService.open(accountRequisites);
			return new HttpResponse(HttpResponseCode.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return prepareResponseOnException(e);
		}
	}

	@Mapping(value = "/deposit", httpMethod = HttpMethod.POST)
	public Response deposit(HttpRequest request, OperationDTO operationDTO) {
		OperationRequisites requisites = operationDTO.extractOperationRequisites();
		try {
			synchronized (accountSynchronizer.getLock(requisites.getAccountRecipient().getNumber())) {
				accountService.deposit(requisites);
				return new HttpResponse(HttpResponseCode.OK);
			}
		} catch (Exception e) {
			e.printStackTrace();
			return prepareResponseOnException(e);
		}
	}
	
	@Mapping(value = "/withdraw", httpMethod = HttpMethod.POST)
	public Response withdraw(HttpRequest request, OperationDTO operationDTO) {
		OperationRequisites requisites = operationDTO.extractOperationRequisites();
		try {
			synchronized (accountSynchronizer.getLock(requisites.getAccountFrom().getNumber())) {
				accountService.withdraw(requisites);
				return new HttpResponse(HttpResponseCode.OK);
			}
		} catch (Exception e) {
			e.printStackTrace();
			return prepareResponseOnException(e);
		}
	}
	
	@Mapping(value = "/transfer", httpMethod = HttpMethod.POST)
	public Response transfer(HttpRequest request, OperationDTO operationDTO) {
		OperationRequisites requisites = operationDTO.extractOperationRequisites();
		try {
			synchronized (accountSynchronizer.getLock(requisites.getAccountFrom().getNumber())) {
				accountService.transfer(requisites);
				return new HttpResponse(HttpResponseCode.OK);
			}
		} catch (Exception e) {
			e.printStackTrace();
			return prepareResponseOnException(e);
		}
	}
	
	@Mapping(value = "/view", httpMethod = HttpMethod.GET)
	public Response view(HttpRequest request) {
		Optional<String> accountNumberString = request.getParameterByName("number");
		try {
			if (accountNumberString.isPresent()) {
				synchronized (accountSynchronizer.getLock(Integer.parseInt(accountNumberString.get()))) {
					Account account =
							accountService.findByNumber(Integer.parseInt(accountNumberString.get()));
					AccountDTO responseAccountDTO = prepareAccountDTO(account);
					return new HttpResponse(HttpResponseCode.OK, responseAccountDTO);
				}
			} else {
				throw new NoSuchElementException("Request format for account incorrect");
			}
		} catch (Exception e) {
			e.printStackTrace();
			return prepareResponseOnException(e);
		}
	}
	
	private HttpResponse prepareResponseOnException(Throwable t) {
		Throwable cause = t.getCause();
		if (cause instanceof IllegalArgumentException) {
			ErrorDTO errorDTO = new ErrorDTO(404, t.getMessage());
			return new HttpResponse(HttpResponseCode.NOTFOUND, errorDTO);
		} else {
			ErrorDTO errorDTO = new ErrorDTO(500, t.getMessage());
			return new HttpResponse(HttpResponseCode.INTERNALSERVERERROR, errorDTO);
		}
	}
	
	private AccountDTO prepareAccountDTO(Account account) {
		List<Operation> operations = account.getHistory();
		List<OperationDTO> operationsDTO = new ArrayList<>();
		ClientDTO clientDTO = new ClientDTO(account.getOwner());
		for (Operation operation : operations) {
			operationsDTO.add(new OperationDTO(operation, clientDTO));
		}
		operationsDTO.sort(Comparator.comparing(OperationDTO::getHistoryNumber).reversed());
		return new AccountDTO(account, operationsDTO, clientDTO);
	}
}
