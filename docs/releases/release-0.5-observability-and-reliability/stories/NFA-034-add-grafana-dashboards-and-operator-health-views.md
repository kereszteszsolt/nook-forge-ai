# NFA-034: Add Grafana dashboards and operator health views

## Status

Planned

## User story

As an admin, I want clear charts so I can spot a fault fast.

## Goal

Add useful charts and a safe health page.

## Dependencies

`NFA-033`.

## Acceptance criteria

- [ ] Provisioned dashboards cover system health, task flow, AI calls, file safety, and storage cleanup.
- [ ] Panels use bounded queries, clear units, useful thresholds, and no personal or prompt data.
- [ ] The app health view separates API, database, storage, Ollama, Langfuse, and metric-stack state.
- [ ] The Angular monitoring page shows safe health and links to local tools only when they are configured.
- [ ] Dashboard and health tests cover healthy, slow, failed, missing, and disabled service states.
- [ ] Privacy-safe screenshots document Grafana and the product health view with invented task data.
- [ ] Dashboard provisioning is reproducible after volume removal and restart.
- [ ] An observability outage never changes a completed task into a failed task.

## Out of scope

This story does not embed full Langfuse traces or Grafana panels inside the product UI.
