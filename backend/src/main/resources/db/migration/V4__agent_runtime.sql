CREATE TABLE agent_definitions (
    id UUID PRIMARY KEY,
    role VARCHAR(50) NOT NULL UNIQUE,
    name VARCHAR(255) NOT NULL,
    description TEXT,
    version VARCHAR(50) NOT NULL,
    status VARCHAR(50) NOT NULL,
    risk_level VARCHAR(50) NOT NULL,
    created_at TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE agent_executions (
    id UUID PRIMARY KEY,
    agent_id UUID NOT NULL,
    agent_version VARCHAR(50) NOT NULL,
    parent_execution_id UUID,
    tenant_id UUID NOT NULL,
    project_id UUID NOT NULL,
    workspace_path TEXT NOT NULL,
    status VARCHAR(50) NOT NULL,
    objective TEXT NOT NULL,
    max_steps INT NOT NULL DEFAULT 50,
    current_step INT NOT NULL DEFAULT 0,
    budget_tokens BIGINT,
    consumed_tokens BIGINT DEFAULT 0,
    created_at TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP,
    started_at TIMESTAMP WITH TIME ZONE,
    completed_at TIMESTAMP WITH TIME ZONE,
    correlation_id VARCHAR(255)
);

CREATE TABLE agent_messages (
    id UUID PRIMARY KEY,
    execution_id UUID NOT NULL,
    role VARCHAR(50) NOT NULL,
    content TEXT NOT NULL,
    tool_call_id VARCHAR(255),
    created_at TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP
);

CREATE INDEX idx_agent_executions_tenant ON agent_executions(tenant_id);
CREATE INDEX idx_agent_executions_parent ON agent_executions(parent_execution_id);
CREATE INDEX idx_agent_messages_execution ON agent_messages(execution_id);
