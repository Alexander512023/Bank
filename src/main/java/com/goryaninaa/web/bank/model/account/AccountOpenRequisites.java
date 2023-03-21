package com.goryaninaa.web.bank.model.account;

import com.goryaninaa.web.bank.model.operation.OperationRequisites;

public class AccountOpenRequisites {
	private OperationRequisites operationRequisites;
	private AccountType accountType;
	private int term;
	
	public AccountOpenRequisites() {
	}

	public AccountOpenRequisites(OperationRequisites operationRequisites, AccountType accountType, int term) {
		super();
		this.operationRequisites = operationRequisites;
		this.accountType = accountType;
		this.term = term;
	}

	public OperationRequisites getOperationRequisites() {
		return operationRequisites;
	}

	public void setOperationRequisites(OperationRequisites operationRequisites) {
		this.operationRequisites = operationRequisites;
	}

	public AccountType getAccountType() {
		return accountType;
	}

	public void setAccountType(AccountType accountType) {
		this.accountType = accountType;
	}

	public int getTerm() {
		return term;
	}

	public void setTerm(int term) {
		this.term = term;
	}
	
}
