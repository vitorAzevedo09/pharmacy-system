package com.pharmacy.system.store.domain.model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;

/**
 * User
 */
@Entity(name = "users")
public class User implements UserDetails {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id")
  private Long id;

  @Column(name = "first_name")
  private String firstName;

  @Column(name = "last_name")
  private String lastName;

  @Column(name = "username")
  private String username;

  @Column(name = "email")
  private String email;

  @Column(name = "password")
  private String password;

  @Column(name = "enabled")
  private boolean enabled;

  @Column(name = "token_expired")
  private boolean tokenExpired;

  @ManyToMany
  // @formatter:off
  @JoinTable(
    name = "users_roles",
    joinColumns = @JoinColumn(
      name = "user_id",
      referencedColumnName = "id"),
    inverseJoinColumns = @JoinColumn(
      name = "role_id",
      referencedColumnName = "id"))
  // @formatter:on
  private List<Role> roles;

  private List<String> getPrivileges() {
    List<String> privileges = new ArrayList<>();
    for (Role role : this.roles) {
      for (Privilege privilege : role.getPrivileges()) {
        privileges.add(privilege.getName());
      }
    }
    return privileges;
  }

  private List<SimpleGrantedAuthority> getGrantedAuthorities(List<String> privileges) {
    return privileges.stream().map(privilege -> new SimpleGrantedAuthority(privilege)).toList();
  }

  @Override
  public Collection<? extends GrantedAuthority> getAuthorities() {
    return getGrantedAuthorities(getPrivileges());
  }

  @Override
  public boolean isAccountNonLocked() {
    // TODO Auto-generated method stub
    return true;
  }

  @Override
  public boolean isAccountNonExpired() {
    // TODO Auto-generated method stub
    return true;
  }

  @Override
  public boolean isCredentialsNonExpired() {
    // TODO Auto-generated method stub
    return true;
  }

  public String getUsername() {
    return username;
  }

  public Long getId() {
    return id;
  }

  public String getFirstName() {
    return firstName;
  }

  public String getLastName() {
    return lastName;
  }

  public String getEmail() {
    return email;
  }

  public String getPassword() {
    return password;
  }

  public boolean isEnabled() {
    return enabled;
  }

  public boolean isTokenExpired() {
    return tokenExpired;
  }

  public Collection<Role> getRoles() {
    return roles;
  }

}
