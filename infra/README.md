# Infrastructure

The repository-root `docker-compose.yml` runs the web, API, and PostgreSQL services against an existing Ollama endpoint. `docker-compose.ollama.yml` adds one official Ollama service and its persistent model volume without an automatic model pull.

- `NFA-004` owns the PostgreSQL profiles and Flyway migrations under `apps/api`;
- `NFA-005` owns the base Compose file, container images, and optional Ollama override;
- `NFA-033` adds the optional observability stack.

No Helm or Kubernetes files are planned. The base local stack must remain useful without observability services.
