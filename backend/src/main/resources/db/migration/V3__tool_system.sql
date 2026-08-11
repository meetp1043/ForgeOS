CREATE TABLE tool_definitions (
    id UUID PRIMARY KEY,
    name VARCHAR(255) NOT NULL UNIQUE,
    description TEXT,
    version VARCHAR(50) NOT NULL,
    category VARCHAR(50) NOT NULL,
    risk_level VARCHAR(50) NOT NULL,
    required_permission VARCHAR(50) NOT NULL,
    timeout_ms BIGINT NOT NULL,
    status VARCHAR(50) NOT NULL,
    created_at TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE tool_executions (
    id UUID PRIMARY KEY,
    tool_id VARCHAR(255) NOT NULL,
    tool_version VARCHAR(50) NOT NULL,
    actor_id UUID NOT NULL,
    actor_type VARCHAR(50) NOT NULL,
    tenant_id UUID,
    project_id UUID,
    workspace_path TEXT,
    status VARCHAR(50) NOT NULL,
    error_code VARCHAR(50),
    error_message TEXT,
    started_at TIMESTAMP WITH TIME ZONE NOT NULL,
    completed_at TIMESTAMP WITH TIME ZONE,
    duration_ms BIGINT,
    correlation_id VARCHAR(255)
);

CREATE INDEX idx_tool_executions_tenant ON tool_executions(tenant_id);
CREATE INDEX idx_tool_executions_tool ON tool_executions(tool_id, status);
