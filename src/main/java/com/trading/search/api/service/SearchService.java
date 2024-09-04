package com.trading.search.api.service;

import com.trading.item.domain.Item;
import com.trading.item.domain.ItemRepository;
import com.trading.search.domain.ItemSearchKeyword;
import com.trading.search.domain.ItemSearchKeywordRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Service
public class SearchService {

    private final ItemRepository itemRepository;
    private final ItemSearchKeywordRepository itemSearchKeywordRepository;

    @Transactional
    public List<ItemSearchResponse> searchItemKeyword(String keyword) {
        List<Item> items = itemRepository.findByNameContaining(keyword);

        ItemSearchKeyword itemSearchKeyword = itemSearchKeywordRepository.findByItemKeyword(keyword)
                .orElse(ItemSearchKeyword.create(keyword));
        itemSearchKeyword.increaseItemSearchCnt();
        itemSearchKeywordRepository.save(itemSearchKeyword);

        return items.stream()
                .map(ItemSearchResponse::from)
                .toList();
    }
}
