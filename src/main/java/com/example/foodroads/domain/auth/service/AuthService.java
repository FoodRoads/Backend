package com.example.foodroads.domain.auth.service;

import com.example.foodroads.domain.auth.service.dto.LoginRequest;
import com.example.foodroads.domain.auth.service.dto.LoginResponse;

public interface AuthService {
    LoginResponse login(LoginRequest request);
}
