package com.forgeos.search.infrastructure;

import org.springframework.ai.embedding.EmbeddingModel;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class EmbeddingClientAdapter {

    private final EmbeddingModel embeddingModel;

    public EmbeddingClientAdapter(EmbeddingModel embeddingModel) {
        this.embeddingModel = embeddingModel;
    }

    public float[] generateEmbedding(String text) {
        // Use Spring AI's unified embedding model abstraction (Phase 24 Model Gateway)
        return embeddingModel.embed(text);
    }
    
    public List<float[]> generateEmbeddings(List<String> texts) {
        // Batch embedding generation
        // Note: Spring AI API might return a list of lists depending on version.
        // This is a placeholder for the actual batch execution logic.
        return texts.stream().map(this::generateEmbedding).toList();
    }
}
