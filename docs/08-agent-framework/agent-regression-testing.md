# Regression Testing

When an Agent Definition is updated (e.g., its System Prompt is tweaked to improve coding style), the new version must be regression tested against previous known tasks to ensure the change didn't break existing capabilities.

## The Regression Suite

The Framework maintains a suite of historical tasks (input contexts + expected outputs). 
When `Backend Engineer v1.1` is proposed, it is run through the suite.

## Detection Targets
The regression suite specifically looks for:
- **Quality Regression**: Does the agent now fail to solve a bug it previously solved?
- **Security Regression**: Does the new prompt accidentally make the agent more susceptible to executing untrusted code?
- **Cost Regression**: Did the new prompt cause the agent to become overly verbose, doubling the token usage for the same task?
- **Latency Regression**: Does the new agent take 5 LLM loops to solve a problem the old one solved in 2?
- **Tool Regression**: Does the agent now output malformed JSON for tool calls?
- **Instruction Regression**: Does the agent ignore the System Policy now because the new Task Policy was written too aggressively?
