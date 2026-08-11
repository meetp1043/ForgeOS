CREATE TABLE workflow_definitions (
    id UUID PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    description TEXT,
    version VARCHAR(50) NOT NULL,
    status VARCHAR(50) NOT NULL,
    tenant_id UUID,
    created_at TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE task_definitions (
    id UUID PRIMARY KEY,
    workflow_definition_id UUID NOT NULL REFERENCES workflow_definitions(id),
    name VARCHAR(255) NOT NULL,
    task_type VARCHAR(50) NOT NULL,
    agent_role VARCHAR(50),
    dependencies JSONB,
    failure_policy VARCHAR(50) NOT NULL,
    priority VARCHAR(50) NOT NULL,
    created_at TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE workflow_executions (
    id UUID PRIMARY KEY,
    workflow_definition_id UUID NOT NULL REFERENCES workflow_definitions(id),
    workflow_version VARCHAR(50) NOT NULL,
    tenant_id UUID NOT NULL,
    project_id UUID NOT NULL,
    status VARCHAR(50) NOT NULL,
    objective TEXT NOT NULL,
    budget_tokens BIGINT,
    created_at TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP,
    started_at TIMESTAMP WITH TIME ZONE,
    completed_at TIMESTAMP WITH TIME ZONE
);

CREATE TABLE task_executions (
    id UUID PRIMARY KEY,
    workflow_execution_id UUID NOT NULL REFERENCES workflow_executions(id),
    task_definition_id UUID NOT NULL REFERENCES task_definitions(id),
    agent_execution_id UUID,
    status VARCHAR(50) NOT NULL,
    created_at TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP,
    started_at TIMESTAMP WITH TIME ZONE,
    completed_at TIMESTAMP WITH TIME ZONE,
    error_message TEXT
);

CREATE INDEX idx_workflow_executions_tenant ON workflow_executions(tenant_id);
CREATE INDEX idx_task_executions_workflow ON task_executions(workflow_execution_id);
CREATE INDEX idx_task_definitions_workflow ON task_definitions(workflow_definition_id);
