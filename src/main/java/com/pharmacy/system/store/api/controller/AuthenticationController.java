package com.pharmacy.system.store.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.pharmacy.system.store.api.assembler.AuthAssembler;
import com.pharmacy.system.store.api.dto.AuthenticationDTO;
import com.pharmacy.system.store.api.dto.AuthorizationResponseDTO;
import com.pharmacy.system.store.api.dto.RegisterDTO;
import com.pharmacy.system.store.domain.exception.UserConflictException;
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

  @Autowired
  private AuthAssembler authAssembler;

  @PostMapping("/login")
  public ResponseEntity<?> login(
      @RequestBody AuthenticationDTO data) {
    var authenticate = new UsernamePasswordAuthenticationToken(data.username(),
        data.password());
    System.out.println(authenticate);
    var authenticated = authenticationManager.authenticate(authenticate);
    User userAuthenticaded = (User) authenticated.getPrincipal();
    var token = tokenService.generateToken(userAuthenticaded);
    return ResponseEntity.ok(authAssembler.toAuthorizationResponseDTO(userAuthenticaded, token));

  }

  @PostMapping("/register")
  public ResponseEntity<?> register(@RequestBody @Valid RegisterDTO data) {
    try {
      String encryptedPassword = new BCryptPasswordEncoder().encode(data.password());
      User newUser = User.builder()
          .username(data.username())
          .password(encryptedPassword)
          .email(data.email())
          .firstName(data.first_name())
          .lastName(data.last_name())
          .enabled(true)
          .roles(userService.getAllFromIdIn(data.roles()))
          .build();
      newUser = userService.save(newUser);
      return ResponseEntity.ok().build();
    } catch (UserConflictException exception) {
      throw new ResponseStatusException(HttpStatus.CONFLICT, exception.getMessage());
    }
  }

}
