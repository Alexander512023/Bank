package com.goryaninaa.web.bank.education.winter.repository.number.capacity;

public interface NumberCapacity {
  void requestNewNumber(int sessionId);

  int retrieveNewNumber(int sessionId);
}
