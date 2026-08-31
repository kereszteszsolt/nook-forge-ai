---
name: observability
description: Implement or review one approved Nook Forge logging, Langfuse, Micrometer, Prometheus, Grafana, health, recovery, or runbook story.
---

# Observability

- Keep the base app runnable without the optional observability stack.
- Use one safe correlation key across HTTP, tasks, AI calls, and artifacts.
- Do not log document text, prompts, responses, secrets, or user file paths.
- Keep Langfuse content tracing off by default and require explicit opt-in.
- Recheck the current official Java-compatible Langfuse ingestion path in `NFA-032`.
- Use only the minimum required integration and keep it behind an outbound adapter.
- Do not add a collector or a second tracing platform without a new approved story.
- Use low-cardinality metric labels and never use task, file, or workspace IDs as labels.
- Make dashboards reproducible from checked provisioning files.
- Test disabled, unavailable, and enabled observability modes.
- Keep Kibana out unless a later approved story adds Elasticsearch for a real need.

Show the exact local evidence before a release claim and ask for commit approval separately.
