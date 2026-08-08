# Dashboard Requirements

The main project dashboard is the central hub for observing and managing the AI organization.

## Dashboard Components

- **Project Overview**: High-level summary of the project, including its name, description, and status.
- **Current Objective**: The immediate goal the AI organization is working towards (e.g., "Implement User Authentication").
- **Project Health**: Metrics indicating the stability of the build, test coverage, and code quality scores.
- **Agent Activity**: A real-time visual representation of which agents are active, what task they are executing, and their current thought process.
- **Active Tasks**: A Kanban-style view of the Sprint backlog (TODO, IN PROGRESS, REVIEW, DONE).
- **Blockers**: Any tasks currently stuck due to missing context, test failures, or ambiguity.
- **Approvals**: A prominent widget highlighting any pending human approvals required to unblock agents.
- **Test Status**: Pass/fail indicators for the latest CI/CD test runs.
- **Deployment Status**: Information on the currently deployed version across environments (Dev, Staging, Prod).
- **Incidents**: Real-time alerts for any production anomalies or SRE agent interventions.
- **Cost**: A rolling tracker of AI token costs incurred during the current project or billing cycle.
- **Recent Decisions**: A log of recent architectural or product decisions made by the PM or Architect agents.
- **Recent Artifacts**: Quick links to the latest generated PRDs, ADRs, or test reports.
