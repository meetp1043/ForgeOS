# Workflow Glossary

| Term | Definition |
| :--- | :--- |
| **Workflow** | A coordinated sequence of steps that accomplishes a software engineering objective. |
| **Workflow Definition** | The immutable, versioned template that describes a class of workflows. |
| **Workflow Instance** | A single, concrete execution of a Workflow Definition, bound to a specific project. |
| **Step** | The smallest unit of work within a workflow. Each step has a type, inputs, outputs, and policies. |
| **Transition** | A directed edge between two steps, defining how execution flows from one step to the next based on triggers and conditions. |
| **Condition** | A boolean expression evaluated during a transition to determine the execution path. |
| **Execution** | The process of running a step: assigning an agent, invoking tools, and producing results. |
| **Retry** | Re-executing a failed step according to a defined policy (max attempts, backoff). |
| **Timeout** | The maximum allowed duration for a step, agent, tool, approval, or workflow before forced termination. |
| **Compensation** | A forward-moving action that logically neutralizes the effect of a previously completed step when a downstream step fails. |
| **Rollback** | Reversing an operation to its exact prior state (a specific type of compensation). |
| **Approval** | A human decision gate that pauses workflow execution until a human explicitly approves, rejects, or requests changes. |
| **Escalation** | Moving a problem up the authority chain when the current level cannot resolve it. |
| **Pause** | A human-initiated temporary halt of workflow execution. |
| **Resume** | Restarting a paused workflow from its persisted state. |
| **Cancellation** | Permanently terminating a workflow before natural completion. |
| **Recovery** | The process of rehydrating workflow state after a system crash or restart. |
| **Idempotency** | The property ensuring that executing an operation multiple times produces the same result as executing it once. |
| **Concurrency** | The management of multiple simultaneous operations to prevent conflicts and data corruption. |
| **Sub-workflow** | A complete workflow triggered as a child step within a parent workflow. |
| **Fan-out** | Spawning multiple parallel child steps from a single parent step. |
| **Fan-in** | Synchronizing the completion of multiple parallel child steps before proceeding. |
| **Saga** | A pattern for managing long-running transactions via a sequence of local transactions with compensating actions. |
| **Checkpoint** | A persisted snapshot of workflow state that enables recovery. |
