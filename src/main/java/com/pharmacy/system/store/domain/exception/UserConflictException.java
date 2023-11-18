package com.pharmacy.system.store.domain.exception;

/**
 * UserConflictException
 */
public class UserConflictException extends ResourceConflictException {

  public UserConflictException(String field) {
    super(String.format("%s field already exist", field));
  }

}
