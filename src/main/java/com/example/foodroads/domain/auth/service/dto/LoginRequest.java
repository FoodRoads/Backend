package com.example.foodroads.domain.auth.service.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@ToString
@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class LoginRequest {

    @NotBlank
    private String token;

    @NotNull
    private String socialType;

    @Builder
    private LoginRequest(String token, String  socialType) {
        this.token = token;
        this.socialType = socialType;
    }
}
