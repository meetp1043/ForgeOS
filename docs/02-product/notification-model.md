# Notification Model

ForgeOS keeps the user informed of the AI organization's progress without overwhelming them with noise.

## Notification Triggers
- **Task completion**: When a significant feature or sprint is finished.
- **Task failure**: When an agent hits its retry limit and gives up.
- **Approval requests**: When an agent hits a medium/high/critical risk gate.
- **Deployment**: Success or failure of a release.
- **Incidents**: Real-time alerts if a production metric fails.
- **Security warnings**: When an agent detects a vulnerability or secret leak.
- **Budget warnings**: When token usage approaches user-defined limits.
- **Agent escalation**: When an agent is blocked by ambiguity and needs human clarification.
- **Project milestones**: When architectural phases are completed.

## Delivery Channels
- **In-App**: Real-time toast notifications and a centralized Notification Center in the dashboard.
- **Email**: Daily digests or immediate alerts for critical approvals and incidents.
- **Future Integrations**: Webhooks for Slack, Discord, and Microsoft Teams.
