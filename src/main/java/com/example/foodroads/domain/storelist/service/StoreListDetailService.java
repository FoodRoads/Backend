package com.example.foodroads.domain.storelist.service;

import com.example.foodroads.domain.storelist.service.dto.StoreListDetailAddRequest;
import com.example.foodroads.domain.storelist.service.dto.StoreListDetailResponse;
import com.example.foodroads.domain.storelist.service.dto.StoreListDetailUpdateRequest;

import java.util.List;

public interface StoreListDetailService {
    List<StoreListDetailResponse> getStoreListDetails(Long storeListId);

    StoreListDetailResponse addStoreListDetail(Long memberId, StoreListDetailAddRequest request);

    StoreListDetailResponse updateStoreListDetail(Long storeListDetailId, StoreListDetailUpdateRequest request, Long memberId);

    void deleteStoreListDetail(Long storeListDetailId, Long memberId);
}
