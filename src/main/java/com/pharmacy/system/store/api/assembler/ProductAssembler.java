package com.pharmacy.system.store.api.assembler;

import org.springframework.stereotype.Component;

import com.pharmacy.system.store.api.dto.ProductDTO;
import com.pharmacy.system.store.api.dto.ProductWithIdDTO;
import com.pharmacy.system.store.domain.model.Product;

/**
 * ProductAssembler
 */
@Component
public class ProductAssembler {

                public Product toEntity(ProductDTO dto) {
                                return Product.builder()
                                                                .name(dto.name())
                                                                .manufacturer(dto.manufacturer())
                                                                .description(dto.description())
                                                                .quantityInStock(dto.quantity())
                                                                .price(dto.price())
                                                                .expirationDate(dto.expiration_date())
                                                                .build();
                }

                public Product toEntity(ProductWithIdDTO dto) {
                                return Product.builder()
                                                                .id(dto.id())
                                                                .name(dto.name())
                                                                .manufacturer(dto.manufacturer())
                                                                .description(dto.description())
                                                                .quantityInStock(dto.quantity())
                                                                .price(dto.price())
                                                                .build();
                }

                public ProductWithIdDTO toOutput(Product entity) {
                                return new ProductWithIdDTO(
                                                                entity.getId(),
                                                                entity.getName(),
                                                                entity.getManufacturer(),
                                                                entity.getDescription(),
                                                                entity.getPrice(),
                                                                entity.getQuantityInStock(),
                                                                entity.getExpirationDate());
                }
}
