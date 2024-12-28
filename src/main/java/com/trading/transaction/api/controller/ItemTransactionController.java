package com.trading.transaction.api.controller;

import com.trading.transaction.api.service.*;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.List;

@RequiredArgsConstructor
@RestController
public class ItemTransactionController {

    private final PopularItemService popularItemService;
    private final ItemTransactionService itemTransactionService;

    @GetMapping("/api/v1/itr/popular")
    public List<PopularItemResponse> getPopularItems() {
        return popularItemService.getPopularItems();
    }

    @PostMapping("/api/v1/itr")
    public ItemTransactionResponse createItemTransaction(@RequestBody ItemTransactionRequest request) {
        LocalDateTime now = LocalDateTime.now();
        return itemTransactionService.createItemTransaction(request, now);
    }
}
