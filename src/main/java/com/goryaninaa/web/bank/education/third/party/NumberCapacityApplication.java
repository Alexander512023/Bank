package com.goryaninaa.web.bank.education.third.party;

import com.goryaninaa.web.bank.education.winter.repository.number.capacity.NumberCapacity;
import java.util.Map;
import java.util.Objects;
import java.util.Queue;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * This class is a dummy of third party application, that's kinda connects to Bank application via
 * in and out queues in synchronous manner. Business point of this piece of code is to provide new
 * accounts numbers when it's needed.
 */
public class NumberCapacityApplication implements NumberCapacity {

  private final Queue<Integer> outQueue = new ConcurrentLinkedQueue<>();
  private final Queue<Map<Integer, Integer>> inQueue = new ConcurrentLinkedQueue<>();
  private final Map<Integer, Integer> numStorage = new ConcurrentHashMap<>();
  private static final AtomicInteger NUMBER_COUNTER = new AtomicInteger(1001);

  public NumberCapacityApplication() {
    //Default constructor
  }

  @Override
  public void requestNewNumber(final int sessionId) {
    outQueue.add(sessionId);
    thirdPartyConjure();
  }

  @Override
  public int retrieveNewNumber(final int sessionId) {
    int num;
    while (true) {
      if (numStorage.containsKey(sessionId)) {
        num = numStorage.get(sessionId);
        break;
      }
    }
    return num;
  }

  private void thirdPartyConjure() {
    inQueue.add(Map.of(Objects.requireNonNull(outQueue.poll()), NUMBER_COUNTER.getAndIncrement()));
    Map<Integer, Integer> response = null;
    while (!inQueue.isEmpty()) {
      response = inQueue.poll();
    }
    try {
      Thread.sleep(3000);
    } catch (InterruptedException e) {
      Thread.currentThread().interrupt();
    }
    assert response != null;
    numStorage.put(
        response.keySet().stream().findFirst().orElseThrow(),
        response.values().stream().findFirst().orElseThrow());
  }
}
