package com.pharmacy.system.store.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.pharmacy.system.store.api.assembler.ProductAssembler;
import com.pharmacy.system.store.api.dto.ProductCreateDTO;
import com.pharmacy.system.store.api.dto.ProductOutputDTO;
import com.pharmacy.system.store.domain.model.Product;
import com.pharmacy.system.store.domain.service.ProductService;

/**
 * ProductController
 */
@RestController
@RequestMapping("/products")
public class ProductController {

  @Autowired
  private ProductService productService;

  @Autowired
  private ProductAssembler productAssembler;

  @GetMapping
  @ResponseStatus(code = HttpStatus.OK)
  public Page<ProductOutputDTO> getAll(Pageable pageable) {
    return productService.getAll(pageable).map(p -> productAssembler.toOutput(p));
  }

  @GetMapping("/{productId}")
  @ResponseStatus(code = HttpStatus.OK)
  public ResponseEntity<ProductOutputDTO> getOne(@PathVariable final Long productId) {
    Product product = productService.findOrFail(productId);
    ProductOutputDTO productOut = productAssembler.toOutput(product);
    return ResponseEntity.ok(productOut);
  }

  @PostMapping
  @ResponseStatus(code = HttpStatus.OK)
  public void create(@RequestBody ProductCreateDTO productInput) {
    Product product = productAssembler.toEntity(productInput);
    productService.create(product);
  }

}
