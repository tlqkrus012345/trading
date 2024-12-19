package com.trading.transaction.api.service;

import com.trading.transaction.domain.ItemTransactionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Service
public class PopularItemService {

    private final ItemTransactionRepository itemTransactionRepository;
    private final static String POPULAR_ITEM = "POPULAR_ITEM";

    @Cacheable(POPULAR_ITEM)
    @Transactional(readOnly = true)
    public List<PopularItemResponse> getPopularItems() {
        return itemTransactionRepository.findPopularItems();
    }
}
