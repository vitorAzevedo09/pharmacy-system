package com.pharmacy.system.store.api.dto;

import java.time.OffsetDateTime;
import java.util.Set;

/**
 * OrderOutput
 */
public record OrderDTO(
                OffsetDateTime orderDateTime,
                Set<OrderItemDTO> items) {
}
