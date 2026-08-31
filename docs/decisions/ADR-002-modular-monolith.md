# ADR-002: Build one modular Spring Boot monolith

- Status: Accepted
- Date: 2026-08-31

## Context

The first product has one local user path and one database. Microservices would add network, deployment, and failure work before there is a service boundary. A single unstructured package would make later AI, file, and documentation work hard to change.

## Decision

Build one Spring Boot process with package-by-feature boundaries. Use domain, application, and adapter packages inside a feature only when they make a real boundary clear. Use ArchUnit to guard dependency direction.

## Consequences

Local startup stays small. Transactions and task state remain easy to reason about. External dependencies such as Ollama, JPA, file storage, and ZIP parsing stay behind adapters.

Do not create empty layers, one interface per method, or separate Maven modules without a measured need.
