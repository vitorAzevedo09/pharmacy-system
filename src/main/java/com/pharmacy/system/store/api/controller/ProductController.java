package com.pharmacy.system.store.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.pharmacy.system.store.api.assembler.ProductAssembler;
import com.pharmacy.system.store.api.dto.ProductDTO;
import com.pharmacy.system.store.api.dto.ProductWithIdDTO;
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
  public Page<ProductWithIdDTO> getAll(Pageable pageable) {
    return productService.findAll(pageable).map(p -> productAssembler.toOutput(p));
  }

  @GetMapping("/{productId}")
  public ResponseEntity<ProductWithIdDTO> getOne(@PathVariable final Long productId) {
    Product product = productService.findOrFail(productId);
    ProductWithIdDTO productOutputDTO = productAssembler.toOutput(product);
    return ResponseEntity.ok(productOutputDTO);
  }

  @PostMapping
  public ResponseEntity<ProductWithIdDTO> create(@RequestBody ProductDTO productInput) {
    Product product = productAssembler.toEntity(productInput);
    product = productService.create(product);
    ProductWithIdDTO productOutput = productAssembler.toOutput(product);
    return new ResponseEntity<ProductWithIdDTO>(productOutput, HttpStatus.CREATED);
  }

  @PutMapping("/{productId}")
  public ResponseEntity<ProductWithIdDTO> completeUpdate(
      @PathVariable final Long productId,
      @RequestBody ProductDTO productInput) {
    Product product = productAssembler.toEntity(productInput);
    product = productService.update(productId, product);
    ProductWithIdDTO productOutputDTO = productAssembler.toOutput(product);
    return ResponseEntity.ok(productOutputDTO);
  }

  @PatchMapping("/{productID}")
  public ResponseEntity<ProductWithIdDTO> partialUpdate(
      @PathVariable final Long productID,
      @RequestBody ProductDTO productDTO) {
    Product product = productAssembler.toEntity(productDTO);
    product = productService.partialUpdate(productID, product);
    return new ResponseEntity<ProductWithIdDTO>(productAssembler.toOutput(product), HttpStatus.OK);
  }

  @DeleteMapping("/{productId}")
  public ResponseEntity<?> delete(@PathVariable final Long productId) {
    productService.delete(productId);
    return ResponseEntity.noContent().build();
  }

}
