package com.example.foodroads.domain.auth.controller;

import com.example.foodroads.common.dto.ApiResponse;
import com.example.foodroads.config.interceptor.Auth;
import com.example.foodroads.config.resolver.MemberId;
import com.example.foodroads.domain.auth.service.AuthService;
import com.example.foodroads.domain.auth.service.dto.LoginRequest;
import com.example.foodroads.domain.auth.service.dto.LoginResponse;
import com.example.foodroads.domain.auth.service.dto.SignUpRequest;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @Operation(summary = "회원가입을 요청합니다.")
    @PostMapping("/signup")
    public ApiResponse<LoginResponse> signUp(@Valid @RequestBody SignUpRequest request) {
        return ApiResponse.success(authService.signUp(request));
    }

    @Operation(summary = "로그인을 요청합니다.")
    @PostMapping("/login")
    public ApiResponse<LoginResponse> login(@Valid @RequestBody LoginRequest request) {
        return ApiResponse.success(authService.login(request));
    }

    @Operation(summary = "로그아웃을 요청합니다.")
    @Auth
    @PostMapping("/logout")
    public ApiResponse<String> logout(@MemberId Long memberId) {
        authService.logout(memberId);
        return ApiResponse.success("OK");
    }

    @Operation(summary = "refreshToken 갱신을 요청합니다.")
    @PostMapping("/refresh")
    public ApiResponse<LoginResponse> refresh(@RequestParam String refreshToken) {
        return ApiResponse.success(authService.refresh(refreshToken));
    }

}
