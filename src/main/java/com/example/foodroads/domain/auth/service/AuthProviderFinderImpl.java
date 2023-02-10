package com.example.foodroads.domain.auth.service;

import com.example.foodroads.client.provider.AuthProvider;
import com.example.foodroads.client.provider.KaKaoAuthProvider;
import com.example.foodroads.common.exception.model.NotImplementedException;
import jakarta.annotation.PostConstruct;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@RequiredArgsConstructor
@Component
public class AuthProviderFinderImpl implements AuthProviderFinder {

    private static final Map<String, AuthProvider> authProviderMap = new HashMap<>();

    private final KaKaoAuthProvider kaKaoAuthProvider;

    @PostConstruct
    void initializeAuthProviders() {
        authProviderMap.put("KAKAO", kaKaoAuthProvider);
    }

    @Override
    public AuthProvider findAuthProvider(@NotNull String socialType) {
        AuthProvider authProvider = authProviderMap.get(socialType);
        if (authProvider == null) {
            throw new NotImplementedException(String.format("현재 지원하지 않는 소셜 타입(%s) 입니다", socialType));
        }
        return authProvider;
    }

}
