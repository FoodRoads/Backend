package com.example.foodroads.config;

import com.example.foodroads.common.exception.model.BadGatewayException;
import com.example.foodroads.common.exception.model.InvalidException;
import feign.Response;
import feign.codec.ErrorDecoder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FeignDefaultConfig {

    @Bean
    public ErrorDecoder errorDecoder() {
        return new FeignErrorDecoder();
    }

    private static class FeignErrorDecoder implements ErrorDecoder {

        @Override
        public Exception decode(String methodKey, Response response) {
            if (400 <= response.status() && response.status() <= 499) {
                return new InvalidException(String.format("외부 API 호출 중 클라이언트 에러(%s)가 발생하였습니다. message: (%s)", response.status(), response.body()));
            }
            return new BadGatewayException(String.format("외부 API 연동 중 서버 에러(%s)가 발생하였습니다. message: (%s)", response.status(), response.body()));
        }

    }

}
