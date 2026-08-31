# NFA-032: Add optional Langfuse tracing

## Status

Planned

## User story

As an admin, I want local AI traces so I can find slow or failed calls.

## Goal

Send safe task data to Langfuse.

## Dependencies

`NFA-030` and `NFA-031`.

## Acceptance criteria

- [ ] The story verifies the current official Java-compatible Langfuse trace-ingestion path before it selects dependencies.
- [ ] One outbound adapter sends task and AI-call traces without leaking trace-library types into product code.
- [ ] Default trace data includes task type, provider, model, prompt version, duration, token data when known, and result state.
- [ ] Prompt, response, and document content are omitted unless `LANGFUSE_TRACE_CONTENT=true`.
- [ ] The content switch uses validated config and a clear privacy warning.
- [ ] A missing or failed Langfuse endpoint drops trace data without failing the user task.
- [ ] Langfuse credentials come only from the runtime environment and never appear in logs or task records.
- [ ] Tests cover disabled, enabled, content-off, content-on, timeout, bad key, and exporter failure cases.

## Out of scope

This story does not add the Langfuse Compose service, a collector, a second trace backend, or public trace access.
