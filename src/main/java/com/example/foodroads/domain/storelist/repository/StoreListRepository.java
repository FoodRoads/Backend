package com.example.foodroads.domain.storelist.repository;

import com.example.foodroads.domain.storelist.entity.StoreList;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StoreListRepository extends JpaRepository<StoreList, Long> {
}
