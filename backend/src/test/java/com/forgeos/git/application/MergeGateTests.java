package com.forgeos.git.application;

import com.forgeos.git.domain.MergeGateStatus;
import com.forgeos.git.domain.PullRequest;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class MergeGateTests {

    @Test
    void testMergeBlockedWhenHumanApprovalRequiredButMissing() {
        MergeGateEvaluator evaluator = new MergeGateEvaluatorImpl();
        
        GitOperationPolicy policy = new GitOperationPolicy(false, List.of("master", "main"), true);
        
        PullRequest pr = new PullRequest();
        pr.setPullRequestId(UUID.randomUUID());
        pr.setSourceBranch("feature/123");
        pr.setTargetBranch("master");
        pr.setCiPassing(true);
        pr.setSecurityPassing(true);
        pr.setHumanApproved(false); // Missing!
        
        MergeGateStatus status = evaluator.evaluate(pr, policy);
        
        assertEquals(MergeGateStatus.BLOCKED, status, "PR should be blocked without human approval on a protected branch.");
    }
    
    @Test
    void testMergePassedWhenAllConditionsMet() {
        MergeGateEvaluator evaluator = new MergeGateEvaluatorImpl();
        
        GitOperationPolicy policy = new GitOperationPolicy(false, List.of("master", "main"), true);
        
        PullRequest pr = new PullRequest();
        pr.setPullRequestId(UUID.randomUUID());
        pr.setSourceBranch("feature/123");
        pr.setTargetBranch("master");
        pr.setCiPassing(true);
        pr.setSecurityPassing(true);
        pr.setHumanApproved(true); // Approved!
        
        MergeGateStatus status = evaluator.evaluate(pr, policy);
        
        assertEquals(MergeGateStatus.PASSED, status, "PR should pass when all checks and approvals are met.");
    }
}
