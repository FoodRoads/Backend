package com.example.foodroads.domain.storelist.service;

import com.example.foodroads.common.exception.ErrorCode;
import com.example.foodroads.common.exception.model.NotFoundException;
import com.example.foodroads.domain.member.entity.Member;
import com.example.foodroads.domain.member.repository.MemberRepository;
import com.example.foodroads.domain.storelist.entity.StoreList;
import com.example.foodroads.domain.storelist.repository.StoreListRepository;
import com.example.foodroads.domain.storelist.service.dto.StoreListAddRequest;
import com.example.foodroads.domain.storelist.service.dto.StoreListResponse;
import com.example.foodroads.domain.storelist.service.dto.StoreListUpdateRequest;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

import static com.example.foodroads.common.exception.ErrorCode.E404_NOT_EXISTS_USER;

@Service
@RequiredArgsConstructor
public class StoreListServiceImpl implements StoreListService {

    private final MemberRepository memberRepository;

    private final StoreListRepository storeListRepository;

    @Override
    public List<StoreListResponse> getStoreLists(Long memberId) {
        Member member = memberRepository.findById(memberId).orElseThrow(
                () -> new NotFoundException("존재하지 않는 유저 입니다", E404_NOT_EXISTS_USER)
        );

        return member.getStoreLists().stream()
                .map(StoreListResponse::of)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public StoreListResponse addStoreList(Long memberId, StoreListAddRequest request) {
        Member member = memberRepository.findById(memberId).orElseThrow(
                () -> new NotFoundException("존재하지 않는 유저 입니다", E404_NOT_EXISTS_USER)
        );

        return StoreListResponse.of(storeListRepository.save(request.toEntity(member)));
    }

    @Override
    @Transactional
    public StoreListResponse updateStoreList(Long storeListId, StoreListUpdateRequest request, Long memberId) {
        Member member = memberRepository.getReferenceById(memberId);

        StoreList storeList = storeListRepository.findByIdAndMember(storeListId, member).orElseThrow(
                ()-> new NotFoundException(String.format("해당하는 맛집 리스트(%s)는 존재하지 않습니다", storeListId), ErrorCode.E404_NOT_EXISTS_STORE_LIST)
        );

        storeList.updateName(request.getName());

        return StoreListResponse.of(storeList);
    }

    @Override
    public void deleteStoreList(Long storeListId, Long memberId) {
        Member member = memberRepository.getReferenceById(memberId);

        StoreList storeList = storeListRepository.findByIdAndMember(storeListId, member).orElseThrow(
                ()-> new NotFoundException(String.format("해당하는 맛집 리스트(%s)는 존재하지 않습니다", storeListId), ErrorCode.E404_NOT_EXISTS_STORE_LIST)
        );

        storeListRepository.delete(storeList);
    }

    @Override
    public List<StoreListResponse> searchStoreLists(String keyword) {
        return storeListRepository.searchByKeyword(keyword).stream()
                .map(StoreListResponse::of)
                .collect(Collectors.toList());
    }
}
