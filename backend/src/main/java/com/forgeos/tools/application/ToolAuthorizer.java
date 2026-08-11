package com.forgeos.tools.application;

import com.forgeos.tools.domain.ToolDefinition;
import com.forgeos.tools.domain.ToolErrorCode;
import com.forgeos.tools.domain.ToolRequest;
import com.forgeos.tools.domain.ToolRiskLevel;
import com.forgeos.tools.domain.exception.ToolAuthorizationException;
import org.springframework.stereotype.Component;

@Component
public class ToolAuthorizer {

    public void authorize(ToolRequest request, ToolDefinition toolDef) {
        // 1. Identify Actor (Phase 11 integration point)
        if (request.getActorId() == null) {
            throw new ToolAuthorizationException(ToolErrorCode.UNAUTHORIZED, "Actor ID is required");
        }

        // 2. Identify Tenant (Phase 11 integration point)
        if (request.getTenantId() == null) {
            throw new ToolAuthorizationException(ToolErrorCode.FORBIDDEN, "Tenant context is required");
        }

        // 3. Risk Assessment
        if (toolDef.getRiskLevel() == ToolRiskLevel.CRITICAL) {
            throw new ToolAuthorizationException(ToolErrorCode.ENVIRONMENT_NOT_ALLOWED, 
                    "CRITICAL tools are not permitted in this environment without an isolated Sandbox (Phase 19).");
        }

        // 4. Permissions Check
        // In a complete implementation, this checks the OrganizationMembership or Agent roles.
        // For architectural proof, we verify the required permission is declared.
        if (toolDef.getRequiredPermission() == null) {
            throw new ToolAuthorizationException(ToolErrorCode.FORBIDDEN, "Tool defines no required permission.");
        }

        // 5. Environment Rules
        if (toolDef.getRiskLevel() == ToolRiskLevel.HIGH && request.getEnvironment() == com.forgeos.tools.domain.ExecutionEnvironment.PRODUCTION) {
             throw new ToolAuthorizationException(ToolErrorCode.APPROVAL_REQUIRED, "HIGH risk tools in PRODUCTION require human approval.");
        }
    }
}
