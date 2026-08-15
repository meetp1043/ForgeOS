package com.forgeos.search.api;

import com.forgeos.search.application.SearchService;
import com.forgeos.search.domain.SearchRequest;
import com.forgeos.search.domain.SearchResult;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/search")
public class SearchController {

    private final SearchService searchService;

    public SearchController(SearchService searchService) {
        this.searchService = searchService;
    }

    @PostMapping("/query")
    public ResponseEntity<List<SearchResult>> search(@RequestBody SearchRequest request) {
        return ResponseEntity.ok(searchService.executeSearch(request));
    }

    @GetMapping
    public ResponseEntity<List<SearchResult>> searchGet(@ModelAttribute SearchRequest request) {
        return ResponseEntity.ok(searchService.executeSearch(request));
    }

    @GetMapping("/autocomplete")
    public ResponseEntity<List<SearchResult>> autocomplete(@ModelAttribute SearchRequest request) {
        return ResponseEntity.ok(searchService.executeAutocomplete(request));
    }
}
