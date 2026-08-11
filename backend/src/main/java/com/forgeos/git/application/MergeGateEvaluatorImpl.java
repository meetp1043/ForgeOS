package com.forgeos.git.application;

import com.forgeos.git.domain.MergeGateStatus;
import com.forgeos.git.domain.PullRequest;
import org.springframework.stereotype.Service;

@Service
public class MergeGateEvaluatorImpl implements MergeGateEvaluator {

    @Override
    public MergeGateStatus evaluate(PullRequest pr, GitOperationPolicy policy) {
        if (!pr.isCiPassing()) {
            return MergeGateStatus.BLOCKED;
        }
        
        if (!pr.isSecurityPassing()) {
            return MergeGateStatus.BLOCKED;
        }
        
        if (policy.isRequireHumanApproval() && !pr.isHumanApproved()) {
            return MergeGateStatus.BLOCKED;
        }
        
        // Ensure no direct push to protected branches without PR approval if required
        if (policy.getProtectedBranches().contains(pr.getTargetBranch()) && policy.isRequireHumanApproval() && !pr.isHumanApproved()) {
            return MergeGateStatus.BLOCKED;
        }
        
        return MergeGateStatus.PASSED;
    }
}
