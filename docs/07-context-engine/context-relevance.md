# Context Relevance

Relevance measures how useful a candidate piece of information is for the specific task at hand. Highly relevant context improves reasoning and reduces hallucination; irrelevant context distracts the model and inflates costs.

## Relevance Factors

During the `RANKING` phase, relevance is calculated using a weighted combination of the following factors:

1. **Task Relationship**: Is this information explicitly linked to the current `TaskID` in the backend database?
2. **Dependency Relationship**: Is this code module imported by the module currently being edited?
3. **Semantic Similarity**: Does the vector embedding of the candidate match the vector embedding of the objective? (Useful for broad discovery).
4. **Explicit Reference**: Did the user or the workflow explicitly mention this file or document? (e.g., "Look at `auth.js`").
5. **Artifact Relationship**: Is the candidate part of an artifact that governs the current feature?
6. **Project Scope**: Information within the current project is exponentially more relevant than cross-project organizational rules.
7. **Current Workflow Phase**: A deployment script is highly relevant during the "Release" phase, but largely irrelevant during the "Requirements" phase.
8. **Agent Role**: CSS styling guidelines are highly relevant to a Frontend Engineer, but irrelevant to a Database Engineer.
9. **Recency**: Information generated yesterday is generally more relevant than information generated a year ago.
10. **Authority**: Higher authority items are artificially boosted in relevance to ensure agents adhere to architectural constraints.
11. **Failure History**: Has this exact task or piece of code failed recently? If so, the failure log is hyper-relevant.
