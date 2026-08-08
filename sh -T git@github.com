 .github/workflows/repository-validation.yml        | 22 [32m++++++[m
 AGENTS.md                                          | 87 [32m++++++++++++++++++++++[m
 docs/00-governance/README.md                       | 12 [32m+++[m
 .../00-governance/architecture-decision-records.md | 10 [32m+++[m
 docs/00-governance/change-management.md            |  5 [32m++[m
 docs/00-governance/definition-of-done.md           | 10 [32m+++[m
 docs/00-governance/documentation-standards.md      |  7 [32m++[m
 docs/00-governance/engineering-principles.md       | 28 [32m+++++++[m
 docs/00-governance/git-conventions.md              |  9 [32m+++[m
 docs/00-governance/security-baseline.md            |  8 [32m++[m
 docs/README.md                                     | 38 [32m++++++++++[m
 prompts/README.md                                  | 37 [32m+++++++++[m
 scripts/check-secrets.ps1                          | 37 [32m+++++++++[m
 scripts/validate-repository.ps1                    | 58 [32m+++++++++++++++[m
 templates/architecture-decision-record.md          | 30 [32m++++++++[m
 15 files changed, 398 insertions(+)
