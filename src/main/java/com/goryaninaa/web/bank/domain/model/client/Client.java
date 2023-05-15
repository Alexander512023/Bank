package com.goryaninaa.web.bank.domain.model.client;

import com.goryaninaa.web.bank.domain.model.account.Account;
import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

/**
 * Bank client entity class.
 */
@SuppressWarnings("unused")
public class Client { //NOPMD - suppressed DataClass - entity class

  private int clientId;
  private String passport;
  private String firstName;
  private String lastName;
  private LocalDate dateOfBirth;
  private List<Account> products;

  public Client() {
    // Default constructor
  }

  public Client(final String passport) {
    this.passport = passport;
  }

  /**
   * Constructor which receives values for all client fields.
   *
   * @param clientId - unique identifier
   * @param passport - client's passport requisites
   * @param firstName - client's first name
   * @param lastName - client's last name
   * @param dateOfBirth - client's date of birth
   */
  public Client(final int clientId, final String passport, final String firstName,
                final String lastName, final LocalDate dateOfBirth) {
    super();
    this.clientId = clientId;
    this.passport = passport;
    this.firstName = firstName;
    this.lastName = lastName;
    this.dateOfBirth = dateOfBirth;
  }

  public int getClientId() {
    return clientId;
  }

  public void setClientId(final int clientId) {
    this.clientId = clientId;
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

  public List<Account> getProducts() {
    return products;
  }

  public void setProducts(final List<Account> products) {
    this.products = products;
  }

  public LocalDate getDateOfBirth() {
    return dateOfBirth;
  }

  public void setDateOfBirth(final LocalDate dateOfBirth) {
    this.dateOfBirth = dateOfBirth;
  }

  @Override
  public int hashCode() {
    return Objects.hash(passport);
  }

  @Override
  public boolean equals(final Object obj) {
    boolean isEqual;
    if (this == obj) {
      isEqual = true;
    } else if (obj == null) {
      isEqual = false; //NOPMD - suppressed ConfusingTernary - not applicable for equals case
    } else if (getClass() != obj.getClass()) { //NOPMD - suppressed ConfusingTernary -
      // not applicable for equals case
      isEqual = false;
    } else {
      final Client other = (Client) obj;
      isEqual = Objects.equals(passport, other.passport);
    }
    return isEqual;
  }

  @Override
  public String toString() {
    return "Client{"
        + "clientId=" + clientId
        + ", passport='" + passport + '\''
        + ", firstName='" + firstName + '\''
        + ", lastName='" + lastName + '\''
        + ", dateOfBirth=" + dateOfBirth
        + ", products=" + products
        + '}';
  }
}
