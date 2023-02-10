package com.example.foodroads.domain.auth.service;


import com.example.foodroads.client.provider.AuthProvider;
import jakarta.validation.constraints.NotNull;


public interface AuthProviderFinder {

    AuthProvider findAuthProvider(@NotNull String socialType);

}
