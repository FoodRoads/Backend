package com.example.foodroads.domain.storelist.service.dto;

import com.example.foodroads.domain.storelist.entity.StoreList;
import com.example.foodroads.domain.storelist.entity.StoreListDetail;
import lombok.*;

@ToString
@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class StoreDetailResponse {

    private Long storeListDetailId;

    private Long storeId;

    private String storeName;

    private String storeX;

    private String storeY;

    private String storeInfoUrl;

    private String description;

    public static StoreDetailResponse of(StoreListDetail storeListDetail) {
        return new StoreDetailResponse(storeListDetail.getId(), storeListDetail.getStore().getId(),
                storeListDetail.getStore().getName(), storeListDetail.getStore().getX(), storeListDetail.getStore().getY(),
                storeListDetail.getStore().getUrl(), storeListDetail.getDescription());
    }

}
