# Roadmap

## Committed planning scope

The current plan contains Releases 0.1 through 0.5 and stories `NFA-001` through `NFA-036`.

### Release 0.1: foundation

Create the polyglot monorepo, modular Spring Boot API, Angular shell, design-token flow, PostgreSQL and Flyway baseline, both Ollama deployment modes, and one structured LangChain4j task.

### Release 0.2: task workflows

Add durable tasks, files, steps, artifacts, safe single-file intake, asynchronous progress, first task types, a typed API boundary, Angular task workspace, and Markdown export.

### Release 0.3: multi-file workspaces

Add multi-file manifests, secure ZIP extraction, file classification, supported document and code parsing, bounded context, cross-file work, archive UI, and result ZIP export.

### Release 0.4: Documentation Forge

Detect project facts and generate proposed README, overview, architecture, configuration, development, and user-guide files. Keep observed, inferred, and unknown statements separate. Generate vertical Mermaid diagrams and include verified screenshot assets or explicit visual gaps when the evidence supports them.

### Release 0.5: observability and reliability

Add safe structured logs, Micrometer, Prometheus, optional Langfuse tracing, Grafana, optional observability Compose, runtime recovery, cleanup, limits, screenshots, and operator guides.

See the [release index](releases/README.md).

## Candidate future options

These items have no release number and no story IDs. They are not promised features. See the [detailed future option notes](future/README.md).

### OpenRouter or another provider

Consider this only after the Ollama path and provider boundary are stable. The design requires explicit user selection, clear cloud disclosure, environment-only secrets, no fallback, and unchanged application ports.

### LangGraph4j

Consider this when a real task needs branching, checkpoints, resume, or a human approval node. Do not add it for a linear list of steps.

### Application MCP

Start with one disabled-by-default, read-only hello-world connection and one small typed tool. Require capability allowlists, workspace path isolation, explicit approval for writes, safe result schemas, and tool-call audit records.

Penpot MCP is already an active development and design handoff tool when the user supplies a design link and the connection is available. It is not the same as product MCP support.

### Elasticsearch and Kibana

Consider these only when JSON container logs are not enough and a measured search or retention need exists. They must remain optional and must not duplicate Langfuse or Prometheus work.

### Other possible work

Possible later work includes authentication, OCR, model catalog discovery, task cancellation, resumable long jobs, RAG, template packs, plugin-style task definitions, and signed artifact manifests. Each item needs a new reviewed release plan.

## Explicit exclusions

Kubernetes and Helm are not planned. A local-first single-user product does not need them without a real deployment target.
