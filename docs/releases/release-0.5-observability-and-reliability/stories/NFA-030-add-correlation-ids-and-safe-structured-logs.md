# NFA-030: Add correlation IDs and safe structured logs

## Status

Planned

## User story

As an operator, I want safe linked logs so I can trace a failed task.

## Goal

Add one trace key and keep private text out of logs.

## Dependencies

`NFA-029`.

## Acceptance criteria

- [ ] Each HTTP request gets or validates one safe correlation ID and returns it to the client.
- [ ] Task, step, AI, file, and artifact events keep the correlation context across async work.
- [ ] Application logs use structured JSON fields and stable event names.
- [ ] Logs contain safe IDs, counts, states, durations, and bounded error codes only.
- [ ] Raw file text, prompts, responses, secrets, original local paths, and stack traces stay out of normal logs.
- [ ] Error logging keeps full internal cause data only in a safe local developer mode that is off by default.
- [ ] Tests cover generated, supplied, invalid, async, failure, and redaction cases.
- [ ] The Angular error view can show the safe correlation ID for local support.

## Out of scope

This story does not add Elasticsearch, Kibana, Langfuse, Prometheus, or Grafana.
