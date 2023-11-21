package com.pharmacy.system.store.api.dto;

import java.util.List;

/**
 * AuthorizationResponseDTO
 */
public record AuthorizationResponseDTO(
    String token,
    String type,
    String refreshToken,
    Long id,
    String username,
    String email,
    List<String> roles) {
}
