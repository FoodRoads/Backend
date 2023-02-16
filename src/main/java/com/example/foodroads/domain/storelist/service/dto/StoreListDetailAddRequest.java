package com.example.foodroads.domain.storelist.service.dto;

import com.example.foodroads.domain.store.entity.Store;
import com.example.foodroads.domain.storelist.entity.StoreList;
import com.example.foodroads.domain.storelist.entity.StoreListDetail;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

@ToString
@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class StoreListDetailAddRequest {

    @NotBlank
    private Long storeListId;

    @NotBlank
    private String storeId;

    @NotBlank
    private String storeName;

    @NotBlank
    private String storeX;

    @NotBlank
    private String storeY;

    @NotBlank
    private String storeInfoUrl;

    private String description;

    @Builder
    private StoreListDetailAddRequest(Long storeListId, String storeId, String storeName, String storeX, String storeY, String storeInfoUrl, String description) {
        this.storeListId = storeListId;
        this.storeId = storeId;
        this.storeName = storeName;
        this.storeX = storeX;
        this.storeY = storeY;
        this.storeInfoUrl = storeInfoUrl;
        this.description = description;
    }

    public StoreListDetail toStoreListDetailEntity(StoreList storeList, Store store) {
        return StoreListDetail.newInstance(storeList, store, description);
    }

    public Store toStoreEntity() {
        return Store.newInstance(storeId, storeName, storeX, storeY, storeInfoUrl);
    }

    public void updateStore(Store store) {
        store.updateStore(storeName, storeX, storeY, storeInfoUrl);
    }
}
