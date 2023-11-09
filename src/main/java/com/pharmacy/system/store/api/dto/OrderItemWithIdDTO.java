package com.pharmacy.system.store.api.dto;

import java.math.BigDecimal;

/**
 * OrderItemWithIdDTO
 */
public record OrderItemWithIdDTO(
                Long orderItemID,
                Integer quantity,
                BigDecimal pricePerUnit) {
}
