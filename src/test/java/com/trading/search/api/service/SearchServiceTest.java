package com.trading.search.api.service;

import com.trading.search.domain.ItemSearchKeyword;
import com.trading.search.domain.ItemSearchKeywordRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.orm.ObjectOptimisticLockingFailureException;
import org.springframework.test.context.ActiveProfiles;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import static org.assertj.core.api.Assertions.assertThat;

@ActiveProfiles("test")
@SpringBootTest
class SearchServiceTest {

    @Autowired
    private SearchFacadeService searchFacadeService;

    @Autowired
    private SearchService searchService;

    @Autowired
    private ItemSearchKeywordRepository itemSearchKeywordRepository;

    @AfterEach
    void tearDown() {
        itemSearchKeywordRepository.deleteAllInBatch();
    }

    @DisplayName("특정 키워드가 검색이 되면 카운트가 1 증가한다.")
    @Test
    void searchItemKeyword() {
        String itemKeyword = "강철";

        searchService.searchItemKeyword(itemKeyword);

        ItemSearchKeyword itemSearchKeyword = itemSearchKeywordRepository.findByItemKeyword(itemKeyword).orElseThrow();

        assertThat(itemSearchKeyword.getItemSearchCount()).isEqualTo(1);
        assertThat(itemSearchKeyword.getItemKeyword()).isEqualTo(itemKeyword);
    }

    @DisplayName("특정 키워드가 동시에 2번 검색이 되면 OptimisticLockingFailureException 발생한다.")
    @Test
    void searchItemKeyword2() throws InterruptedException {
        String itemKeyword = "강철";
        itemSearchKeywordRepository.save(ItemSearchKeyword.create(itemKeyword));

        final int count = 2;
        final ExecutorService executorService = Executors.newFixedThreadPool(count);
        final CountDownLatch countDownLatch = new CountDownLatch(count);

        List<Exception> exceptions = Collections.synchronizedList(new ArrayList<>());
        for (int i=0; i<count; i++) {
            executorService.submit(() -> {
                try {
                    searchService.searchItemKeyword(itemKeyword);
                } catch (Exception e) {
                    exceptions.add(e);
                } finally {
                    countDownLatch.countDown();
                }
            });
        }

        countDownLatch.await();

        assertThat(exceptions)
                .hasAtLeastOneElementOfType(ObjectOptimisticLockingFailureException.class);
    }

    @DisplayName("특정 키워드가 동시에 10번 검색이 되면 카운트가 10 증가한다.")
    @Test
    void searchItemKeyword3() throws InterruptedException {
        String itemKeyword = "강철";
        itemSearchKeywordRepository.save(ItemSearchKeyword.create(itemKeyword));

        final int count = 10;
        final ExecutorService executorService = Executors.newFixedThreadPool(count);
        final CountDownLatch countDownLatch = new CountDownLatch(count);

        for (int i=0; i<count; i++) {
            executorService.submit(() -> {
                try {
                    searchFacadeService.searchItemKeyword(itemKeyword);
                } finally {
                    countDownLatch.countDown();
                }
            });
        }

        countDownLatch.await();

        ItemSearchKeyword itemSearchKeyword = itemSearchKeywordRepository.findByItemKeyword(itemKeyword).orElseThrow();
        assertThat(itemSearchKeyword.getItemSearchCount()).isEqualTo(10);
        assertThat(itemSearchKeyword.getItemKeyword()).isEqualTo(itemKeyword);
    }
}