package com.trading.search.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class ItemSearchKeyword {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String itemKeyword;

    private Long itemSearchCount;

    @Builder
    private ItemSearchKeyword(String itemKeyword, Long itemSearchCount) {
        this.itemKeyword = itemKeyword;
        this.itemSearchCount = itemSearchCount;
    }

    public static ItemSearchKeyword create(String keyword) {
        return ItemSearchKeyword.builder()
                .itemKeyword(keyword)
                .itemSearchCount(0L)
                .build();
    }

    public void increaseItemSearchCnt() {
        itemSearchCount += 1;
    }
}
