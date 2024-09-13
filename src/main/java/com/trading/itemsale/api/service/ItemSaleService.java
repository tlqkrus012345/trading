package com.trading.itemsale.api.service;

import com.trading.itemsale.domain.ItemSaleInfo;
import com.trading.itemsale.domain.ItemSaleInfoStatus;
import com.trading.itemsale.domain.ItemSaleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional(readOnly = true)
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

    public List<ItemSaleResponse> getItemsForSale() {
        List<ItemSaleInfo> itemSaleInfos = itemSaleRepository.findAllByStatusIn(ItemSaleInfoStatus.forSale());

        return itemSaleInfos.stream()
                .map(ItemSaleResponse::from)
                .toList();
    }

    @Transactional
    public void reduceQuantity(Long itemSaleId, int totalQuantity) {
        ItemSaleInfo itemSaleInfo = itemSaleRepository.findById(itemSaleId)
                .orElseThrow(() -> new IllegalArgumentException("잘못된 판매 등록 id: " + itemSaleId));

        itemSaleInfo.reduceQuantity(totalQuantity);
    }
}
