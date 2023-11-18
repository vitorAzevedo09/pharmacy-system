package com.pharmacy.system.store.domain.model;

import java.util.Collection;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;

/**
 * Privilege
 */
@Entity(name = "privilege")
public class Privilege {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private String name;

  @ManyToMany(mappedBy = "privileges")
  private Collection<Role> roles;

  public Long getId() {
    return id;
  }

  public String getName() {
    return name;
  }

  public Collection<Role> getRoles() {
    return roles;
  }

}
