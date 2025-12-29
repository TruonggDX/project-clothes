package com.t3h.projectclothes.service;

import com.t3h.projectclothes.dto.auth.LoginRequest;
import com.t3h.projectclothes.dto.auth.LoginResponse;

public interface AuthService {

  LoginResponse login(LoginRequest request);
}
