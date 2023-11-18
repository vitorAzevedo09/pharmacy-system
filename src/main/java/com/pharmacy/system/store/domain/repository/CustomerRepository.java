package com.pharmacy.system.store.domain.repository;

import java.util.Optional;

import org.springframework.stereotype.Repository;

import com.pharmacy.system.store.domain.model.Customer;

/**
 * CustomerRepository
 */
@Repository
public interface CustomerRepository extends UserRepository {

  public Optional<Customer> findCustomerById(final Long id);

}
