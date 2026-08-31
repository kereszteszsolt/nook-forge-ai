# Release 0.1: Foundation and first structured AI path

## Status

In progress

## Outcome

Create the real monorepo, modular Spring Boot API, Angular shell, PostgreSQL and Flyway baseline, two Ollama deployment modes, and one typed LangChain4j plan flow.

## Boundaries

- Ollama is the only provider.
- The first AI endpoint is a small Release 0.1 preview path and is removed when the durable task API replaces it in `NFA-008`.
- No user file upload, async task queue, archive work, documentation generation, or observability stack is added.
- No Nx, Turborepo, microservice, broker, Helm, or Kubernetes file is added.

## Story order

| Story | Title | Status |
| --- | --- | --- |
| [`NFA-001`](stories/NFA-001-establish-the-repository-baseline.md) | Establish the repository baseline | Implemented |
| [`NFA-002`](stories/NFA-002-build-the-spring-boot-app-shell.md) | Build the Spring Boot app shell | Implemented |
| [`NFA-003`](stories/NFA-003-build-the-angular-app-shell-and-token-flow.md) | Build the Angular app shell and token flow | Implemented |
| [`NFA-004`](stories/NFA-004-add-postgresql-and-flyway.md) | Add PostgreSQL and Flyway | Implemented |
| [`NFA-005`](stories/NFA-005-add-docker-and-both-ollama-modes.md) | Add Docker and both Ollama modes | Planned |
| [`NFA-006`](stories/NFA-006-add-the-first-structured-ai-task.md) | Add the first structured AI task | Planned |
| [`NFA-007`](stories/NFA-007-join-the-first-full-stack-path-and-publish-guides.md) | Join the first full stack path and publish guides | Planned |

## Delivery rule

Work on the first planned story whose dependencies are implemented. Each story needs separate plan, implementation, and commit approval. A later story may not enter the same implementation commit.

## Plans and proof

- [Implementation plan](implementation-plan.md)
- [Verification record](verification.md)
