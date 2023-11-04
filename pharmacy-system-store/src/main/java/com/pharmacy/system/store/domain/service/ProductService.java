package com.pharmacy.system.store.domain.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.pharmacy.system.store.domain.model.Product;
import com.pharmacy.system.store.domain.repository.ProductRepository;

/**
 * ProductService
 */
@Service
public class ProductService {

  @Autowired
  private ProductRepository productRepository;

  public Page<Product> findAll(Pageable pageable) {
    return productRepository.findAll(pageable);
  }

}
