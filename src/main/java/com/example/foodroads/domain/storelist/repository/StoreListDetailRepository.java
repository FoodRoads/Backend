package com.example.foodroads.domain.storelist.repository;

import com.example.foodroads.domain.storelist.entity.StoreList;
import com.example.foodroads.domain.storelist.entity.StoreListDetail;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface StoreListDetailRepository extends JpaRepository<StoreListDetail, Long> {

    List<StoreListDetail> findByStoreList(StoreList storeList);
}
