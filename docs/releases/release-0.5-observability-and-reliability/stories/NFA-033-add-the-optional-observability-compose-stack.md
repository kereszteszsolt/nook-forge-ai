# NFA-033: Add the optional observability Compose stack

## Status

Planned

## User story

As an admin, I want local monitor tools that I can turn on when I need them.

## Goal

Add one extra Docker path for those tools.

## Dependencies

`NFA-031` and `NFA-032`.

## Acceptance criteria

- [ ] `docker-compose.observability.yml` adds the supported local Langfuse, Prometheus, Grafana, and required support services.
- [ ] The override follows the pinned official Langfuse self-hosting shape selected during the story.
- [ ] Core Compose starts and completes tasks without the observability override.
- [ ] All observability ports bind to loopback and use `.env` credentials where a service needs them.
- [ ] Prometheus scrapes only approved local targets and keeps a bounded retention setting.
- [ ] Grafana uses provisioned data sources and no built-in default password in verified config.
- [ ] Base, Ollama, observability, and combined Compose configurations all pass validation.
- [ ] Volumes and service names are isolated under the `nookforge` Compose project.

## Out of scope

This story does not expose monitoring to a LAN, add cloud telemetry, add a trace collector, or add Elasticsearch and Kibana.
