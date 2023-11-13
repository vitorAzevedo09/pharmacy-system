package com.pharmacy.system.store.api.assembler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.pharmacy.system.store.api.dto.CustomerWithIdDTO;
import com.pharmacy.system.store.domain.model.Customer;
import com.pharmacy.system.store.domain.service.CustomerService;

/**
 * CustomerAssembler
 */
@Component
public class CustomerAssembler {

  @Autowired
  private CustomerService customerService;

  public Customer toEntity(CustomerWithIdDTO dto) {
    return customerService.findOrFail(dto.ID());
  }

}
