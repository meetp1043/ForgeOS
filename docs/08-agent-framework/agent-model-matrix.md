# Agent Model Matrix

This matrix maps roles to their standard `Model Policy` defaults.

| Role | Primary Model (High budget) | Secondary Model (Low budget) | Local Fallback (Privacy required) |
| :--- | :--- | :--- | :--- |
| **Product Manager** | GPT-4o / Claude 3.5 Sonnet | GPT-4o-mini | Llama-3-70b |
| **Solution Architect** | Claude 3.5 Sonnet / GPT-4o | *N/A (Requires reasoning)* | Llama-3-70b |
| **Backend Eng** | Claude 3.5 Sonnet | GPT-4o-mini | Llama-3-70b / DeepSeek Coder |
| **Frontend Eng** | Claude 3.5 Sonnet | GPT-4o-mini | Llama-3-70b / DeepSeek Coder |
| **DBA** | GPT-4o | GPT-4o-mini | Llama-3-70b |
| **Code Reviewer** | Claude 3.5 Sonnet | GPT-4o-mini | Llama-3-70b |
| **Security Eng** | GPT-4o | *N/A (Requires reasoning)* | Llama-3-70b |
| **DevOps Agent** | GPT-4o-mini | GPT-3.5-turbo | Llama-3-8b |
