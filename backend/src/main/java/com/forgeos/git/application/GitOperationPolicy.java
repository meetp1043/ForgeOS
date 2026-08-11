package com.forgeos.git.application;

import java.util.List;

public class GitOperationPolicy {
    private boolean forcePushAllowed;
    private List<String> protectedBranches;
    private boolean requireHumanApproval;

    public GitOperationPolicy(boolean forcePushAllowed, List<String> protectedBranches, boolean requireHumanApproval) {
        this.forcePushAllowed = forcePushAllowed;
        this.protectedBranches = protectedBranches;
        this.requireHumanApproval = requireHumanApproval;
    }

    public boolean isForcePushAllowed() { return forcePushAllowed; }
    public List<String> getProtectedBranches() { return protectedBranches; }
    public boolean isRequireHumanApproval() { return requireHumanApproval; }
}
