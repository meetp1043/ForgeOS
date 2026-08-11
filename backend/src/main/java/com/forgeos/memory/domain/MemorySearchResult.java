package com.forgeos.memory.domain;

public class MemorySearchResult {
    private Memory memory;
    private double score;
    private String matchType;
    private String rankingReason;

    public MemorySearchResult(Memory memory, double score, String matchType, String rankingReason) {
        this.memory = memory;
        this.score = score;
        this.matchType = matchType;
        this.rankingReason = rankingReason;
    }

    public Memory getMemory() { return memory; }
    public void setMemory(Memory memory) { this.memory = memory; }
    public double getScore() { return score; }
    public void setScore(double score) { this.score = score; }
    public String getMatchType() { return matchType; }
    public void setMatchType(String matchType) { this.matchType = matchType; }
    public String getRankingReason() { return rankingReason; }
    public void setRankingReason(String rankingReason) { this.rankingReason = rankingReason; }
}
