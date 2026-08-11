package com.forgeos.tool.application;

import com.forgeos.tool.domain.AuthorizationDecision;
import com.forgeos.tool.domain.ToolApproval;
import com.forgeos.tool.domain.ToolRequest;

public interface ToolAuthorizationEngine {
    AuthorizationDecision evaluate(ToolRequest request);
    void registerApproval(ToolApproval approval);
}
