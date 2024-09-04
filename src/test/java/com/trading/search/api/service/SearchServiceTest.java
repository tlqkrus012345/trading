package com.trading.search.api.service;

import com.trading.search.domain.ItemSearchKeyword;
import com.trading.search.domain.ItemSearchKeywordRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import static org.assertj.core.api.Assertions.assertThat;

@ActiveProfiles("test")
@SpringBootTest
class SearchServiceTest {

    @Autowired
    private SearchService searchService;

    @Autowired
    private ItemSearchKeywordRepository itemSearchKeywordRepository;

    @DisplayName("특정 키워드가 검색이 되면 카운트가 1 증가한다.")
    @Test
    void searchItemKeyword() {
        String itemKeyword = "강철";

        searchService.searchItemKeyword(itemKeyword);

        ItemSearchKeyword itemSearchKeyword = itemSearchKeywordRepository.findByItemKeyword(itemKeyword).orElseThrow();

        assertThat(itemSearchKeyword.getItemSearchCount()).isEqualTo(1);
        assertThat(itemSearchKeyword.getItemKeyword()).isEqualTo(itemKeyword);
    }
}