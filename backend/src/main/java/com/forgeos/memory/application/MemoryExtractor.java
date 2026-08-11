package com.forgeos.memory.application;

import com.forgeos.memory.domain.MemoryCandidate;
import java.util.List;

public interface MemoryExtractor {
    /**
     * Extracts potential MemoryCandidates from unstructured text (e.g. conversation, documents).
     * Does NOT automatically activate them as durable memory.
     */
    List<MemoryCandidate> extract(String text, String sourceReference);
}
