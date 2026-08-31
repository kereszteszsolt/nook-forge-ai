# NFA-005: Add Docker and both Ollama modes

## Status

Implemented

## User story

As a user, I want to use my own Ollama or start one with the app.

## Goal

Support both local modes with clear config.

## Dependencies

`NFA-002` through `NFA-004`.

## Acceptance criteria

- [x] Base Compose starts `web`, `api`, and `postgres` and connects to `OLLAMA_BASE_URL`.
- [x] Base Compose starts no Ollama service and supports native, container, or remote reachable endpoints.
- [x] `docker-compose.ollama.yml` adds one official Ollama service with its own persistent model volume.
- [x] The dedicated Ollama host port defaults to `11435` and the API uses the internal service URL.
- [x] Supported service ports bind to loopback by default and Linux host-gateway access is configured.
- [x] `.env.example` documents all current variables while `.env` remains ignored.
- [x] Both Compose configurations pass validation and use health-based startup where it is useful.
- [x] The API and web images contain no Ollama binary, model, or cloud key.

## Out of scope

This story does not pull a model automatically or add a cloud provider.
