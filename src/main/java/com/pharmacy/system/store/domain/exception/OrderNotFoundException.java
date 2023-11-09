package com.pharmacy.system.store.domain.exception;

/**
 * OrderNotFoundException
 */
public class OrderNotFoundException extends ResourceNotFoundException {

  public OrderNotFoundException(String message) {
    super(message);
  }

  public OrderNotFoundException(Long id) {
    super(String.format("Product with id %d not found in system", id));
  }

}
