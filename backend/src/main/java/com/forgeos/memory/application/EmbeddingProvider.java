package com.forgeos.memory.application;

import java.util.List;

public interface EmbeddingProvider {
    /**
     * Returns a float array representing the semantic embedding of the input text.
     */
    List<Float> embed(String text);
    
    /**
     * Returns the dimension size of the embedding model (e.g. 1536 for OpenAI ada-002).
     */
    int getDimension();
}
