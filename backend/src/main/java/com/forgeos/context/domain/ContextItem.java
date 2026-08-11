package com.forgeos.context.domain;

public class ContextItem {
    private String sourceId;
    private String sourceType;
    private String authority;
    private double relevanceScore;
    private String content;

    public ContextItem(String sourceId, String sourceType, String authority, double relevanceScore, String content) {
        this.sourceId = sourceId;
        this.sourceType = sourceType;
        this.authority = authority;
        this.relevanceScore = relevanceScore;
        this.content = content;
    }

    public String getSourceId() { return sourceId; }
    public String getSourceType() { return sourceType; }
    public String getAuthority() { return authority; }
    public double getRelevanceScore() { return relevanceScore; }
    public String getContent() { return content; }
}
