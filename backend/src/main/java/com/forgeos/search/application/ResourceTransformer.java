package com.forgeos.search.application;

import com.forgeos.search.domain.SearchDocument;

/**
 * Interface for translating domain resources into generic SearchDocuments.
 * Specific mappers (e.g. ArtifactSearchMapper) should implement this.
 */
public interface ResourceTransformer<T> {
    
    SearchDocument transform(T resource);
    
    boolean supports(Class<?> clazz);
}
