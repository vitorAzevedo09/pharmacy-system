package com.pharmacy.system.store.domain.model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Builder.Default;

/**
 * User
 */
@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "users")
@EqualsAndHashCode(of = "id")
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

  @Default
  @Column(name = "enabled")
  private boolean enabled = true;

  @Default
  @Column(name = "token_expired")
  private boolean tokenExpired = false;

  @ManyToMany(fetch = FetchType.EAGER)
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

}
