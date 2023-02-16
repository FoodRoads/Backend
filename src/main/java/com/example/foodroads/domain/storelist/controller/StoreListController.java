package com.example.foodroads.domain.storelist.controller;

import com.example.foodroads.common.dto.ApiResponse;
import com.example.foodroads.config.interceptor.Auth;
import com.example.foodroads.config.resolver.MemberId;
import com.example.foodroads.domain.storelist.service.StoreListService;
import com.example.foodroads.domain.storelist.service.dto.StoreListAddRequest;
import com.example.foodroads.domain.storelist.service.dto.StoreListResponse;
import com.example.foodroads.domain.storelist.service.dto.StoreListUpdateRequest;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class StoreListController {

    private final StoreListService storeListService;

    @Operation(summary = "나의 맛집 리스트들을 조회합니다.")
    @Auth
    @GetMapping("/storelists/me")
    ApiResponse<List<StoreListResponse>> getMyStoreLists(@MemberId Long memberId) {
        return ApiResponse.success(storeListService.getStoreLists(memberId));
    }

    @Operation(summary = "나의 맛집 리스트를 등록합니다.")
    @Auth
    @PostMapping("/storelists/me")
    ApiResponse<StoreListResponse> addMyStoreList(@Valid @RequestBody StoreListAddRequest request, @MemberId Long memberId) {
        return ApiResponse.success(storeListService.addStoreList(memberId, request));
    }

    @Operation(summary = "나의 맛집 리스트 {storeListId}를 수정합니다.")
    @Auth
    @PatchMapping("/storelists/me/{storeListId}")
    ApiResponse<StoreListResponse> updateMyStoreList(@PathVariable Long storeListId,
                                                     @Valid @RequestBody StoreListUpdateRequest request,
                                                     @MemberId Long memberId) {
        return ApiResponse.success(storeListService.updateStoreList(storeListId, request, memberId));
    }

    @Operation(summary = "나의 맛집 리스트 {storeListId}를 삭제합니다.")
    @Auth
    @DeleteMapping("/storelists/me/{storeListId}")
    ApiResponse<String> deleteMyStoreList(@PathVariable Long storeListId, @MemberId Long memberId) {
        storeListService.deleteStoreList(storeListId, memberId);
        return ApiResponse.success("OK");
    }

    @Operation(summary = "맛집 리스트들을 keyword로 검색합니다.")
    @GetMapping("/storelists/search")
    ApiResponse<List<StoreListResponse>> searchStoreLists(@RequestParam String keyword) {
        return ApiResponse.success(storeListService.searchStoreLists(keyword));
    }
}
