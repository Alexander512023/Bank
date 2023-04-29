package com.goryaninaa.web.bank.domain.model.client;

import com.goryaninaa.web.bank.domain.model.account.Account;
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
  private String secondName;
  private String dateOfBirth;
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
   * @param secondName - client's second name
   * @param dateOfBirth - client's date of birth
   */
  public Client(final int clientId, final String passport, final String firstName,
                final String secondName, final String dateOfBirth) {
    super();
    this.clientId = clientId;
    this.passport = passport;
    this.firstName = firstName;
    this.secondName = secondName;
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

  public String getSecondName() {
    return secondName;
  }

  public void setSecondName(final String secondName) {
    this.secondName = secondName;
  }

  public List<Account> getProducts() {
    return products;
  }

  public void setProducts(final List<Account> products) {
    this.products = products;
  }

  public String getDateOfBirth() {
    return dateOfBirth;
  }

  public void setDateOfBirth(final String dateOfBirth) {
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

}
