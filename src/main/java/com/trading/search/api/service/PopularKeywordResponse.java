package com.trading.search.api.service;

import com.trading.search.domain.ItemSearchKeyword;
import lombok.Builder;
import lombok.Getter;

@Getter
public class PopularKeywordResponse {

    private String itemKeyword;
    private Long itemSearchCount;

    @Builder
    private PopularKeywordResponse(String itemKeyword, Long itemSearchCount) {
        this.itemKeyword = itemKeyword;
        this.itemSearchCount = itemSearchCount;
    }

    public static PopularKeywordResponse from(ItemSearchKeyword itemSearchKeyword) {
        return PopularKeywordResponse.builder()
                .itemKeyword(itemSearchKeyword.getItemKeyword())
                .itemSearchCount(itemSearchKeyword.getItemSearchCount())
                .build();
    }
}
