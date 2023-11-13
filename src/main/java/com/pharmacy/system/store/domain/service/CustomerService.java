package com.pharmacy.system.store.domain.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pharmacy.system.store.domain.exception.CustomerNotFound;
import com.pharmacy.system.store.domain.model.Customer;
import com.pharmacy.system.store.domain.repository.CustomerRepository;

/**
 * CustomerService
 */
@Service
public class CustomerService {

  @Autowired
  private CustomerRepository customerRepository;

  public Customer findOrFail(final Long id) {
    return customerRepository.findById(id)
        .orElseThrow(() -> new CustomerNotFound(id));
  }

}
