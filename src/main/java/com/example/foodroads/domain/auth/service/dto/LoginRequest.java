package com.example.foodroads.domain.auth.service.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@ToString
@Getter
@NoArgsConstructor
public class LoginRequest {

    @NotBlank
    private String token;

    @NotNull
    private String socialType;
}
