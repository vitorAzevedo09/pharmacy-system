package com.pharmacy.system.store.api.dto;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.Date;
import java.util.Set;

/**
 * OrderOutput
 */
public record OrderDTO(
    Long ID,
    Date orderDate,
    BigDecimal totalAmount,
    Set<OrderItemWithIdDTO> items,
    OffsetDateTime confirmationDate,
    OffsetDateTime cancellationDate,
    OffsetDateTime deliveryDate) {
}
