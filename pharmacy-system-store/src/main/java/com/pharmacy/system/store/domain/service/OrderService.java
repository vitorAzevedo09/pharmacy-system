package com.pharmacy.system.store.domain.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.pharmacy.system.store.domain.exception.OrderItemNotFoundException;
import com.pharmacy.system.store.domain.exception.OrderNotFoundException;
import com.pharmacy.system.store.domain.model.Order;
import com.pharmacy.system.store.domain.model.OrderItem;
import com.pharmacy.system.store.domain.repository.OrderItemRepository;
import com.pharmacy.system.store.domain.repository.OrderRepository;

import jakarta.transaction.Transactional;

/**
 * OrderService
 */
@Service
public class OrderService {

  @Autowired
  private OrderRepository orderRepository;

  @Autowired
  private OrderItemRepository orderItemRepository;

  public Page<Order> findAll(Pageable pageable) {
    return orderRepository.findAll(pageable);
  }

  public Order findOrFail(final Long orderID) {
    return orderRepository.findById(orderID)
        .orElseThrow(() -> new OrderNotFoundException(orderID));
  }

  public OrderItem findOrFailItem(final Long orderItemID) {
    return orderItemRepository.findById(orderItemID)
        .orElseThrow(() -> new OrderItemNotFoundException(orderItemID));
  }

  @Transactional
  public Order create(final Order order) {
    return orderRepository.save(order);
  }

  @Transactional
  public Order update(final Long id, final Order order) {
    if (!orderRepository.existsById(id)) {
      throw new OrderNotFoundException(id);
    }
    order.setID(id);
    return orderRepository.save(order);
  }

  @Transactional
  public Order partialUpdate(final Long id, final Order order) {
    Order orderDB = findOrFail(id);
    orderDB.copyFrom(order);
    return orderRepository.save(orderDB);
  }

  @Transactional
  public void delete(final Long orderID) {
    Order entity = findOrFail(orderID);
    orderRepository.delete(entity);
  }

}
