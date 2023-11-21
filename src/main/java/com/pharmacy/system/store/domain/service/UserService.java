package com.pharmacy.system.store.domain.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
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
public class UserService implements UserDetailsService {

  @Autowired
  private UserRepository userRepository;

  @Autowired
  private RoleRepository roleRepository;

  public User getOne(final Long userId) {
    return userRepository.findById(userId)
        .orElseThrow(() -> new UserNotFoundException(userId));
  }

  public User save(User user) {
    if (userRepository.existsByUsername(user.getUsername()))
      throw new UserConflictException(
          String.format("User with username %s already exists", user.getUsername()));

    if (userRepository.existsByEmail(user.getEmail()))
      throw new UserConflictException(
          String.format("User with email %s already exists", user.getEmail()));
    return userRepository.save(user);
  }

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
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
