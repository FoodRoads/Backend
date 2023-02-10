package com.example.foodroads.config;

import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Configuration;


@EnableFeignClients(basePackages = "com.example.foodroads")
@Configuration
public class FeignClientConfig {

}
