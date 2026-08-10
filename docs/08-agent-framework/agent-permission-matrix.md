# Agent Permission Matrix

This matrix maps hard system permissions to roles.

| Permission | Definition | Backend Eng | Frontend Eng | DBA | QA Eng | Sec Eng | Sol Arch | DevOps |
| :--- | :--- | :--- | :--- | :--- | :--- | :--- | :--- | :--- |
| `FILE_READ` | Read repository files. | Yes | Yes | Yes | Yes | Yes | Yes | Yes |
| `FILE_WRITE` | Modify repository files. | Yes | Yes | Yes | Yes | No | Yes | Yes |
| `GIT_COMMIT` | Create commits locally. | Yes | Yes | Yes | Yes | No | Yes | Yes |
| `GIT_PUSH` | Push branches to origin. | Yes | Yes | Yes | Yes | No | Yes | Yes |
| `MERGE_PR` | Merge code into main/master. | No | No | No | No | No | No | Yes |
| `DATABASE_READ` | Query data (non-PII). | Yes (Dev) | No | Yes | Yes | Yes | Yes | Yes |
| `DATABASE_WRITE`| Mutate data. | Yes (Dev) | No | Yes | No | No | No | No |
| `DATABASE_DROP` | Delete tables/schemas. | No | No | Yes (Dev) | No | No | No | No |
| `DEPLOY_PROD` | Push to live environment. | No | No | No | No | No | No | Yes |
| `SECRET_MGMT` | View/Rotate raw keys. | No | No | No | No | Yes | No | Yes |
