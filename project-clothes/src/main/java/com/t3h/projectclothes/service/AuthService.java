package com.t3h.projectclothes.service;

import com.t3h.projectclothes.dto.auth.LoginRequest;

public interface AuthService {

  String login(LoginRequest loginRequest);
}
