package com.pharmacy.system.store.api.dto;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.List;

/**
 * ProductCreateDTO
 */

public record ProductDTO(
                                                  String name,
                                                  String manufacturer,
                                                  String description,
                                                  int quantity,
                                                  BigDecimal price,
                                                  Date expiration_date,
                                                  List<ImageWithIdDTO> image) {
}
