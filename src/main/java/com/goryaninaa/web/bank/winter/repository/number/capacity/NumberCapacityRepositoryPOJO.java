package com.goryaninaa.web.bank.winter.repository.number.capacity;

import com.goryaninaa.web.bank.service.account.NumberCapacityRepository;
import com.goryaninaa.web.bank.winter.dao.concurrent.stub.NumberCapacity;

public class NumberCapacityRepositoryPOJO implements NumberCapacityRepository {

  private final NumberCapacity numberCapacity;

  public NumberCapacityRepositoryPOJO(NumberCapacity numberCapacity) {
    this.numberCapacity = numberCapacity;
  }

  @Override
  public int getNumber() {
    return numberCapacity.getNumber();
  }

}
