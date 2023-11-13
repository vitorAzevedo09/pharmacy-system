package com.pharmacy.system.store.domain.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.pharmacy.system.store.domain.exception.ProductNotFoundException;
import com.pharmacy.system.store.domain.model.Product;
import com.pharmacy.system.store.domain.repository.ProductRepository;

import jakarta.transaction.Transactional;

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

  public Product findOrFail(final Long productID) {
    return productRepository.findById(productID)
        .orElseThrow(() -> new ProductNotFoundException(productID));
  }

  @Transactional
  public Product create(final Product product) {
    return productRepository.save(product);
  }

  @Transactional
  public Product update(final Long id, final Product newProduct) {
    if (!productRepository.existsById(id)) {
      throw new ProductNotFoundException(id);
    }
    newProduct.setId(id);
    return productRepository.save(newProduct);
  }

  @Transactional
  public Product partialUpdate(final Long id, final Product productNew) {
    Product productDB = findOrFail(id);
    productDB.copyFrom(productNew);
    return productRepository.save(productDB);
  }

  @Transactional
  public void delete(final Long productID) {
    Product entity = findOrFail(productID);
    productRepository.delete(entity);
  }

}
