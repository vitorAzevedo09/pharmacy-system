package com.pharmacy.system.store.api.dto;

import java.math.BigDecimal;

/**
 * OrderItemOnlyIdDTO
 */
public record OrderItemDTO(
    Long product_id,
    Integer quantity,
    BigDecimal price_unit) {
}
