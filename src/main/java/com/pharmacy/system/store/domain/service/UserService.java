package com.pharmacy.system.store.domain.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pharmacy.system.store.domain.exception.UserNotFoundException;
import com.pharmacy.system.store.domain.model.User;
import com.pharmacy.system.store.domain.repository.UserRepository;

/**
 * UserService
 */
@Service
public class UserService {

  @Autowired
  private UserRepository userRepository;

  public User getOne(final Long userId) {
    return userRepository.findById(userId)
        .orElseThrow(() -> new UserNotFoundException(userId));
  }

}
