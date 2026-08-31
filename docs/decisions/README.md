# Architecture decision records

These decisions capture the approved planning direction. They may be replaced only by a later ADR and an approved story.

| ADR | Status | Decision |
| --- | --- | --- |
| [ADR-001](ADR-001-polyglot-monorepo.md) | Accepted | Small polyglot monorepo without Nx or Turborepo |
| [ADR-002](ADR-002-modular-monolith.md) | Accepted | One Spring Boot modular monolith with package-by-feature boundaries |
| [ADR-003](ADR-003-ai-provider-boundary.md) | Accepted | LangChain4j adapters, Ollama now, provider-neutral application ports |
| [ADR-004](ADR-004-ollama-deployment-modes.md) | Accepted | Existing Ollama by default and optional Compose-managed Ollama |
| [ADR-005](ADR-005-postgresql-and-local-artifacts.md) | Accepted | PostgreSQL metadata, Flyway, and local file artifacts |
| [ADR-006](ADR-006-design-tokens-and-penpot.md) | Accepted | Repository-owned design tokens with Penpot MCP handoff |
| [ADR-007](ADR-007-optional-observability.md) | Accepted | Optional Langfuse, Prometheus, and Grafana stack |
