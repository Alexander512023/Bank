package com.goryaninaa.web.bank.education.winter.repository.number.capacity;

/**
 * Interface of entity that provides numbers for new opening accounts.
 */
public interface NumberCapacity {
  void requestNewNumber(int sessionId);

  int retrieveNewNumber(int sessionId);
}
