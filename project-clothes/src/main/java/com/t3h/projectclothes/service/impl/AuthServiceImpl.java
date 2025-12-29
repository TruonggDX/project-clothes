package com.t3h.projectclothes.service.impl;

import com.t3h.projectclothes.dto.auth.LoginRequest;
import com.t3h.projectclothes.exception.BusinessException;
import com.t3h.projectclothes.security.service.JwtService;
import com.t3h.projectclothes.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

  private final AuthenticationManager authenticationManager;
  private final JwtService jwtService;

  @Override
  public String login(LoginRequest loginRequest) {
    try {
      authenticationManager.authenticate(
          new UsernamePasswordAuthenticationToken(loginRequest.getEmail(),
              loginRequest.getPassword()));
      return jwtService.generateToken(loginRequest.getEmail());
    } catch (AuthenticationException e) {
      throw new BusinessException(401, "Invalid mail or password");
    } catch (Exception e) {
      throw new BusinessException(500, "Internal error");
    }
  }
}
