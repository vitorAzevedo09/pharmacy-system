package com.pharmacy.system.store.domain.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import com.pharmacy.system.store.domain.exception.ResourceNotFoundException;
import com.pharmacy.system.store.domain.exception.UserConflictException;
import com.pharmacy.system.store.domain.exception.UserNotFoundException;
import com.pharmacy.system.store.domain.model.Role;
import com.pharmacy.system.store.domain.model.User;
import com.pharmacy.system.store.domain.repository.RoleRepository;
import com.pharmacy.system.store.domain.repository.UserRepository;

/**
 * UserService
 */
@Service
public class UserService {

  @Autowired
  private UserRepository userRepository;

  @Autowired
  private RoleRepository roleRepository;

  public User getOne(final Long userId) {
    return userRepository.findById(userId)
        .orElseThrow(() -> new UserNotFoundException(userId));
  }

  public boolean existUsernameOrEmail(User user) {
    if (userRepository.findByUsername(user.getUsername()) != null
        || !userRepository.findByEmail(user.getUsername()).isPresent()) {
      return true;
    }
    return false;
  }

  public User save(User user) {
    if (existUsernameOrEmail(user)) {
      throw new UserConflictException("Username or Email");
    }
    return userRepository.save(user);
  }

  public UserDetails findByUsername(final String username) {
    return userRepository.findByUsername(username);
  }

  public Role getOneRole(final Long id) {
    return roleRepository.findById(id)
        .orElseThrow(() -> new ResourceNotFoundException(
            String.format("Role with id %d not exist", id)));
  }

  public List<Role> getAllRoles() {
    return roleRepository.findAll();
  }

  public List<Role> getAllUserRoles(final Long userId) {
    User user = getOne(userId);
    return user.getRoles();
  }

  public List<Role> getAllFromIdIn(final List<Long> userIds) {
    return roleRepository.findByIdIn(userIds);
  }

}
