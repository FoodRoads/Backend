package com.example.foodroads.config;

import com.example.foodroads.config.resolver.MemberId;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springdoc.core.utils.SpringDocUtils;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI openAPI() {
        SpringDocUtils.getConfig().addAnnotationsToIgnore(MemberId.class);
        return new OpenAPI()
                .info(this.info())
                .components(new Components()
                        .addSecuritySchemes(HttpHeaders.AUTHORIZATION, new SecurityScheme()
                                .type(SecurityScheme.Type.APIKEY)
                                .in(SecurityScheme.In.HEADER)
                                .name(HttpHeaders.AUTHORIZATION)
                        )
                )
                .addSecurityItem(new SecurityRequirement()
                        .addList(HttpHeaders.AUTHORIZATION));
    }

    private Info info() {
        return new Info()
                .title("FoodRoads API")
                .version("0.1")
                .description("FoodRoads API 입니다.");
    }
}
