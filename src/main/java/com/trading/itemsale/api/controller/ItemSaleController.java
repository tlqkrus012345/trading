package com.trading.itemsale.api.controller;

import com.trading.itemsale.api.service.ItemSaleResponse;
import com.trading.itemsale.api.service.ItemSaleService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class ItemSaleController {

    private final ItemSaleService itemSaleService;

    @GetMapping("api/v1/item-sale/display")
    public List<ItemSaleResponse> getItemsForDisplay() {
        return itemSaleService.getItemsForDisplay();
    }

    @GetMapping("api/v1/item-sale/sale")
    public List<ItemSaleResponse> getItemsForSale() {
        return itemSaleService.getItemsForSale();
    }
}
