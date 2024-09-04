package com.trading.search.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@ActiveProfiles("test")
@DataJpaTest
class ItemSearchKeywordRepositoryTest {

    @Autowired
    private ItemSearchKeywordRepository itemSearchKeywordRepository;

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
}