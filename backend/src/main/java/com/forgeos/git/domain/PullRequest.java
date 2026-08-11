package com.forgeos.git.domain;

import java.util.UUID;

public class PullRequest {
    private UUID pullRequestId;
    private UUID repositoryId;
    private String sourceBranch;
    private String targetBranch;
    private String status; // DRAFT, OPEN, MERGED
    private boolean ciPassing;
    private boolean securityPassing;
    private boolean humanApproved;

    public UUID getPullRequestId() { return pullRequestId; }
    public void setPullRequestId(UUID pullRequestId) { this.pullRequestId = pullRequestId; }
    public UUID getRepositoryId() { return repositoryId; }
    public void setRepositoryId(UUID repositoryId) { this.repositoryId = repositoryId; }
    public String getSourceBranch() { return sourceBranch; }
    public void setSourceBranch(String sourceBranch) { this.sourceBranch = sourceBranch; }
    public String getTargetBranch() { return targetBranch; }
    public void setTargetBranch(String targetBranch) { this.targetBranch = targetBranch; }
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
    public boolean isCiPassing() { return ciPassing; }
    public void setCiPassing(boolean ciPassing) { this.ciPassing = ciPassing; }
    public boolean isSecurityPassing() { return securityPassing; }
    public void setSecurityPassing(boolean securityPassing) { this.securityPassing = securityPassing; }
    public boolean isHumanApproved() { return humanApproved; }
    public void setHumanApproved(boolean humanApproved) { this.humanApproved = humanApproved; }
}
