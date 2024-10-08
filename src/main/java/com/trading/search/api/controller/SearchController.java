package com.trading.search.api.controller;

import com.trading.search.api.service.ItemSearchResponse;
import com.trading.search.api.service.PopularKeywordResponse;
import com.trading.search.api.service.PopularKeywordService;
import com.trading.search.api.service.SearchFacadeService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class SearchController {

    private final SearchFacadeService searchService;
    private final PopularKeywordService popularKeywordService;

    @GetMapping("/api/v1/search/{keyword}")
    public List<ItemSearchResponse> searchItemKeyword(@PathVariable("keyword") String keyword) {
        return searchService.searchItemKeyword(keyword);
    }

    @GetMapping("/api/v1/search/popular")
    public List<PopularKeywordResponse> getPopularKeywordList() {
        return popularKeywordService.getPopularKeywordList();
    }
}
