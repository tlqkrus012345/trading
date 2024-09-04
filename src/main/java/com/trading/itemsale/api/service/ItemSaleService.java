package com.trading.itemsale.api.service;

import com.trading.itemsale.domain.ItemSaleInfo;
import com.trading.itemsale.domain.ItemSaleInfoStatus;
import com.trading.itemsale.domain.ItemSaleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class ItemSaleService {

    private final ItemSaleRepository itemSaleRepository;

    public List<ItemSaleResponse> getItemsForDisplay() {
        List<ItemSaleInfo> itemSaleInfos = itemSaleRepository.findAllByStatusIn(ItemSaleInfoStatus.forDisplay());

        return itemSaleInfos.stream()
                .map(ItemSaleResponse::from)
                .toList();
    }
}
