package com.example.foodroads.domain.auth.repository;

import com.example.foodroads.domain.auth.entity.RefreshToken;
import org.springframework.boot.context.properties.bind.BindResult;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RefreshTokenRepository extends JpaRepository<RefreshToken, Long> {
   Optional<RefreshToken> findByRefreshToken(String refreshToken);
}