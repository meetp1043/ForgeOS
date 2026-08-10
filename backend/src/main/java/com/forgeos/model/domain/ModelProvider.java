package com.forgeos.model.domain;

public interface ModelProvider {
    ModelResponse generate(ModelRequest request);
}
