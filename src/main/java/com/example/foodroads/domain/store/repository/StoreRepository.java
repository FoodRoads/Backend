package com.example.foodroads.domain.store.repository;

import com.example.foodroads.domain.store.entity.Store;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StoreRepository extends JpaRepository<Store, String> {
}
