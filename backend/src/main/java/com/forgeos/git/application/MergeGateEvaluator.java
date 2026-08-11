package com.forgeos.git.application;

import com.forgeos.git.domain.MergeGateStatus;
import com.forgeos.git.domain.PullRequest;

public interface MergeGateEvaluator {
    MergeGateStatus evaluate(PullRequest pr, GitOperationPolicy policy);
}
