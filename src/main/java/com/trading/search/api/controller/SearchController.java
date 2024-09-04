package com.trading.search.api.controller;

import com.trading.search.api.service.ItemSearchResponse;
import com.trading.search.api.service.SearchService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class SearchController {

    private final SearchService searchService;

    @GetMapping("/api/v1/search/{keyword}")
    public List<ItemSearchResponse> searchItemKeyword(@PathVariable("keyword") String keyword) {
        return searchService.searchItemKeyword(keyword);
    }
}
