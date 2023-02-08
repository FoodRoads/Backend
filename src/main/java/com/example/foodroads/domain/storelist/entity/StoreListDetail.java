package com.example.foodroads.domain.storelist.entity;

import com.example.foodroads.domain.store.entity.Store;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class StoreListDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @ManyToOne
    @JoinColumn(name = "store_list_id")
    private StoreList storeList;

    @ManyToOne
    @JoinColumn(name = "store_id")
    private Store store;

    private String description;
}
