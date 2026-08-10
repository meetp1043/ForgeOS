# Memory Confidence

Not all retained information is equally reliable. A passing comment in a chat is inherently less reliable than an approved Architecture Decision Record (ADR).

To ensure high-quality reasoning, every memory entry is tagged with a `Confidence` score.

## Confidence Levels

1. **HIGH**
   - **Definition**: Authoritative, verified, or explicitly approved information.
   - **Examples**: Human-approved requirements, merged source code facts, explicit user configurations.
   - **Behavior**: Can override lower-confidence memories. Trusted blindly by execution agents.

2. **MEDIUM**
   - **Definition**: Information derived from strong signals but lacking explicit human sign-off.
   - **Examples**: Agent consensus, successful test runs, implicit patterns extracted from code structure.
   - **Behavior**: Trusted for standard execution, but will yield to `HIGH` confidence facts if a conflict arises.

3. **LOW**
   - **Definition**: Inferred, speculative, or highly contextual information.
   - **Examples**: Ideas from a brainstorming chat, assumptions made during a failed execution, unverified agent hypotheses.
   - **Behavior**: Should never silently override higher-confidence memory. Agents must treat this as "likely but unverified."

## Determination
Confidence is determined at the point of the `Candidate -> Validated` lifecycle transition. It is calculated based on the memory's **Provenance** (who generated it) and the **Artifact** it was derived from.
