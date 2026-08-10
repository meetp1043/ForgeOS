# Conversation Memory

Conversation Memory defines how human-agent and agent-agent dialogue contributes to the persistent knowledge base.

## The Problem with Raw Chat
Not every conversation message becomes permanent memory. Storing raw chat logs as memory leads to:
1. Massive token costs during retrieval.
2. Context pollution with conversational pleasantries, typos, and discarded ideas.
3. Contradictions (e.g., a user saying "Let's do X... actually wait, let's do Y").

## The Distillation Pipeline
To convert conversation into memory, ForgeOS uses a distillation pipeline:

1. **Candidate Extraction**: A background summarizer agent monitors the chat stream, looking for factual statements, decisions, or user preferences.
2. **Importance Detection**: The agent filters out low-value noise.
3. **Validation**: The extracted candidate is checked. (e.g., Did the user finalize this decision, or were they just brainstorming?)
4. **Storage**: The fact is stored as a Semantic, Episodic, or User Memory.
5. **Summarization**: Over time, older conversations are rolled up into dense, bulleted summaries.

## Characteristics
- **Owner**: User / Agent
- **Scope**: Conversation / Task / Project
- **Retention**: Medium-term (raw turns are archived; distilled facts are promoted to long-term memory).
- **Access**: Restricted to the participants of the conversation.
