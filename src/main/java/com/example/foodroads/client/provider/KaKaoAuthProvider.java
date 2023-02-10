package com.example.foodroads.client.provider;

import com.example.foodroads.client.external.kakao.KaKaoAuthApiClient;
import com.example.foodroads.client.external.kakao.dto.response.KaKaoTokenResponse;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class KaKaoAuthProvider implements AuthProvider {

    private final KaKaoAuthApiClient kaKaoApiCaller;

    @Override
    public String getSocialId(@NotNull String token) {
        KaKaoTokenResponse response = kaKaoApiCaller.getUserProfile("Bearer " + token);
        return response.getId();
    }

}
