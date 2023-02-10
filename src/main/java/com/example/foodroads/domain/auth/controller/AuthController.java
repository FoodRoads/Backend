package com.example.foodroads.domain.auth.controller;

import com.example.foodroads.common.dto.ApiResponse;
import com.example.foodroads.config.interceptor.Auth;
import com.example.foodroads.config.resolver.MemberId;
import com.example.foodroads.domain.auth.service.AuthService;
import com.example.foodroads.domain.auth.service.dto.LoginRequest;
import com.example.foodroads.domain.auth.service.dto.LoginResponse;
import com.example.foodroads.domain.auth.service.dto.SignUpRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/signup")
    public ApiResponse<LoginResponse> signUp(@Valid @RequestBody SignUpRequest request) {
        return ApiResponse.success(authService.signUp(request));
    }

    @PostMapping("/login")
    public ApiResponse<LoginResponse> login(@Valid @RequestBody LoginRequest request) {
        return ApiResponse.success(authService.login(request));
    }

    @Auth
    @PostMapping("/logout")
    public ApiResponse<String> logout(@MemberId Long memberId) {
        authService.logout(memberId);
        return ApiResponse.success("OK");
    }

    @Auth
    @PostMapping("/refresh")
    public ApiResponse<LoginResponse> refresh(@MemberId Long memberId, @RequestBody String refreshToken) {
        return ApiResponse.success(authService.refresh(memberId, refreshToken));
    }

}
