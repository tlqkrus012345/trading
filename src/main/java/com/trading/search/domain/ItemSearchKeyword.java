package com.trading.search.domain;

import jakarta.persistence.*;
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

    @Version
    private long version;

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
