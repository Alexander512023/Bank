package com.goryaninaa.web.bank.dto;

import com.goryaninaa.web.bank.domain.model.account.Account;
import com.goryaninaa.web.bank.domain.model.client.Client;
import java.time.LocalDate;
import java.util.List;

/**
 * AccountDto is data transfer object for Account entity.
 *
 */
@SuppressWarnings("unused")
public class ClientDto { //NOPMD - suppressed DataClass - data transfer object

  private String passport;
  private String firstName;
  private String lastName;
  private LocalDate dateOfBirth;
  private List<Account> products;

  public ClientDto() {
    // Default constructor
  }

  /**
   * Use this constructor to create ClientDTO object from Client object.
   *
   * @param client - client object from which corresponding DTO object should be created
   */
  public ClientDto(final Client client) {
    this.passport = client.getPassport();
    this.firstName = client.getFirstName();
    this.lastName = client.getLastName();
    this.dateOfBirth = client.getDateOfBirth();
    this.products = client.getProducts();
  }

  public String getPassport() {
    return passport;
  }

  public void setPassport(final String passport) {
    this.passport = passport;
  }

  public String getFirstName() {
    return firstName;
  }

  public void setFirstName(final String firstName) {
    this.firstName = firstName;
  }

  public String getLastName() {
    return lastName;
  }

  public void setLastName(final String lastName) {
    this.lastName = lastName;
  }

  public LocalDate getDateOfBirth() {
    return dateOfBirth;
  }

  public void setDateOfBirth(final LocalDate dateOfBirth) {
    this.dateOfBirth = dateOfBirth;
  }

  public List<Account> getProducts() {
    return products;
  }

  public void setProducts(final List<Account> products) {
    this.products = products;
  }

}
