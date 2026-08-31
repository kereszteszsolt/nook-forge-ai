# NFA-036: Capture release evidence and publish runbooks

## Status

Planned

## User story

As an operator, I want one runbook and proof set so I can trust the local stack.

## Goal

Test all supported modes and close the release.

## Dependencies

`NFA-030` through `NFA-035`.

## Acceptance criteria

- [ ] The operator guide covers startup, shutdown, health, model checks, storage, cleanup, backup, restore, and common failures.
- [ ] The privacy guide explains logs, metrics, Langfuse metadata, and the content-trace opt-in.
- [ ] README and all architecture, config, security, testing, technology, design, and visual docs match Release 0.5.
- [ ] Release proof covers base, managed Ollama, observability, and combined Compose modes.
- [ ] Smoke tests cover each task family, file and ZIP limits, documentation export, signal failure, restart, and cleanup.
- [ ] Checked screenshots use invented data and show product, Langfuse, and Grafana views without private content.
- [ ] The repository audit, Java checks, Angular checks, Compose checks, link checks, review, and approved commit pass.
- [ ] Future OpenRouter, LangGraph4j, MCP, Elasticsearch, and Kibana work stays marked as uncommitted options.

## Out of scope

This story does not implement a future provider, workflow engine, MCP tool, search stack, or cluster deployment.
