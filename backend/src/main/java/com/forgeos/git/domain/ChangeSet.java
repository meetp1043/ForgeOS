package com.forgeos.git.domain;

import java.util.List;
import java.util.UUID;

public class ChangeSet {
    private UUID changeSetId;
    private UUID workspaceId;
    private String baseCommit;
    private String headCommit;
    private List<String> filesChanged;
    private int insertions;
    private int deletions;

    public UUID getChangeSetId() { return changeSetId; }
    public void setChangeSetId(UUID changeSetId) { this.changeSetId = changeSetId; }
    public UUID getWorkspaceId() { return workspaceId; }
    public void setWorkspaceId(UUID workspaceId) { this.workspaceId = workspaceId; }
    public String getBaseCommit() { return baseCommit; }
    public void setBaseCommit(String baseCommit) { this.baseCommit = baseCommit; }
    public String getHeadCommit() { return headCommit; }
    public void setHeadCommit(String headCommit) { this.headCommit = headCommit; }
    public List<String> getFilesChanged() { return filesChanged; }
    public void setFilesChanged(List<String> filesChanged) { this.filesChanged = filesChanged; }
    public int getInsertions() { return insertions; }
    public void setInsertions(int insertions) { this.insertions = insertions; }
    public int getDeletions() { return deletions; }
    public void setDeletions(int deletions) { this.deletions = deletions; }
}
