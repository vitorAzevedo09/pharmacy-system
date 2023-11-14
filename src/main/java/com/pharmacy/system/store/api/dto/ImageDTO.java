package com.pharmacy.system.store.api.dto;

import org.springframework.web.multipart.MultipartFile;

/**
 * ImageDTO
 */
public record ImageDTO(
    MultipartFile file,
    String description) {
}
