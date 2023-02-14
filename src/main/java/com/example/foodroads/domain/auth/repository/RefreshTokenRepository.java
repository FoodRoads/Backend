package com.example.foodroads.domain.auth.repository;

import com.example.foodroads.domain.auth.entity.RefreshToken;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface RefreshTokenRepository extends CrudRepository<RefreshToken, Long> {
   List<RefreshToken> findAll();
   Optional<RefreshToken> findByRefreshToken(String refreshToken);
}