package com.goryaninaa.web.bank.model.client;

import static org.junit.jupiter.api.Assertions.assertTrue;

import com.goryaninaa.web.bank.domain.model.client.Client;
import org.junit.jupiter.api.Test;

class ClientTest {

  @Test
  void hashCodeShouldWorkCorrectly() {
    Client client1 = new Client();
    Client client2 = new Client();
    client1.setPassport("1234 123456");
    client2.setPassport("1234 123456");
    boolean hashCodesEqual = client1.hashCode() == client2.hashCode();
    client2.setPassport("1");
    boolean hashCodesNotEqual = !(client1.hashCode() == client2.hashCode());
    boolean testPassed = hashCodesEqual && hashCodesNotEqual;
    assertTrue(testPassed);
  }

  @Test
  void equals() {
    Client client1 = new Client();
    Client client2 = new Client();
    client1.setPassport("1234 123456");
    client2.setPassport("1234 123456");
    boolean clientsAreEqual = client1.equals(client2);
    client2.setPassport("1");
    boolean clientsAreNotEqual = !client1.equals(client2);
    boolean testPassed = clientsAreEqual && clientsAreNotEqual;
    assertTrue(testPassed);
  }
}