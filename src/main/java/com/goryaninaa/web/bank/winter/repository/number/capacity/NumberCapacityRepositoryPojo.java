package com.goryaninaa.web.bank.winter.repository.number.capacity;

import com.goryaninaa.web.bank.service.account.NumberCapacityRepository;
import com.goryaninaa.web.bank.winter.dao.concurrent.stub.NumberCapacity;

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
    return numberCapacity.getNumber();
  }

}
