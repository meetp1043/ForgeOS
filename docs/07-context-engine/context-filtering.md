# Context Filtering

Filtering is the mechanism by which irrelevant, unauthorized, or dangerous Context Candidates are removed from the pipeline *before* they consume ranking compute or token budgets.

## The Filtering Pipeline

Filtering must occur in a strict, sequential order to guarantee security and efficiency:

### 1. Security Filtering (Hard Boundary)
Removes secrets, credentials, and artifacts with restricted classifications.

### 2. Tenant Filtering (Hard Boundary)
Ensures `Candidate.TenantID == Request.TenantID`. Dropping this filter constitutes a critical data breach.

### 3. Project Filtering (Hard Boundary)
Ensures candidates belong to the active project, unless explicit cross-project reading is authorized.

### 4. Workspace Filtering
Restricts code retrieval to the active developer environment (e.g., the correct Git branch or ephemeral environment).

### 5. Permission Filtering (Agent Role)
Evaluates if the specific Agent Role (e.g., `QA_ENGINEER`) is authorized to view the candidate type (e.g., `HR_DATABASE_SCHEMA`).

### 6. Relevance Filtering
Drops candidates that fall below a minimum semantic or structural relevance threshold, preventing context pollution.

### 7. Freshness Filtering
Drops candidates that are demonstrably stale (e.g., a test report from three weeks ago when the code was updated today).

### 8. Duplication Filtering
Removes identical chunks of information retrieved from different sources (e.g., the same function retrieved via Git and via a tool output).

## Why Order Matters
Security and Tenant filtering *must* happen first. If Relevance Filtering occurred first, the engine might spend expensive vector search compute ranking a highly relevant API key belonging to another tenant, only to filter it out later (or worse, accidentally leak it if the security filter fails).
