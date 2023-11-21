package com.pharmacy.system.store.api.assembler;

import org.springframework.stereotype.Component;

import com.pharmacy.system.store.api.dto.AuthorizationResponseDTO;
import com.pharmacy.system.store.domain.model.User;

/**
 * AuthAssembler
 */
@Component
public class AuthAssembler {

  public AuthorizationResponseDTO toAuthorizationResponseDTO(User user, String token) {
    return new AuthorizationResponseDTO(
        token,
        "Bearer",
        "",
        user.getId(),
        user.getUsername(),
        user.getEmail(),
        user.getRoles()
            .stream()
            .map(r -> r.getName())
            .toList());
  }

}
