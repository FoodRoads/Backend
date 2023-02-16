package com.example.foodroads.domain.storelist.service;

import com.example.foodroads.common.exception.ErrorCode;
import com.example.foodroads.common.exception.model.NotFoundException;
import com.example.foodroads.common.exception.model.UnAuthorizedException;
import com.example.foodroads.domain.member.repository.MemberRepository;
import com.example.foodroads.domain.store.entity.Store;
import com.example.foodroads.domain.store.repository.StoreRepository;
import com.example.foodroads.domain.storelist.entity.StoreList;
import com.example.foodroads.domain.storelist.entity.StoreListDetail;
import com.example.foodroads.domain.storelist.repository.StoreListDetailRepository;
import com.example.foodroads.domain.storelist.repository.StoreListRepository;
import com.example.foodroads.domain.storelist.service.dto.StoreListDetailAddRequest;
import com.example.foodroads.domain.storelist.service.dto.StoreListDetailResponse;
import com.example.foodroads.domain.storelist.service.dto.StoreListDetailUpdateRequest;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class StoreListDetailServiceImpl implements StoreListDetailService {

    private final MemberRepository memberRepository;

    private final StoreListDetailRepository storeListDetailRepository;

    private final StoreListRepository storeListRepository;

    private final StoreRepository storeRepository;

    @Override
    public List<StoreListDetailResponse> getStoreListDetails(Long storeListId) {

        StoreList storeList = storeListRepository.findById(storeListId).orElseThrow(
                ()-> new NotFoundException(String.format("해당하는 맛집 리스트(%s)는 존재하지 않습니다", storeListId), ErrorCode.E404_NOT_EXISTS_STORE_LIST)
        );

        return storeListDetailRepository.findByStoreList(storeList).stream()
                .map(StoreListDetailResponse::of)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public StoreListDetailResponse addStoreListDetail(Long memberId, StoreListDetailAddRequest request) {
        StoreList storeList = storeListRepository.findById(request.getStoreListId()).orElseThrow(
                ()-> new NotFoundException(String.format("해당하는 맛집 리스트(%s)는 존재하지 않습니다", request.getStoreListId()), ErrorCode.E404_NOT_EXISTS_STORE_LIST)
        );

        if (!storeList.getMember().getId().equals(memberId)) {
            throw new UnAuthorizedException("맛집 리스트 주인만 등록할 수 있습니다.");
        }

        Optional<Store> optionalStore = storeRepository.findById(request.getStoreId());
        Store store;

        if (optionalStore.isPresent()) {
            store = optionalStore.get();
            store.updateStore(request.getStoreName(), request.getStoreY(), request.getStoreY(), request.getStoreInfoUrl());
        } else {
            store = request.toStoreEntity();
            storeRepository.save(store);
        }

        return StoreListDetailResponse.of(storeListDetailRepository.save(request.toStoreListDetailEntity(storeList, store)));
    }

    @Override
    @Transactional
    public StoreListDetailResponse updateStoreListDetail(Long storeListDetailId, StoreListDetailUpdateRequest request, Long memberId) {
        StoreListDetail storeListDetail = storeListDetailRepository.findById(storeListDetailId).orElseThrow(
                () -> new NotFoundException(String.format("해당하는 맛집 리스트 상세(%s)는 존재하지 않습니다", storeListDetailId), ErrorCode.E404_NOT_EXISTS_STORE_LIST_DETAIL)
        );

        if (!storeListDetail.getStoreList().getMember().getId().equals(memberId)) {
            throw new UnAuthorizedException("등록자만 수정할 수 있습니다.");
        }

        storeListDetail.updateDescription(request.getDescription());

        return StoreListDetailResponse.of(storeListDetail);
    }

    @Override
    public void deleteStoreListDetail(Long storeListDetailId, Long memberId) {
        StoreListDetail storeListDetail = storeListDetailRepository.findById(storeListDetailId).orElseThrow(
                () -> new NotFoundException(String.format("해당하는 맛집 리스트 상세(%s)는 존재하지 않습니다", storeListDetailId), ErrorCode.E404_NOT_EXISTS_STORE_LIST_DETAIL)
        );

        if (!storeListDetail.getStoreList().getMember().getId().equals(memberId)) {
            throw new UnAuthorizedException("등록자만 삭제할 수 있습니다.");
        }

        storeListDetailRepository.delete(storeListDetail);
    }
}
