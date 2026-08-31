# Release 0.5: Observability and operational reliability

## Status

Planned

## Outcome

Add privacy-safe logs, metrics, optional local Langfuse and Grafana tooling, stronger task recovery, cleanup, limits, and operator proof without making the monitoring stack mandatory.

## Boundaries

- The base app works when every optional observability service is disabled or unavailable.
- Prompt, response, and document content tracing is off by default.
- The Langfuse integration uses only the minimum current Java-compatible path selected in its story.
- Prometheus labels stay bounded and never use user or task IDs.
- Elasticsearch, Kibana, cloud telemetry, LangSmith, TruLens, and Ragas are not added.

## Story order

| Story | Title | Status |
| --- | --- | --- |
| [`NFA-030`](stories/NFA-030-add-correlation-ids-and-safe-structured-logs.md) | Add correlation IDs and safe structured logs | Planned |
| [`NFA-031`](stories/NFA-031-add-micrometer-and-prometheus-metrics.md) | Add Micrometer and Prometheus metrics | Planned |
| [`NFA-032`](stories/NFA-032-add-optional-langfuse-tracing.md) | Add optional Langfuse tracing | Planned |
| [`NFA-033`](stories/NFA-033-add-the-optional-observability-compose-stack.md) | Add the optional observability Compose stack | Planned |
| [`NFA-034`](stories/NFA-034-add-grafana-dashboards-and-operator-health-views.md) | Add Grafana dashboards and operator health views | Planned |
| [`NFA-035`](stories/NFA-035-add-cleanup-recovery-and-runtime-limits.md) | Add cleanup, recovery, and runtime limits | Planned |
| [`NFA-036`](stories/NFA-036-capture-release-evidence-and-publish-runbooks.md) | Capture release evidence and publish runbooks | Planned |

## Delivery rule

Work on the first planned story whose dependencies are implemented. Each story needs separate plan, implementation, and commit approval. A later story may not enter the same implementation commit.

## Plans and proof

- [Implementation plan](implementation-plan.md)
- [Verification record](verification.md)
