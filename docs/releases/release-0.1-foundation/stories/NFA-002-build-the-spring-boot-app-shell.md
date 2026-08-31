# NFA-002: Build the Spring Boot app shell

## Status

Implemented

## User story

As a developer, I want a small Java app so I can add work without a large frame.

## Goal

Start Spring Boot with clear package rules. Keep the first API simple.

## Dependencies

`NFA-001`.

## Acceptance criteria

- [x] `apps/api` uses Java 21, Maven Wrapper, Spring Boot, and pinned build plugins.
- [x] The base package is `io.nookforge` and the first package-by-feature boundaries are clear.
- [x] Validated configuration fails fast and no service reads environment variables directly.
- [x] The API exposes stable liveness, readiness, build, and product identity data.
- [x] HTTP errors use one `ProblemDetail` mapping with safe public messages.
- [x] ArchUnit tests guard domain, application, web, persistence, and AI dependency direction.
- [x] Focused unit tests and `./mvnw verify` pass without an Ollama service.
- [x] No empty layer, field injection, generic base service, or provider client is added.

## Out of scope

This story does not add PostgreSQL, Angular, Docker Compose, LangChain4j, or a product task.
