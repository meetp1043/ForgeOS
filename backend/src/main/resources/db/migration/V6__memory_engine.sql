CREATE TABLE memory_candidates (
    id UUID PRIMARY KEY,
    tenant_id UUID,
    project_id UUID,
    scope VARCHAR(50) NOT NULL,
    type VARCHAR(50) NOT NULL,
    title VARCHAR(255) NOT NULL,
    content TEXT NOT NULL,
    source VARCHAR(100) NOT NULL,
    source_reference VARCHAR(255),
    confidence VARCHAR(50),
    importance VARCHAR(50),
    security_classification VARCHAR(50) NOT NULL,
    created_at TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE memories (
    id UUID PRIMARY KEY,
    tenant_id UUID,
    organization_id UUID,
    project_id UUID,
    workspace_id UUID,
    scope VARCHAR(50) NOT NULL,
    type VARCHAR(50) NOT NULL,
    title VARCHAR(255) NOT NULL,
    summary TEXT,
    content TEXT NOT NULL,
    source VARCHAR(100) NOT NULL,
    source_reference VARCHAR(255),
    created_by UUID,
    importance VARCHAR(50),
    confidence VARCHAR(50),
    status VARCHAR(50) NOT NULL,
    authority VARCHAR(50) NOT NULL,
    security_classification VARCHAR(50) NOT NULL,
    version INT NOT NULL DEFAULT 1,
    metadata JSONB,
    created_at TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP,
    expires_at TIMESTAMP WITH TIME ZONE
);

CREATE TABLE memory_conflicts (
    id UUID PRIMARY KEY,
    memory_a_id UUID NOT NULL REFERENCES memories(id),
    memory_b_id UUID NOT NULL REFERENCES memories(id),
    reason TEXT NOT NULL,
    scope VARCHAR(50) NOT NULL,
    severity VARCHAR(50) NOT NULL,
    status VARCHAR(50) NOT NULL,
    resolution TEXT,
    created_at TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP
);

CREATE INDEX idx_memories_tenant ON memories(tenant_id);
CREATE INDEX idx_memories_project ON memories(project_id);
CREATE INDEX idx_memories_scope ON memories(scope);
CREATE INDEX idx_memories_type ON memories(type);
CREATE INDEX idx_memories_status ON memories(status);
