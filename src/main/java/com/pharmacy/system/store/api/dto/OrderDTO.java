package com.pharmacy.system.store.api.dto;

import java.util.Set;

/**
 * OrderOutput
 */
public record OrderDTO(
                                CustomerWithIdDTO customer,
                                Set<OrderItemDTO> items) {
}
