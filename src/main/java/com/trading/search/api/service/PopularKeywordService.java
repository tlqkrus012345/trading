package com.trading.search.api.service;

import com.trading.search.domain.ItemSearchKeyword;
import com.trading.search.domain.ItemSearchKeywordRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class PopularKeywordService {

    private final ItemSearchKeywordRepository itemSearchKeywordRepository;

    public List<PopularKeywordResponse> getPopularKeywordList() {
        List<ItemSearchKeyword> itemSearchKeywords = itemSearchKeywordRepository.findPopularItemKeyword();

        return itemSearchKeywords.stream()
                .map(PopularKeywordResponse::from)
                .toList();
    }
}
