package com.trading.itemsale.domain;

import com.trading.common.BaseEntity;
import com.trading.item.domain.ItemType;
import jakarta.persistence.*;
import lombok.AccessLevel;
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

    private ItemType itemType;

    private String description;

    private int quantity;

    @Enumerated(value = EnumType.STRING)
    private ItemSaleInfoStatus status;
}
