package com.example.foodroads.client.external.kakao;

import com.example.foodroads.client.external.kakao.dto.response.KaKaoTokenResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;

@FeignClient(
    name = "KakaoAuthApiClient",
    url = "https://kapi.kakao.com",
    configuration = {
        KaKaoFeignConfig.class
    }
)
public interface KaKaoAuthApiClient {

    @GetMapping("/v1/user/access_token_info")
    KaKaoTokenResponse getUserProfile(@RequestHeader("Authorization") String accessToken);

}
