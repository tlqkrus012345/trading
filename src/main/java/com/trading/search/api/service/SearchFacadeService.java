package com.trading.search.api.service;

import lombok.RequiredArgsConstructor;
import org.springframework.orm.ObjectOptimisticLockingFailureException;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class SearchFacadeService {

    private final SearchService searchService;

    public List<ItemSearchResponse> searchItemKeyword(String keyword) {
        final int waitDuration = 50;

        while (true) {
            try {
                return searchService.searchItemKeyword(keyword);
            } catch (ObjectOptimisticLockingFailureException e) {
                try {
                    Thread.sleep(waitDuration);
                } catch (InterruptedException e1) {
                    throw new RuntimeException(e1);
                }
            }
        }
    }
}
