package com.pharmacy.system.store.domain.exception;

import com.pharmacy.system.store.domain.model.enumerate.OrderStatus;

/**
 * OrderNotFoundException
 */
public class OrderFlowException extends ResourceNotFoundException {

  public OrderFlowException(String message) {
    super(message);
  }

  public OrderFlowException(OrderStatus currentStatus, OrderStatus nextStatus) {
    super(String.format("Cannot go to %s status from % status", currentStatus, nextStatus));
  }

}
