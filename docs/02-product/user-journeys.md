# User Journeys

### 1. Create a New Project
- **Trigger**: User selects "New Project".
- **User actions**: Enters project name and initial context.
- **ForgeOS actions**: Initializes Workspace, Memory, and Context engines.
- **Agent involvement**: Executive agent sets up the organizational structure.
- **Artifacts created**: Project Model, initial Workspace.
- **Human approvals**: None.
- **Possible failures**: Storage limit reached.
- **Expected result**: An empty but fully initialized ForgeOS project.

### 2. Describe a Software Idea
- **Trigger**: User types a natural language prompt (e.g., "Build an inventory app").
- **User actions**: Converses with the system to clarify intent.
- **ForgeOS actions**: Routes the conversation to the Business Analyst agent.
- **Agent involvement**: Business Analyst asks clarifying questions.
- **Artifacts created**: Raw context notes.
- **Human approvals**: None.
- **Possible failures**: Ambiguous prompt causing endless clarification loops.
- **Expected result**: Sufficient context gathered to begin requirements definition.

### 3. Business Analysis & 4. Requirements Creation
- **Trigger**: Analyst determines enough context exists.
- **User actions**: Reviews the generated requirements document.
- **ForgeOS actions**: Synthesizes the conversation into structured PRDs.
- **Agent involvement**: Product Manager and Business Analyst.
- **Artifacts created**: Requirements Document (PRD).
- **Human approvals**: User must explicitly approve the PRD.
- **Possible failures**: Misinterpreted business rules.
- **Expected result**: An approved, structured requirements artifact.

### 5. Architecture Design
- **Trigger**: PRD is approved.
- **User actions**: Reviews architecture proposal.
- **ForgeOS actions**: Generates system design, tech stack selection, and data models.
- **Agent involvement**: Solution Architect.
- **Artifacts created**: Architecture Decision Records (ADRs), System Design Document.
- **Human approvals**: User must approve the architecture.
- **Possible failures**: Architect selects incompatible technologies.
- **Expected result**: An approved architectural blueprint.

### 6. Project Planning & 7. Start Implementation
- **Trigger**: Architecture is approved.
- **User actions**: Clicks "Start Sprint".
- **ForgeOS actions**: Breaks architecture into discrete tasks, assigns them to developers.
- **Agent involvement**: Project Manager, Backend/Frontend Developers.
- **Artifacts created**: Task list, initial source code.
- **Human approvals**: User approves the task breakdown.
- **Possible failures**: Circular task dependencies.
- **Expected result**: Agents begin writing code autonomously.

### 8. Review Generated Code & 9. Approve an Agent Action
- **Trigger**: Developer agent completes a task.
- **User actions**: Inspects the diff and review notes.
- **ForgeOS actions**: Presents the code, test results, and Reviewer agent's sign-off.
- **Agent involvement**: Code Reviewer, Security Reviewer.
- **Artifacts created**: Code commits, Review Reports.
- **Human approvals**: User approves the merge.
- **Possible failures**: Merge conflicts, failing tests.
- **Expected result**: Code is merged into the main branch.

### 10. Reject an Agent Action
- **Trigger**: User identifies a flaw in the generated code.
- **User actions**: Clicks "Reject" and provides feedback.
- **ForgeOS actions**: Routes the task back to the Developer agent with the feedback appended as context.
- **Agent involvement**: Developer agent.
- **Artifacts created**: Updated Task history.
- **Expected result**: Agent revises the code and submits a new review request.

### 11. Run Tests & 12. Fix a Failed Test
- **Trigger**: CI pipeline or agent execution triggers tests.
- **User actions**: Monitors progress.
- **ForgeOS actions**: Executes test suite. If failed, routes logs to QA/Developer.
- **Agent involvement**: QA Engineer, Developer.
- **Artifacts created**: Test reports.
- **Expected result**: Agent automatically patches the code to fix the test and re-runs.

### 13. Deploy
- **Trigger**: All tasks complete, code merged, user requests deployment.
- **User actions**: Clicks "Deploy to Production".
- **ForgeOS actions**: Executes deployment pipelines.
- **Agent involvement**: DevOps Engineer.
- **Artifacts created**: Deployment manifests, Release logs.
- **Human approvals**: CRITICAL - Explicit human approval required.
- **Possible failures**: Cloud provider API errors, misconfigured secrets.
- **Expected result**: Application is live.

### 14. Monitor Production & 15. Handle an Incident
- **Trigger**: Production metric crosses a threshold (e.g., 500 errors).
- **User actions**: Receives alert, reviews agent's proposed mitigation.
- **ForgeOS actions**: SRE agent analyzes logs, proposes a rollback or hotfix.
- **Agent involvement**: SRE, DevOps.
- **Artifacts created**: Incident Report.
- **Human approvals**: User approves the hotfix deployment.
- **Expected result**: System returns to healthy state.

### 16. Modify Requirements & 17. Add a New Feature
- **Trigger**: User provides new context to an existing project.
- **ForgeOS actions**: PM agent diffs the new request against the existing PRD, Architect updates ADRs, Planner generates new tasks.
- **Expected result**: System safely integrates the new feature without breaking existing logic.

### 18. Pause, 19. Resume, 20. Archive a Project
- **Trigger**: User selects lifecycle actions.
- **ForgeOS actions**: Safely halts active agents, persists memory/context to disk, or securely packages artifacts for archival.
