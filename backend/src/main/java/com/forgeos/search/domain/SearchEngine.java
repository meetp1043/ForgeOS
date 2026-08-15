package com.forgeos.search.domain;

import java.util.List;

/**
 * Search engine abstraction.
 */
public interface SearchEngine {
    
    /**
     * Executes a search query.
     */
    List<SearchResult> search(SearchRequest request);

    /**
     * Executes an autocomplete prefix query.
     */
    List<SearchResult> autocomplete(SearchRequest request);

    /**
     * Indexes a single document.
     */
    void index(SearchDocument document);

    /**
     * Bulk indexes multiple documents.
     */
    void bulkIndex(List<SearchDocument> documents);

    /**
     * Updates an existing document.
     */
    void update(SearchDocument document);

    /**
     * Deletes a document by ID.
     */
    void delete(String documentId);

    /**
     * Executes a full reindex (schema recreation and bulk data load).
     */
    void reindex();

    /**
     * Checks the health of the search engine.
     */
    boolean health();
}
