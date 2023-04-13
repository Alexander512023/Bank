package com.goryaninaa.web.bank.model.account;

import com.goryaninaa.web.bank.model.operation.OperationRequisites;

/**
 * This is supporting class, which instances should be used as requisites in open account
 * scenarios.
 */
@SuppressWarnings("unused")
public class AccountOpenRequisites { //NOPMD - suppressed DataClass - supporting class
  private OperationRequisites operRequisites;
  private AccountType accountType;
  private int term;

  public AccountOpenRequisites() {
    // Default constructor
  }

  /**
   * Constructor which should be used to instantiate enriched objects of this class.
   *
   * @param operRequisites - requisites of account open operation
   * @param accountType - type of account, which should be instantiated
   * @param term - variable which refers to term, for which this account should be kept
   */
  public AccountOpenRequisites(final OperationRequisites operRequisites,
                               final AccountType accountType, final int term) {
    super();
    this.operRequisites = operRequisites;
    this.accountType = accountType;
    this.term = term;
  }

  public OperationRequisites getOperRequisites() {
    return operRequisites;
  }

  public void setOperRequisites(final OperationRequisites operRequisites) {
    this.operRequisites = operRequisites;
  }

  public AccountType getAccountType() {
    return accountType;
  }

  public void setAccountType(final AccountType accountType) {
    this.accountType = accountType;
  }

  public int getTerm() {
    return term;
  }

  public void setTerm(final int term) {
    this.term = term;
  }

}
