package com.pharmacy.system.store.pharmacysystemstore.api.dto;

import java.math.BigDecimal;

/**
 * ProductCreateDTO
 */

public record ProductCreateDTO(
        String name,
        String manufacturer,
        String description,
        int quantity,
        BigDecimal price) {

}
