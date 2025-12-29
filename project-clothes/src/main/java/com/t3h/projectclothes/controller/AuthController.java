package com.t3h.projectclothes.controller;

import com.t3h.projectclothes.dto.auth.LoginRequest;
import com.t3h.projectclothes.dto.auth.LoginResponse;
import com.t3h.projectclothes.dto.common.BaseResponse;
import com.t3h.projectclothes.service.AuthService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

  private final AuthService authService;

  @PostMapping("/login")
  public BaseResponse<LoginResponse> login(@Valid @RequestBody LoginRequest request) {
    return BaseResponse.success(authService.login(request));
  }
}
