package com.pharmacy.system.store.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Repository;

import com.pharmacy.system.store.domain.model.User;

/**
 * UserRepository
 */
@Repository
public interface UserRepository extends JpaRepository<User, Long> {

  UserDetails findByUsername(String username);

}
