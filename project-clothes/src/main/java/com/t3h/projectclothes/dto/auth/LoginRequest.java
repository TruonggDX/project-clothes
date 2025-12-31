package com.t3h.projectclothes.dto.auth;

import lombok.Data;

@Data
public class LoginRequest {

  private String email;

  private String password;
}
