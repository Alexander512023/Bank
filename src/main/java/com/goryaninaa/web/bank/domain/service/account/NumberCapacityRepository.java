package com.goryaninaa.web.bank.domain.service.account;

/**
 * This interface describes functionality of number capacity, which is used to get new account
 * numbers.
 */
public interface NumberCapacityRepository {

  int getNumber();

}
