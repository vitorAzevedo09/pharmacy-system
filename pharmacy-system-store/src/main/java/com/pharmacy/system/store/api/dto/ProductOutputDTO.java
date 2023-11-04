package com.pharmacy.system.store.api.dto;

import java.math.BigDecimal;

/**
 * ProductOutputDTO
 */
public record ProductOutputDTO(
        Long id,
        String name,
        BigDecimal price) {
}
