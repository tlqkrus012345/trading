package com.trading.search.domain;

import com.trading.search.api.service.SearchService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.tuple;

@ActiveProfiles("test")
@SpringBootTest
class ItemSearchKeywordRepositoryTest {

    @Autowired
    private ItemSearchKeywordRepository itemSearchKeywordRepository;

    @Autowired
    private SearchService searchService;

    @AfterEach
    void tearDown() {
        itemSearchKeywordRepository.deleteAllInBatch();
    }

    @DisplayName("키워드를 통해 itemSearchKeyword 조회한다")
    @Test
    void findByItemKeyword() {
        String itemKeyword = "test";

        ItemSearchKeyword itemSearchKeyword = ItemSearchKeyword.create(itemKeyword);

        itemSearchKeywordRepository.save(itemSearchKeyword);

        Optional<ItemSearchKeyword> savedItemSearchKeyword = itemSearchKeywordRepository.findByItemKeyword(itemKeyword);

        assertThat(savedItemSearchKeyword).isPresent();
        assertThat(savedItemSearchKeyword.get().getItemKeyword()).isEqualTo(itemKeyword);
    }

    @DisplayName("가장 많이 검색된 키워드 3개를 조회한다.")
    @Test
    void findPopularItemKeyword() {
        Long searchCount1 = 4L;
        Long searchCount2 = 3L;
        Long searchCount3 = 2L;
        keywordSearch("레어", searchCount1);
        keywordSearch("일반", searchCount2);
        keywordSearch("에픽", searchCount3);

        List<ItemSearchKeyword> popularItemKeywords = itemSearchKeywordRepository.findPopularItemKeyword();

        assertThat(popularItemKeywords).hasSize(3)
                .extracting("itemKeyword", "itemSearchCount")
                .containsExactly(
                        tuple("레어", searchCount1),
                        tuple("일반", searchCount2),
                        tuple("에픽", searchCount3)
                );
        assertThat(popularItemKeywords.get(0).getItemKeyword()).isEqualTo("레어");
        assertThat(popularItemKeywords.get(1).getItemKeyword()).isEqualTo("일반");
    }

    @DisplayName("동일한 카운트이면 한글 순으로 정렬된다")
    @Test
    void findPopularItemKeyword2() {
        Long searchCount1 = 2L;
        Long searchCount2 = 2L;
        Long searchCount3 = 2L;
        keywordSearch("나다", searchCount1);
        keywordSearch("가나", searchCount2);
        keywordSearch("다가", searchCount3);

        List<ItemSearchKeyword> popularItemKeywords = itemSearchKeywordRepository.findPopularItemKeyword();

        assertThat(popularItemKeywords).hasSize(3)
                .extracting("itemKeyword", "itemSearchCount")
                .containsExactly(
                        tuple("가나", searchCount1),
                        tuple("나다", searchCount2),
                        tuple("다가", searchCount3)
                );
        assertThat(popularItemKeywords.get(0).getItemKeyword()).isEqualTo("가나");
        assertThat(popularItemKeywords.get(1).getItemKeyword()).isEqualTo("나다");
        assertThat(popularItemKeywords.get(2).getItemKeyword()).isEqualTo("다가");
    }

    private void keywordSearch(String keyword, Long count) {
        for (int i = 0; i < count; i++) {
            searchService.searchItemKeyword(keyword);
        }
    }
}