package com.example.foodroads.domain.storelist.service;

import com.example.foodroads.domain.storelist.service.dto.StoreListAddRequest;
import com.example.foodroads.domain.storelist.service.dto.StoreListResponse;
import com.example.foodroads.domain.storelist.service.dto.StoreListUpdateRequest;

import java.util.List;

public interface StoreListService {
    List<StoreListResponse> getStoreLists(Long memberId);

    StoreListResponse addStoreList(Long memberId, StoreListAddRequest request);

    StoreListResponse updateStoreList(Long storeListId, StoreListUpdateRequest request, Long memberId);

    void deleteStoreList(Long storeListId, Long memberId);
}
