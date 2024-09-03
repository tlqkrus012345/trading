package com.trading.itemsale.domain;

import com.trading.common.BaseEntity;
import com.trading.item.domain.ItemType;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class ItemSaleInfo extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long memberId;

    private Long inventoryId;

    private Long itemId;

    private String memberName;

    private String itemName;

    private int itemPrice;

    @Enumerated(EnumType.STRING)
    private ItemType itemType;

    private String description;

    private int quantity;

    @Enumerated(value = EnumType.STRING)
    private ItemSaleInfoStatus status;

    @Builder
    private ItemSaleInfo(Long memberId, Long inventoryId, Long itemId, String memberName, String itemName, int itemPrice, ItemType itemType, String description, int quantity, ItemSaleInfoStatus status) {
        this.memberId = memberId;
        this.inventoryId = inventoryId;
        this.itemId = itemId;
        this.memberName = memberName;
        this.itemName = itemName;
        this.itemPrice = itemPrice;
        this.itemType = itemType;
        this.description = description;
        this.quantity = quantity;
        this.status = status;
    }
}
