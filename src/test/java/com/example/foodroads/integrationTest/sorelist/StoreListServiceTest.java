package com.example.foodroads.integrationTest.sorelist;

import com.example.foodroads.domain.storelist.entity.StoreList;
import com.example.foodroads.domain.storelist.repository.StoreListRepository;
import com.example.foodroads.domain.storelist.service.StoreListService;
import com.example.foodroads.domain.storelist.service.dto.StoreListAddRequest;
import com.example.foodroads.domain.storelist.service.dto.StoreListUpdateRequest;
import com.example.foodroads.integrationTest.PreparedMemberIntegrationTest;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

public class StoreListServiceTest extends PreparedMemberIntegrationTest {

    @Autowired
    private StoreListService storeListService;

    @Autowired
    private StoreListRepository storeListRepository;

    @AfterEach
    void cleanUp() {
        storeListRepository.deleteAll();
    }

    @Nested
    class AddStoreListTest {
        @Test
        void 해당_멤버의_새로운_맛집_리스트를_등록합니다() {
            //given
            String storeListName = "나의 맛집 리스트";

            StoreListAddRequest request = StoreListAddRequest.builder()
                    .name(storeListName)
                    .build();
            //when
            storeListService.addStoreList(memberId, request);

            //then
            List<StoreList> storeLists = storeListRepository.findAll();

            assertAll(
                    () -> assertThat(storeLists).hasSize(1),
                    () -> assertThat(storeLists.get(0).getName()).isEqualTo(storeListName),
                    () -> assertThat(storeLists.get(0).getMember().getId()).isEqualTo(memberId)
            );
        }
    }

    @Nested
    class UpdateStoreListTest {
        @Test
        void 내가_추가한_맛집_리스트를_수정합니다() {
            //given
            String storeListName = "나의 수정 맛집 리스트";

            StoreList storeList = StoreList.newInstance(member, "나의 맛집 리스트");
            storeListRepository.save(storeList);

            StoreListUpdateRequest request = StoreListUpdateRequest.builder()
                    .name(storeListName)
                    .build();

            //when
            storeListService.updateStoreList(storeList.getId(), request, memberId);

            //then
            List<StoreList> storeLists = storeListRepository.findAll();

            assertAll(
                    () -> assertThat(storeLists).hasSize(1),
                    () -> assertThat(storeLists.get(0).getName()).isEqualTo(storeListName),
                    () -> assertThat(storeLists.get(0).getMember().getId()).isEqualTo(memberId)
            );
        }
    }

    @Nested
    class DeleteStoreListTest {

        @Test
        void 내가_추가한_맛집_리스트를_삭제합니다() {
            // given
            StoreList storeList = StoreList.newInstance(member, "나의 맛집 리스트");
            storeListRepository.save(storeList);

            // when
            storeListService.deleteStoreList(storeList.getId(), memberId);

            // then
            List<StoreList> storeLists = storeListRepository.findAll();

            assertThat(storeLists).hasSize(0);
        }
    }
}
