package com.pharmacy.system.store.domain.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Repository;

import com.pharmacy.system.store.domain.model.User;

/**
 * UserRepository
 */
@Repository
public interface UserRepository extends JpaRepository<User, Long> {

  public UserDetails findByUsername(String username);

  public boolean existsByUsername(String username);

  public boolean existsByEmail(String username);

  public Optional<User> findByEmail(String email);

}
