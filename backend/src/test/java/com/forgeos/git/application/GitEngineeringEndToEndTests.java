package com.forgeos.git.application;

import com.forgeos.git.domain.*;
import com.forgeos.git.infrastructure.GitEngine;
import com.forgeos.git.infrastructure.GitHubAdapter;
import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

public class GitEngineeringEndToEndTests {

    @Test
    void testEndToEndDeterministicWorkflow() {
        // 1. Setup & Policy
        WorkspaceManager workspaceManager = new WorkspaceManagerImpl();
        MergeGateEvaluator mergeGate = new MergeGateEvaluatorImpl();
        GitOperationPolicy policy = new GitOperationPolicy(false, List.of("master"), true);

        // 2. Register Repository
        Repository repo = new Repository();
        repo.setRepositoryId(UUID.randomUUID());
        repo.setTenantId(UUID.randomUUID());
        repo.setProvider("GITHUB");
        repo.setFullName("meetp1043/ForgeOS");

        // 3. Create Workspace
        Workspace workspace = workspaceManager.createWorkspace(repo, UUID.randomUUID());
        assertNotNull(workspace.getLocalPath());
        
        // 4. Test Workspace Security (Path Traversal attempt)
        assertThrows(SecurityException.class, () -> {
            workspaceManager.validateWorkspacePath(workspace, "../../etc/passwd");
        }, "Path traversal should be blocked!");

        // 5. Mock Git Engine operations
        GitEngine stubGitEngine = new GitEngine() {
            @Override public void cloneRepository(Workspace w, String url) {}
            @Override public void createBranch(Workspace w, String branchName) {}
            @Override public ChangeSet diff(Workspace w) {
                ChangeSet cs = new ChangeSet();
                cs.setChangeSetId(UUID.randomUUID());
                cs.setFilesChanged(List.of("src/main/java/Safe.java"));
                return cs;
            }
            @Override public void commit(Workspace w, String msg, ChangeSet cs) {}
            @Override public void push(Workspace w, String remote, String branch) {}
        };
        
        // 6. Execute git operations
        stubGitEngine.cloneRepository(workspace, "https://github.com/meetp1043/ForgeOS.git");
        stubGitEngine.createBranch(workspace, "feature/add-safe-code");
        ChangeSet diff = stubGitEngine.diff(workspace);
        
        // 7. Secret Scanner validation
        SecretScanner secretScanner = (w, cs) -> {
            if (cs.getFilesChanged().contains(".env")) {
                throw new SecurityException("Secret Detected!");
            }
        };
        
        assertDoesNotThrow(() -> secretScanner.scan(workspace, diff));
        
        // 8. Commit & Push
        stubGitEngine.commit(workspace, "feat: add safe code", diff);
        stubGitEngine.push(workspace, "origin", "feature/add-safe-code");

        // 9. Mock PR Creation
        GitHubAdapter stubGitHub = new GitHubAdapter() {
            @Override
            public PullRequest createPullRequest(String repo, String source, String target, String title, String body) {
                PullRequest pr = new PullRequest();
                pr.setPullRequestId(UUID.randomUUID());
                pr.setSourceBranch(source);
                pr.setTargetBranch(target);
                // Simulate CI passing but Human Approval missing initially
                pr.setCiPassing(true);
                pr.setSecurityPassing(true);
                pr.setHumanApproved(false); 
                return pr;
            }
            @Override public void mergePullRequest(PullRequest pr) {}
        };

        PullRequest pr = stubGitHub.createPullRequest(repo.getFullName(), "feature/add-safe-code", "master", "Add safe code", "Desc");
        
        // 10. Evaluate Merge Gate - Should Fail
        assertEquals(MergeGateStatus.BLOCKED, mergeGate.evaluate(pr, policy), "Should block without human approval");
        
        // 11. Human Approves
        pr.setHumanApproved(true);
        
        // 12. Evaluate Merge Gate - Should Pass
        assertEquals(MergeGateStatus.PASSED, mergeGate.evaluate(pr, policy), "Should pass after human approval");
        
        // 13. Execute Merge
        stubGitHub.mergePullRequest(pr);
    }
}
