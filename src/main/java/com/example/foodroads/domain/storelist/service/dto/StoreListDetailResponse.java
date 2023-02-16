package com.example.foodroads.domain.storelist.service.dto;

import com.example.foodroads.domain.storelist.entity.StoreListDetail;
import lombok.*;

@ToString
@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class StoreListDetailResponse {

    private Long storeListDetailId;

    private String storeId;

    private String storeName;

    private String storeX;

    private String storeY;

    private String storeInfoUrl;

    private String description;

    public static StoreListDetailResponse of(StoreListDetail storeListDetail) {
        return new StoreListDetailResponse(storeListDetail.getId(), storeListDetail.getStore().getId(),
                storeListDetail.getStore().getName(), storeListDetail.getStore().getX(), storeListDetail.getStore().getY(),
                storeListDetail.getStore().getUrl(), storeListDetail.getDescription());
    }

}
