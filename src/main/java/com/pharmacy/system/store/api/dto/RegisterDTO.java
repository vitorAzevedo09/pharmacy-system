package com.pharmacy.system.store.api.dto;

import java.util.List;

/**
 * RegisterDTO
 */
public record RegisterDTO(
    String username,
    String password,
    String first_name,
    String last_name,
    String email,
    List<Long> roles) {
}
