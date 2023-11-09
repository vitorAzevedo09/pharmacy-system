package com.pharmacy.system.store.domain.exception;

/**
 * ProductNotFoundException
 */
public class ProductNotFoundException extends ResourceNotFoundException {

  public ProductNotFoundException(String message) {
    super(message);
  }

  public ProductNotFoundException(Long id) {
    super(String.format("Product with id %d not found in system", id));
  }

}
