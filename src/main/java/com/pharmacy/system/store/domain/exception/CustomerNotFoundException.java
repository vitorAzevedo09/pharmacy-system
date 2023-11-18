package com.pharmacy.system.store.domain.exception;

/**
 * CustomerNotFoundException
 */
public class CustomerNotFoundException extends ResourceNotFoundException {

  public CustomerNotFoundException(String message) {
    super(message);
  }

  public CustomerNotFoundException(final Long id) {
    super(String.format("Customer with id %d not found", id));
  }

}
