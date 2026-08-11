package com.forgeos.git.domain;

public class Branch {
    private String branchName;
    private String repositoryId;
    private String baseBranch;
    private String currentCommit;

    public String getBranchName() { return branchName; }
    public void setBranchName(String branchName) { this.branchName = branchName; }
    public String getRepositoryId() { return repositoryId; }
    public void setRepositoryId(String repositoryId) { this.repositoryId = repositoryId; }
    public String getBaseBranch() { return baseBranch; }
    public void setBaseBranch(String baseBranch) { this.baseBranch = baseBranch; }
    public String getCurrentCommit() { return currentCommit; }
    public void setCurrentCommit(String currentCommit) { this.currentCommit = currentCommit; }
}
