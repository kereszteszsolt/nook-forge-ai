# NFA-013: Add typed API contracts and live task events

## Status

Planned

## User story

As a web user, I want typed data and live events so task state stays clear.

## Goal

Publish one API contract and stream safe step updates.

## Dependencies

`NFA-008` through `NFA-012`.

## Acceptance criteria

- [ ] The backend publishes versioned OpenAPI for workspaces, files, tasks, steps, results, and artifacts.
- [ ] The Angular API boundary uses one generated or contract-checked typed client from the published contract.
- [ ] An SSE endpoint sends ordered task and step state events with stable event IDs.
- [ ] A reconnect uses the last event ID or current task state without creating duplicate work.
- [ ] Events contain no file text, prompt text, secret, server path, or raw stack trace.
- [ ] HTTP and event errors use the same bounded public error codes.
- [ ] Contract, reconnect, disconnect, duplicate, and completed-stream tests pass.
- [ ] Components do not call `fetch`, build API URLs, or parse wire JSON directly.

## Out of scope

This story does not add WebSocket commands, task cancellation, or a second API client.
