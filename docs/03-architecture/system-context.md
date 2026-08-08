# System Context

This document describes how ForgeOS fits into the broader technology ecosystem and interacts with external entities.

## Context Diagram

```mermaid
C4Context
    title System Context for ForgeOS

    Person(developer, "User / Developer", "Interacts with ForgeOS to build software.")
    
    System(forgeos, "ForgeOS", "AI Software Engineering Operating System. Orchestrates agents to build software.")
    
    System_Ext(llm_providers, "LLM Providers", "OpenAI, Anthropic, Gemini. Provides AI reasoning.")
    System_Ext(local_ai, "Local AI (Ollama)", "Provides local, privacy-preserving AI reasoning.")
    System_Ext(vcs, "Version Control (GitHub/GitLab)", "Stores generated source code and artifacts.")
    System_Ext(cloud_providers, "Cloud Platforms (AWS/GCP)", "Target environments for deploying generated apps.")
    System_Ext(auth_provider, "OAuth Providers", "Handles user authentication (Google, GitHub SSO).")

    Rel(developer, forgeos, "Directs via chat and dashboard", "HTTPS")
    Rel(forgeos, llm_providers, "Sends prompts, receives responses", "HTTPS")
    Rel(forgeos, local_ai, "Sends prompts, receives responses", "HTTP/Localhost")
    Rel(forgeos, vcs, "Commits code, opens PRs", "Git/HTTPS")
    Rel(forgeos, cloud_providers, "Provisions infra, deploys code", "API/Terraform")
    Rel(forgeos, auth_provider, "Validates login", "OAuth2")
```

## Key External Interfaces

- **LLM Providers**: The primary "brain" dependency. ForgeOS manages these via a failover router.
- **Version Control Systems**: ForgeOS acts as a virtual developer, interacting with standard Git repositories to manage state and artifacts.
- **Deployment Environments**: ForgeOS DevOps agents will interact with cloud APIs or Kubernetes clusters to deploy the user's generated applications.
- **Identity Providers**: standard OAuth2 delegation to avoid building custom identity management.
