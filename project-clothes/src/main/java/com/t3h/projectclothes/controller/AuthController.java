package com.t3h.projectclothes.controller;

import com.t3h.projectclothes.dto.auth.LoginRequest;
import com.t3h.projectclothes.dto.common.BaseResponse;
import com.t3h.projectclothes.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/auth")
public class AuthController {
  private final AuthService authService;

  @PostMapping("/login")
  public ResponseEntity<BaseResponse<String>> login(@RequestBody LoginRequest loginRequest) {
    return ResponseEntity.ok(BaseResponse.success(authService.login(loginRequest)));
  }

}
