package com.pharmacy.system.store.api.dto;

/**
 * ImageWithIdDTO
 */
public record ImageWithIdDTO(
    Long id,
    String image_url,
    String description) {
}
