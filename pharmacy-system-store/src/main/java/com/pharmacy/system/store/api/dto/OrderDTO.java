package com.pharmacy.system.store.api.dto;

import java.util.Date;
import java.util.Set;

/**
 * OrderOutput
 */
public record OrderDTO(
        Date orderDate,
        Set<OrderItemOnlyIdDTO> items) {
}
