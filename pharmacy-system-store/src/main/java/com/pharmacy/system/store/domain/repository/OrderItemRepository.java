package com.pharmacy.system.store.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.pharmacy.system.store.domain.model.OrderItem;

/**
 * OrderItemRepository
 */
@Repository
public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {

}
