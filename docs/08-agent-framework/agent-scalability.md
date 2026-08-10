# Agent Scalability

The Agent Framework must support executing thousands of agents concurrently.

## Stateless Execution
The `Agent Instance` is logically stateful (it represents an ongoing task), but the `AgentExecutionOrchestrator` must be horizontally scalable and stateless.
- Active agent states are persisted to a fast datastore (e.g., Redis).
- If the ForgeOS node running "Agent A" crashes, another node can pick up the execution by reading the state from Redis, re-provisioning the tool sandbox, and resuming the LLM API call.

## Tool Sandbox Scaling
The heaviest component of agent execution is not the LLM API call, but the Tool Sandbox (e.g., running `mvn clean install` for a Java project).
- Sandboxes must be scheduled on a scalable compute cluster (e.g., Kubernetes).
- The Framework merely holds an async pointer to the running pod.

## Model Rate Limits
To prevent 5,000 agents from simultaneously hitting the OpenAI rate limit:
- The Model Router implements global token buckets and queuing.
- Agents waiting for model capacity enter the `WAITING` state and yield their threads.
