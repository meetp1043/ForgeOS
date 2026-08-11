# Testing the AI Gateway

The `MockModelProvider` is automatically injected when Spring Boot runs tests.

## Why Mock?
- We do not want automated CI/CD pipelines failing because of missing API keys.
- We do not want to incur costs on every pull request.
- The `MockModelProvider` answers deterministically, allowing Agent reasoning tests to be isolated from LLM hallucinations.
