package com.pharmacy.system.store.domain.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;

/**
 * Customer
 */
@Entity(name = "customers")
public class Customer extends User {

  @Column(name = "company", nullable = true)
  private String company;

  public Customer() {
    super();
  }

  public String getCompany() {
    return company;
  }

  public void setCompany(String company) {
    this.company = company;
  }

}
