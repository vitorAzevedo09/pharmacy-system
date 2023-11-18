package com.pharmacy.system.store.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pharmacy.system.store.api.dto.AuthenticationDTO;
import com.pharmacy.system.store.api.dto.AuthorizationResponseDTO;
import com.pharmacy.system.store.api.dto.RegisterDTO;
import com.pharmacy.system.store.domain.model.User;
import com.pharmacy.system.store.domain.service.TokenService;
import com.pharmacy.system.store.domain.service.UserService;

import jakarta.validation.Valid;

/**
 * AuthenticationController
 */
@RestController
@RequestMapping("/auth")
public class AuthenticationController {

  @Autowired
  private AuthenticationManager authenticationManager;

  @Autowired
  private UserService userService;

  @Autowired
  private TokenService tokenService;

  public ResponseEntity<?> login(
      @RequestBody @Valid AuthenticationDTO data) {
    UsernamePasswordAuthenticationToken authenticate = new UsernamePasswordAuthenticationToken(data.login(),
        data.password());
    var authenticated = authenticationManager.authenticate(authenticate);

    var token = tokenService.generateToken((User) authenticated.getPrincipal());
    return ResponseEntity.ok(new AuthorizationResponseDTO(token));

  }

  @PostMapping("/register")
  public ResponseEntity<?> register(@RequestBody @Valid RegisterDTO data) {
    if (userService.findByUsername(data.login()) != null)
      return ResponseEntity.badRequest().build();
    String encryptedPassword = new BCryptPasswordEncoder().encode(data.password());
    User newUser = User.builder()
        .username(data.login())
        .password(encryptedPassword)
        .roles(userService.getAllFromIdIn(data.roles()))
        .build();
    newUser = userService.save(newUser);
    return ResponseEntity.ok(newUser);
  }

}
