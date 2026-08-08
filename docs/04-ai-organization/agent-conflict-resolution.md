# Agent Conflict Resolution

In an organization of specialized agents, conflicts of perspective are inevitable and healthy, provided they are resolved deterministically.

## Common Conflicts
- **Architect vs. Backend Engineer**: Engineer wants to use an unsupported library for speed; Architect demands compliance with the approved stack.
- **QA vs. Developer**: QA agent rejects code because it fails an edge case; Developer claims the edge case is out of scope.
- **Security vs. DevOps**: Security agent blocks a deployment due to a vulnerable dependency; DevOps wants to ship the release.
- **Cost Engineer vs. SRE**: Cost Engineer wants to downsize the database; SRE demands overhead for traffic spikes.

## Resolution Precedence
1. **Security and Safety**: Security rules ALWAYS override Cost, Speed, or Feature requests.
2. **Product Requirements**: Scope disputes are resolved by the Product Manager or Business Analyst.
3. **Architecture**: Technical disputes are resolved by the Solution Architect or Engineering Manager.
4. **Human Escalation**: If peer agents cannot resolve a dispute after 2 communication cycles, the Orchestrator freezes the task and escalates it to the human user for a final decision.
