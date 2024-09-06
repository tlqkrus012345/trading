package com.trading.search.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class ItemSearchKeywordTest {

    @DisplayName("검색 시 카운트가 1 증가한다")
    @Test
    void increaseItemSearchCnt() {
        ItemSearchKeyword itemSearchKeyword = ItemSearchKeyword.create("테스트");

        itemSearchKeyword.increaseItemSearchCnt();

        assertThat(itemSearchKeyword.getItemSearchCount()).isEqualTo(1);
    }
}