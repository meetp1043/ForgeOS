package com.forgeos.git.infrastructure;

import com.forgeos.git.domain.PullRequest;

public interface GitHubAdapter {
    PullRequest createPullRequest(String repositoryFullName, String sourceBranch, String targetBranch, String title, String body);
    void mergePullRequest(PullRequest pullRequest);
}
