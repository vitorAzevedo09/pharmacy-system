package com.pharmacy.system.store.api.dto;

import java.util.List;

/**
 * RegisterDTO
 */
public record RegisterDTO(String login, String password, List<Long> roles) {
}
