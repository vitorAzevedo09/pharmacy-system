package com.pharmacy.system.store.domain.model;

import jakarta.persistence.Column;
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
  @Column(name = "id")
  private Long id;
  @Column(name = "first_name")
  private String firstName;
  @Column(name = "last_name")
  private String lastName;
  @Column(name = "email")
  private String email;

  public Customer(String username, String password, String firstName, String lastName, String email) {
    super(username, password);
    this.firstName = firstName;
    this.lastName = lastName;
    this.email = email;
  }

  public Customer() {
    super();
  }

  public Long getId() {
    return id;
  }

  public void setId(Long iD) {
    id = iD;
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
