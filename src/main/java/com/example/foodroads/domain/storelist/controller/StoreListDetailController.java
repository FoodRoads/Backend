package com.example.foodroads.domain.storelist.controller;

import com.example.foodroads.common.dto.ApiResponse;
import com.example.foodroads.config.interceptor.Auth;
import com.example.foodroads.config.resolver.MemberId;
import com.example.foodroads.domain.storelist.service.StoreListDetailService;
import com.example.foodroads.domain.storelist.service.dto.StoreListDetailAddRequest;
import com.example.foodroads.domain.storelist.service.dto.StoreListDetailResponse;
import com.example.foodroads.domain.storelist.service.dto.StoreListDetailUpdateRequest;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class StoreListDetailController {

    private final StoreListDetailService storeListDetailService;

    @Operation(summary = "특정 맛집 리스트들을 조회합니다.")
    @GetMapping("/storelists/{storeListId}")
    ApiResponse<List<StoreListDetailResponse>> getMyStoreLists(@PathVariable Long storeListId) {
        return ApiResponse.success(storeListDetailService.getStoreListDetails(storeListId));
    }

    @Operation(summary = "특정 맛집 리스트에 맛집을 등록합니다.")
    @Auth
    @PostMapping("/storelist/detail")
    ApiResponse<StoreListDetailResponse> addMyStoreListDetail(@Valid @RequestBody StoreListDetailAddRequest request,
                                                        @MemberId Long memberId) {
        return ApiResponse.success(storeListDetailService.addStoreListDetail(memberId, request));
    }

    @Operation(summary = "특정 맛집 리스트에 등록한 맛집을 수정합니다.")
    @Auth
    @PatchMapping("/storelist/detail/{storeListDetailId}")
    ApiResponse<StoreListDetailResponse> updateMyStoreListDetail(@PathVariable Long storeListDetailId,
                                                           @Valid @RequestBody StoreListDetailUpdateRequest request,
                                                           @MemberId Long memberId) {
        return ApiResponse.success(storeListDetailService.updateStoreListDetail(storeListDetailId, request, memberId));
    }

    @Operation(summary = "특정 맛집 리스트에 등록한 맛집을 삭제합니다.")
    @Auth
    @DeleteMapping("/storelist/detail/{storeListDetailId}")
    ApiResponse<String> deleteMyStoreListDetail(@PathVariable Long storeListDetailId, @MemberId Long memberId) {
        storeListDetailService.deleteStoreListDetail(storeListDetailId, memberId);
        return ApiResponse.success("OK");
    }
}
