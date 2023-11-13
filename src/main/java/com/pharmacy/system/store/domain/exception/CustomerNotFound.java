package com.pharmacy.system.store.domain.exception;

/**
 * CustomerNotFound
 */
public class CustomerNotFound extends ResourceNotFoundException {

  public CustomerNotFound(String message) {
    super(message);
  }

  public CustomerNotFound(Long id) {
    super(String.format("Customer with id %d not found", id));
  }

}
