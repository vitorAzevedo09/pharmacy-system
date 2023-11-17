package com.pharmacy.system.store.api.dto;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.Set;

import com.pharmacy.system.store.domain.model.enumerate.OrderStatus;

/**
 * OrderWithIdDTO
 */
public record OrderWithIdDTO(
                                Long ID,
                                UserOnlyIdDTO customer,
                                OffsetDateTime order_date,
                                BigDecimal totalAmount,
                                OrderStatus status,
                                Set<OrderItemWithIdDTO> items) {
}
