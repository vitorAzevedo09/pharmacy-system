package com.pharmacy.system.store.api.dto;

import java.math.BigDecimal;

/**
 * OrderItemWithIdDTO
 */
public record OrderItemWithIdDTO(
        Long orderItemID,
        OrderWithIdDTO order,
        Integer quantity,
        BigDecimal pricePerUnit) {
}
