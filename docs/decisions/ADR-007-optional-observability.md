# ADR-007: Keep observability optional

- Status: Accepted
- Date: 2026-08-31

## Context

Langfuse, Prometheus, and Grafana can show AI and application behavior. They also add services, storage, resource use, configuration, and privacy choices. The local product must remain useful without them.

## Decision

Add Micrometer metrics and optional Langfuse tracing in Release 0.5. Run Langfuse, Prometheus, and Grafana only through an optional Docker Compose override.

During `NFA-032`, verify the current official Java-compatible Langfuse ingestion path and use only the minimum required integration behind an observability adapter. Do not add a collector, a second tracing platform, or a general telemetry subsystem.

Keep prompt, document, and generated-output tracing off by default. Observability failure must not fail, block, or change the result of a user task.

## Consequences

- The default local deployment stays smaller and needs fewer resources.
- Users can enable detailed AI traces and application dashboards only when useful.
- Diagnosis is more limited while the optional stack is disabled.
- Enabling the stack adds containers, storage, credentials, configuration, and maintenance.
- Privacy-sensitive content is not stored in Langfuse by default.
- Langfuse integration details remain isolated and may be updated without changing product use cases.

## Out of scope

Elasticsearch and Kibana remain possible future candidates and are not part of this decision.
