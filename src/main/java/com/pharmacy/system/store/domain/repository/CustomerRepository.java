package com.pharmacy.system.store.domain.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.pharmacy.system.store.domain.model.Customer;

/**
 * CustomerRepository
 */
@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {

  Optional<Customer> findById(final Long id);
}
