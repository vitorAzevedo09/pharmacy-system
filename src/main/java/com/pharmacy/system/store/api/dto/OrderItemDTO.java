package com.pharmacy.system.store.api.dto;

/**
 * OrderItemOnlyIdDTO
 */
public record OrderItemDTO(
                ProductOnlyIdDTO product,
                Integer quantity) {
}
