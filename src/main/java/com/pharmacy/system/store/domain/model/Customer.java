package com.pharmacy.system.store.domain.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

/**
 * Customer
 */

@Entity(name = "customers")
public class Customer extends User {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long ID;
  private String firstName;
  private String lastName;
  private String email;

  public Customer(String username, String password, String firstName, String lastName, String email) {
    super(username, password);
    this.firstName = firstName;
    this.lastName = lastName;
    this.email = email;
  }

  public Long getID() {
    return ID;
  }

  public void setID(Long iD) {
    ID = iD;
  }

  public String getFirstName() {
    return firstName;
  }

  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }

  public String getLastName() {
    return lastName;
  }

  public void setLastName(String lastName) {
    this.lastName = lastName;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }
}
