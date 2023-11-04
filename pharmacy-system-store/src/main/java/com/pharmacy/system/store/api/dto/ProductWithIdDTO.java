package com.pharmacy.system.store.api.dto;

import java.math.BigDecimal;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * ProductDTO
 */
public record ProductWithIdDTO(
                                Long id,
                                String name,
                                String manufacturer,
                                String description,
                                BigDecimal price,
                                int quantity,
                                @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy") Date expiration_date) {
}
