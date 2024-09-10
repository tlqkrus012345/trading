package com.trading.transaction.api.service;

import com.trading.transaction.domain.ItemTransactionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class PopularItemService {

    private final ItemTransactionRepository itemTransactionRepository;

    public List<PopularItemResponse> getPopularItems() {
        return itemTransactionRepository.findPopularItems();
    }
}
