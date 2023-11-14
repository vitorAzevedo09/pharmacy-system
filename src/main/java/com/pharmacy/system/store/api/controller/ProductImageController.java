package com.pharmacy.system.store.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.pharmacy.system.store.api.assembler.ImageAssembler;
import com.pharmacy.system.store.api.dto.ImageDTO;
import com.pharmacy.system.store.api.dto.ImageWithIdDTO;
import com.pharmacy.system.store.domain.model.Image;
import com.pharmacy.system.store.domain.service.ImageService;
import com.pharmacy.system.store.domain.service.ProductService;

/**
 * ProductImageController
 */
@RestController
@RequestMapping("/products/{productId}/images")
public class ProductImageController {

  @Autowired
  private ProductService productService;

  @Autowired
  private ImageService imageService;

  @Autowired
  private ImageAssembler imageAssembler;

  @GetMapping()
  public Page<ImageWithIdDTO> getAll(Pageable pageable) {
    return imageService.findAll(pageable)
        .map(i -> imageAssembler.toOutput(i));
  }

  @PostMapping
  public ResponseEntity<ImageWithIdDTO> uploadProductImage(
      @PathVariable final Long productId,
      ImageDTO image) {
    try {
      Image img = productService.uploadProductImage(
          productId,
          image.file(),
          image.description());
      return ResponseEntity.ok(new ImageWithIdDTO(img.getId(), img.getImageUrl(), img.getDescription()));
    } catch (Exception ex) {
      throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, ex.getMessage(), ex);
    }
  }

}
