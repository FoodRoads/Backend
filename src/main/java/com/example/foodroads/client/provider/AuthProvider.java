package com.example.foodroads.client.provider;


import jakarta.validation.constraints.NotNull;

public interface AuthProvider {

    String getSocialId(@NotNull String token);

}
