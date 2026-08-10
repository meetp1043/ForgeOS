# Prompt Injection Defense

Prompt injection is the primary vector for attacking autonomous AI agents. The Context Engine is the frontline defense.

## Potentially Hostile Sources
The Context Engine must treat the following retrieved sources as potentially hostile, as they can be influenced by untrusted third parties:
- Source code comments
- README files
- API Documentation
- GitHub Issues / PR comments
- Web pages (via search tools)
- Tool output (e.g., an error message containing user-controlled input)
- Imported files
- External integrations (Slack, Jira)
- Generated content from other agents

## Defense Mechanisms
1. **Data Isolation**: Utilizing [Instruction vs. Data Separation](context-instruction-data-separation.md).
2. **Authority Supremacy**: Retrieved content must never override:
   - System policy
   - Security policy
   - Agent permissions
   - Workflow policy
   - Human approval
3. **Pre-Filtering**: Running lightweight regex/heuristic scanners on candidate context to detect common injection payloads (e.g., "Ignore previous instructions") before they enter the final package.
