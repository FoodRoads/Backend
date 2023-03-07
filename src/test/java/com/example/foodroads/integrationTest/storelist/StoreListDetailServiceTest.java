package com.example.foodroads.integrationTest.storelist;

import com.example.foodroads.domain.store.entity.Store;
import com.example.foodroads.domain.store.repository.StoreRepository;
import com.example.foodroads.domain.storelist.entity.StoreListDetail;
import com.example.foodroads.domain.storelist.repository.StoreListDetailRepository;
import com.example.foodroads.domain.storelist.service.StoreListDetailService;
import com.example.foodroads.domain.storelist.service.dto.StoreListDetailAddRequest;
import com.example.foodroads.domain.storelist.service.dto.StoreListDetailUpdateRequest;
import com.example.foodroads.integrationTest.PreparedStoreListIntegrationTest;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

public class StoreListDetailServiceTest extends PreparedStoreListIntegrationTest {

    @Autowired
    private StoreListDetailService storeListDetailService;

    @Autowired
    private StoreListDetailRepository storeListDetailRepository;

    @Autowired
    private StoreRepository storeRepository;

    @AfterEach
    void cleanUp() {
        storeListDetailRepository.deleteAll();
        storeRepository.deleteAll();
    }

    @Nested
    class AddStoreListDetailTest {
        @Test
        void 해당_멤버의_리스트에_새로운_맛집을_등록합니다() {
            //given
            String storeId = "맛집ID";
            String storeName = "맛집 이름";
            String storeX = "맛집 x";
            String storeY = "맛집 y";
            String storeInfoUrl = "맛집 종보 주소";
            String description = "맛집 설명";

            StoreListDetailAddRequest request = StoreListDetailAddRequest.builder()
                    .storeListId(storeListId)
                    .storeId(storeId)
                    .storeName(storeName)
                    .storeX(storeX)
                    .storeY(storeY)
                    .storeInfoUrl(storeInfoUrl)
                    .description(description)
                    .build();
            //when
            storeListDetailService.addStoreListDetail(memberId, request);

            //then
            List<StoreListDetail> storeListDetails = storeListDetailRepository.findAll();
            List<Store> stores = storeRepository.findAll();

            assertAll(
                    () -> assertThat(storeListDetails).hasSize(1),
                    () -> assertThat(stores).hasSize(1),
                    () -> assertThat(storeListDetails.get(0).getStoreList().getId()).isEqualTo(storeListId),
                    () -> assertThat(storeListDetails.get(0).getDescription()).isEqualTo(description)
            );
        }
    }

    @Nested
    class UpdateStoreListDetailTest {
        @Test
        void 내가_추가한_맛집을_수정합니다() {
            //given
            String storeId = "맛집ID";
            String storeName = "맛집 이름";
            String storeX = "맛집 x";
            String storeY = "맛집 y";
            String storeInfoUrl = "맛집 종보 주소";
            String description = "맛집 설명";
            String updatedDescription = "맛집 수정 설명";

            Store store = Store.newInstance(storeId, storeName, storeX, storeY, storeInfoUrl);
            storeRepository.save(store);
            StoreListDetail storeListDetail = StoreListDetail.newInstance(storeList, store, description);
            storeListDetailRepository.save(storeListDetail);

            StoreListDetailUpdateRequest request = StoreListDetailUpdateRequest.builder()
                    .description(updatedDescription)
                    .build();

            //when
            storeListDetailService.updateStoreListDetail(storeListDetail.getId(), request, memberId);

            //then
            List<StoreListDetail> storeListDetails = storeListDetailRepository.findAll();
            List<Store> stores = storeRepository.findAll();

            assertAll(
                    () -> assertThat(storeListDetails).hasSize(1),
                    () -> assertThat(stores).hasSize(1),
                    () -> assertThat(storeListDetails.get(0).getDescription()).isEqualTo(updatedDescription)
            );
        }
    }

    @Nested
    class DeleteStoreListDetailTest {

        @Test
        void 내가_추가한_맛집을_삭제합니다() {
            // given
            String storeId = "맛집ID";
            String storeName = "맛집 이름";
            String storeX = "맛집 x";
            String storeY = "맛집 y";
            String storeInfoUrl = "맛집 종보 주소";
            String description = "맛집 설명";

            Store store = Store.newInstance(storeId, storeName, storeX, storeY, storeInfoUrl);
            storeRepository.save(store);
            StoreListDetail storeListDetail = StoreListDetail.newInstance(storeList, store, description);
            storeListDetailRepository.save(storeListDetail);

            // when
            storeListDetailService.deleteStoreListDetail(storeListDetail.getId(), memberId);

            // then
            List<StoreListDetail> storeListDetails = storeListDetailRepository.findAll();
            List<Store> stores = storeRepository.findAll();

            assertAll(
                    () -> assertThat(storeListDetails).hasSize(0),
                    () -> assertThat(stores).hasSize(1)
            );
        }
    }
}
