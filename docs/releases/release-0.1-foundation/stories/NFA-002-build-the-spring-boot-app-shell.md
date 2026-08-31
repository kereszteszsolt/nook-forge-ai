# NFA-002: Build the Spring Boot app shell

## Status

Planned

## User story

As a developer, I want a small Java app so I can add work without a large frame.

## Goal

Start Spring Boot with clear package rules. Keep the first API simple.

## Dependencies

`NFA-001`.

## Acceptance criteria

- [ ] `apps/api` uses Java 21, Maven Wrapper, Spring Boot, and pinned build plugins.
- [ ] The base package is `io.nookforge` and the first package-by-feature boundaries are clear.
- [ ] Validated configuration fails fast and no service reads environment variables directly.
- [ ] The API exposes stable liveness, readiness, build, and product identity data.
- [ ] HTTP errors use one `ProblemDetail` mapping with safe public messages.
- [ ] ArchUnit tests guard domain, application, web, persistence, and AI dependency direction.
- [ ] Focused unit tests and `./mvnw verify` pass without an Ollama service.
- [ ] No empty layer, field injection, generic base service, or provider client is added.

## Out of scope

This story does not add PostgreSQL, Angular, Docker Compose, LangChain4j, or a product task.
