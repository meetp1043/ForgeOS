# Requirements Integration

Requirements dictate *what* the software must do. Integrating them correctly prevents the AI from inventing features or ignoring critical business logic.

## Prioritization of Requirements
Requirements context must prioritize:
1. **Approved Requirements**: Officially signed-off product specifications.
2. **Current Requirements**: Active, work-in-progress requirements tied to the task.
3. **Acceptance Criteria**: The boolean conditions for success.
4. **Business Rules**: Specific domain logic (e.g., "Users under 18 cannot register").
5. **Constraints**: Non-functional requirements (e.g., "Must support 10k RPS").

## Handling Superseded Requirements
Superseded or deprecated requirements must either be explicitly excluded from the context package, or (if included for historical context) **clearly and loudly marked as superseded**. The agent must never mistake an old requirement for active policy.
