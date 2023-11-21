package com.pharmacy.system.store.domain.exception;

/**
 * UserConflictException
 */
public class UserConflictException extends ResourceConflictException {

  public UserConflictException(String message) {
    super(message);
  }

}
