package com.goryaninaa.web.bank.education.winter.repository.number.capacity;

import com.goryaninaa.web.bank.domain.service.account.NumberCapacityRepository;

/**
 * This is simple {@link NumberCapacityRepository} implementation. The point of this class is to
 * provide new account numbers on request.
 */
public class NumberCapacityRepositoryPojo implements NumberCapacityRepository {

  private final NumberCapacity numberCapacity;

  public NumberCapacityRepositoryPojo(final NumberCapacity numberCapacity) {
    this.numberCapacity = numberCapacity;
  }

  @Override
  public int getNumber() {
    numberCapacity.requestNewNumber(1);
    return numberCapacity.retrieveNewNumber(1);
  }

}
