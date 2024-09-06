package com.trading.item.domain;

import com.trading.common.BaseEntity;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class Item extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @Enumerated(EnumType.STRING)
    private ItemType type;

    private int price;

    private String description;

    @Builder
    private Item(String name, ItemType type, int price, String description) {
        this.name = name;
        this.type = type;
        this.price = price;
        this.description = description;
    }
}
