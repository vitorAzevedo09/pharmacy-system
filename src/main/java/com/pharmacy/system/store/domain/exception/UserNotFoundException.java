package com.pharmacy.system.store.domain.exception;

/**
 * UserNotFoundException
 */
public class UserNotFoundException extends ResourceNotFoundException {

  public UserNotFoundException(String message) {
    super(message);
  }

  public UserNotFoundException(final Long id) {
    super(String.format("User with id % not found", id));
  }

}
