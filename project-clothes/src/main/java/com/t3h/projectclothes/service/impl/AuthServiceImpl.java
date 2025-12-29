package com.t3h.projectclothes.service.impl;

import com.t3h.projectclothes.dto.auth.LoginRequest;
import com.t3h.projectclothes.dto.auth.LoginResponse;
import com.t3h.projectclothes.entity.UserEntity;
import com.t3h.projectclothes.repository.AccountRepository;
import com.t3h.projectclothes.security.JwtTokenUtil;
import com.t3h.projectclothes.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

  private final AccountRepository accountRepository;
  private final PasswordEncoder passwordEncoder;
  private final JwtTokenUtil jwtTokenUtil;

  @Override
  public LoginResponse login(LoginRequest request) {
    UserEntity user =
        accountRepository
            .findByEmailAndIsDeletedFalse(request.getEmail())
            .orElseThrow(() -> new RuntimeException("Invalid email or password"));

    if (!user.isEnabled()
        || !passwordEncoder.matches(request.getPassword(), user.getPassword())) {
      throw new RuntimeException("Invalid email or password");
    }

    String token = jwtTokenUtil.generateToken(user.getEmail());

    return LoginResponse.builder()
        .token(token)
        .email(user.getEmail())
        .name(user.getName())
        .build();
  }
}
