package com.trading.transaction.domain;

import com.trading.common.BaseEntity;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class ItemTransaction extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long memberId;

    private Long inventoryId;

    private Long itemId;

    private int totalPrice;

    private int totalQuantity;

    @Enumerated(value = EnumType.STRING)
    private ItemTransactionStatus status;

    private LocalDateTime orderedAt;
}
