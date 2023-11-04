package com.pharmacy.system.store.api.assembler;

import org.springframework.stereotype.Component;

import com.pharmacy.system.store.api.dto.ProductCreateDTO;
import com.pharmacy.system.store.api.dto.ProductOutputDTO;
import com.pharmacy.system.store.domain.model.Product;

/**
 * ProductAssembler
 */
@Component
public class ProductAssembler {

    public Product toEntity(ProductCreateDTO productInput) {
        return Product.builder()
                .name(productInput.name())
                .price(productInput.price())
                .manufacturer(productInput.manufacturer())
                .quantityInStock(productInput.quantity())
                .build();
    }

    public ProductOutputDTO toOutput(Product product) {
        return new ProductOutputDTO(product.getID(), product.getName(), product.getPrice());
    }

}
