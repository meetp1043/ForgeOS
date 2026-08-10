package com.forgeos.model.domain;

public record ModelResponse(String content, int promptTokens, int completionTokens) {}
