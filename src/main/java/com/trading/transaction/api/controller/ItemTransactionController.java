package com.trading.transaction.api.controller;

import com.trading.transaction.api.service.PopularItemResponse;
import com.trading.transaction.api.service.PopularItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class ItemTransactionController {

    private final PopularItemService popularItemService;

    @GetMapping("/api/v1/itr/popular")
    public List<PopularItemResponse> getPopularItems() {
        return popularItemService.getPopularItems();
    }
}
