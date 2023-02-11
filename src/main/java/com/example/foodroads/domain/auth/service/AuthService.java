package com.example.foodroads.domain.auth.service;

import com.example.foodroads.domain.auth.service.dto.LoginRequest;
import com.example.foodroads.domain.auth.service.dto.LoginResponse;
import com.example.foodroads.domain.auth.service.dto.SignUpRequest;

public interface AuthService {

    LoginResponse signUp(SignUpRequest request);

    LoginResponse login(LoginRequest request);

    void logout(Long memberId);

    LoginResponse refresh(String refreshToken);

    void checkAvailableName(String name);
}
