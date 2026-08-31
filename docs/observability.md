# Observability plan

Release 0.5 adds optional local observability. It must improve diagnosis without making the base app heavy or leaking file content.

## Signal split

| Signal | Tool | Purpose |
| --- | --- | --- |
| HTTP, JVM, task, storage, and executor metrics | Micrometer and Prometheus | rates, latency, saturation, failures |
| AI call and task traces | Langfuse | model latency, prompt version, task steps, structured-output result |
| Dashboards | Grafana | operator overview for Prometheus metrics |
| Runtime logs | structured JSON to stdout | safe event and failure diagnosis |

Langfuse and Grafana solve different problems. Langfuse shows AI-task traces, while Grafana shows aggregated application and runtime metrics.

Kibana is not part of Release 0.5 because Elasticsearch is not planned.

## Optional deployment

```text
docker-compose.yml
    core app

docker-compose.ollama.yml
    optional Nook Forge-owned Ollama

docker-compose.observability.yml
    optional Langfuse, Prometheus, Grafana, and required support services
```

The official supported Langfuse self-hosting shape is selected and pinned during `NFA-033`. Do not hand-build a smaller incompatible stack merely to reduce the service count.

## Langfuse integration boundary

`NFA-032` must recheck the current official Java-compatible Langfuse trace-ingestion path before it selects dependencies. The implementation uses only the minimum supported adapter and transport needed to send Nook Forge AI task traces.

The integration stays behind an outbound observability port. It must not spread tracing-library types through domain, application, controller, file, or persistence code. No collector, second tracing platform, or general telemetry subproject is planned.

## Correlation

One safe correlation context links:

```text
HTTP request
      ↓
workspace
      ↓
task execution
      ↓
task step
      ↓
AI call
      ↓
artifact
```

Task, file, and workspace IDs may appear as bounded log fields or Langfuse metadata when safe. They must not become Prometheus labels.

## Planned metrics

- HTTP request rate, errors, and latency;
- task queue depth and active task count;
- task completion, failure, and duration by bounded task type;
- AI call count, failure, and duration by provider and model family;
- structured-output parse and repair count;
- upload bytes and rejected file count;
- archive expanded bytes and rejected entry count;
- workspace and artifact storage use;
- JVM memory, GC, threads, and executor saturation.

No metric label may use an unbounded name, path, prompt, task ID, file ID, workspace ID, or error message.

## Langfuse privacy

Default trace data includes safe task type, provider, model, prompt template version, duration, token counts when available, result status, and bounded error class. Prompt text, model output, and document content remain off unless `LANGFUSE_TRACE_CONTENT=true` is enabled after a clear privacy warning.

A failed or missing Langfuse service drops trace data and records a bounded local warning. It must not fail, block, retry, or change the result of a user task.

## Grafana dashboards

Planned dashboards:

1. system and JVM health;
2. task throughput and latency;
3. AI call health and structured-output failures;
4. file and archive intake safety;
5. workspace storage and cleanup.

Provisioning files live in the repository. Dashboards use invented or local metrics and contain no personal data.

## Health and readiness

The API exposes liveness and readiness through Spring Boot Actuator. Readiness covers database and required local storage. Ollama reachability may be a separate component status so a temporary model outage does not hide API process health.

Optional observability outages must not fail a user task. They produce bounded warnings and dropped-signal metrics only.
