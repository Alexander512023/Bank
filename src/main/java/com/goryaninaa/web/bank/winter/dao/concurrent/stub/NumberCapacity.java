package com.goryaninaa.web.bank.winter.dao.concurrent.stub;

import java.util.concurrent.atomic.AtomicInteger;

public class NumberCapacity {
  private static final AtomicInteger numberCounter = new AtomicInteger(1001);

  public NumberCapacity() {
    //Default constructor
  }

  public int getNumber() {
    return numberCounter.getAndIncrement();
  }
}
