package com.pharmacy.system.store.domain.exception;

/**
 * OrderNotFoundException
 */
public class OrderItemNotFoundException extends ResourceNotFoundException {

  public OrderItemNotFoundException(String message) {
    super(message);
  }

  public OrderItemNotFoundException(Long id) {
    super(String.format("Item with id %d not found in system", id));
  }

}
