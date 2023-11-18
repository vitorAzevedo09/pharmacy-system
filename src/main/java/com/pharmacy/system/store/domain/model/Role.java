package com.pharmacy.system.store.domain.model;

import java.util.Collection;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;

@Entity(name = "roles")
public class Role {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private String name;
  @ManyToMany(mappedBy = "roles")
  private Collection<User> users;

  @ManyToMany
  // @formatter:off
  @JoinTable(
      name = "roles_privileges", 
      joinColumns = @JoinColumn(
        name = "role_id", referencedColumnName = "id"), 
      inverseJoinColumns = @JoinColumn(
        name = "privilege_id", referencedColumnName = "id"))
  // @formatter:on
  private Collection<Privilege> privileges;

  public Long getId() {
    return id;
  }

  public String getName() {
    return name;
  }

  public Collection<User> getUsers() {
    return users;
  }

  public Collection<Privilege> getPrivileges() {
    return privileges;
  }

}
