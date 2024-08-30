package com.trading.itemsale.domain;

import com.trading.common.BaseEntity;
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

    @Enumerated(value = EnumType.STRING)
    private ItemSaleInfoStatus status;
}
