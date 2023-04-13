package com.goryaninaa.web.bank.dto;

import com.goryaninaa.web.bank.model.account.Account;
import com.goryaninaa.web.bank.model.client.Client;
import java.util.List;

@SuppressWarnings("unused")
public class ClientDto {

  private String passport;
  private String firstName;
  private String secondName;
  private String dateOfBirth;
  private List<Account> products;

  public ClientDto() {

  }

  public ClientDto(Client client) {
    this.passport = client.getPassport();
    this.firstName = client.getFirstName();
    this.secondName = client.getSecondName();
    this.dateOfBirth = client.getDateOfBirth();
    this.products = client.getProducts();
  }

  public String getPassport() {
    return passport;
  }

  public void setPassport(String passport) {
    this.passport = passport;
  }

  public String getFirstName() {
    return firstName;
  }

  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }

  public String getSecondName() {
    return secondName;
  }

  public void setSecondName(String secondName) {
    this.secondName = secondName;
  }

  public String getDateOfBirth() {
    return dateOfBirth;
  }

  public void setDateOfBirth(String dateOfBirth) {
    this.dateOfBirth = dateOfBirth;
  }

  public List<Account> getProducts() {
    return products;
  }

  public void setProducts(List<Account> products) {
    this.products = products;
  }

}
