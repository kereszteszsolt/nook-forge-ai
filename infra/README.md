# Infrastructure placeholder

Infrastructure files are added only by their approved stories. `NFA-004` placed PostgreSQL profiles and Flyway migrations in `apps/api`; it intentionally added no Compose or database initialization file here.

- `NFA-005` adds the base Compose file and the optional Ollama override;
- `NFA-033` adds the optional observability stack.

No Helm or Kubernetes files are planned. The base local stack must remain useful without observability services.
