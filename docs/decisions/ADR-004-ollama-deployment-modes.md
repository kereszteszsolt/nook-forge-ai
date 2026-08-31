# ADR-004: Support two Ollama deployment modes

- Status: Accepted
- Date: 2026-08-31

## Context

Some users already run Ollama natively or in another container. Other users want one Nook Forge-owned Ollama service. Installing Ollama inside the API image would mix model runtime and app runtime.

## Decision

Base Compose connects to `OLLAMA_BASE_URL` and starts no Ollama service. An optional `docker-compose.ollama.yml` override adds an official Ollama container, an isolated model volume, and a non-conflicting host port.

## Consequences

Existing installations remain useful. The dedicated mode is easy to remove and does not alter app images. The README must show both commands and the active endpoint.

`.env.example` is committed. `.env` is ignored and holds local values.
