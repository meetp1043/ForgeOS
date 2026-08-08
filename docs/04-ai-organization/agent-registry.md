# Agent Registry

The Agent Registry is the single source of truth for all agent roles available in the ForgeOS platform. It is a logical database table (or set of tables) within the ForgeOS Core API.

## Registry Entry Schema

Each registered agent contains the following metadata:

| Field | Type | Description |
| :--- | :--- | :--- |
| `agent_id` | UUID | Unique identifier for the agent role definition. |
| `role_name` | String | Human-readable name (e.g., "Backend Engineer"). |
| `taxonomy` | Enum | Category: `EXECUTIVE`, `MANAGER`, `SPECIALIST`, `REVIEWER`, `ADVISOR`. |
| `layer` | Enum | Hierarchy: `EXECUTIVE`, `MANAGEMENT`, `ENGINEERING`, `QUALITY`, `OPERATIONS`, `SUPPORT`. |
| `version` | SemVer | Current active version of the role definition. |
| `status` | Enum | `ACTIVE`, `DEPRECATED`, `RETIRED`, `DRAFT`. |
| `capabilities` | List | Tags describing what the agent can do (e.g., `JAVA_CODING`, `SQL_DESIGN`). |
| `permissions` | List | Granted RBAC permissions (e.g., `FILE_WRITE`, `GIT_COMMIT`). |
| `tools` | List | Tool IDs the agent is authorized to invoke. |
| `model_policy` | Object | Allowed LLM models and fallback chain. |
| `parent_role` | UUID (nullable) | The role this agent reports to in the hierarchy. |
| `allowed_children` | List (UUID) | Roles this agent is allowed to delegate tasks to. |
| `evaluation_metrics` | Object | The configured KPIs for this role. |
| `security_classification` | Enum | `LOW`, `MEDIUM`, `HIGH`, `CRITICAL`. |
| `created_at` | Timestamp | When the role was first registered. |
| `updated_at` | Timestamp | When the role was last modified. |

## Registry Operations
- **Register**: Add a new agent role (requires Administrator approval).
- **Update**: Modify an existing role (creates a new version).
- **Deprecate**: Mark a role as deprecated (no new assignments).
- **Retire**: Archive the role permanently.
- **Query**: The Orchestrator queries the registry to determine which agent to assign for a given task based on capabilities and availability.
