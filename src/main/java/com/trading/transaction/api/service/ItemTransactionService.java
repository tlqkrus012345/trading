package com.trading.transaction.api.service;

import com.trading.transaction.domain.ItemTransaction;
import com.trading.transaction.domain.ItemTransactionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@RequiredArgsConstructor
@Service
public class ItemTransactionService {

    private final ItemTransactionRepository itemTransactionRepository;

    @Transactional
    public ItemTransactionResponse createItemTransaction(ItemTransactionRequest request, LocalDateTime now) {
        ItemTransaction itemTransaction = ItemTransaction.create(request, now);

        return ItemTransactionResponse.from(itemTransactionRepository.save(itemTransaction));
    }
}
