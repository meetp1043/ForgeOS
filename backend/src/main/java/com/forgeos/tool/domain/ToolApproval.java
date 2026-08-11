package com.forgeos.tool.domain;

import java.util.UUID;

public class ToolApproval {
    private UUID requestId;
    private boolean approved;

    public ToolApproval(UUID requestId, boolean approved) {
        this.requestId = requestId;
        this.approved = approved;
    }

    public UUID getRequestId() { return requestId; }
    public boolean isApproved() { return approved; }
}
