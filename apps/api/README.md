# Nook Forge API

This directory contains the Java 21 and Spring Boot 4.1.1 API shell created by `NFA-002` and the PostgreSQL and Flyway foundation added by `NFA-004`. The source uses the `io.nookforge` base package, validated configuration, a small system web adapter, one safe error boundary, and a persistence adapter without an AI provider client.

## Current verification state

The source and pinned Maven build are present. The official Maven Wrapper 3.3.4 targets Maven 3.9.16 with a pinned distribution checksum. `./mvnw verify` passes on Java 21 with Docker available for its isolated PostgreSQL Testcontainers test; unit, configuration, web-slice, and architecture tests remain independent of PostgreSQL.

## HTTP contract

| Method and path | Response |
| --- | --- |
| `GET /actuator/health/liveness` | status only; components and details stay hidden |
| `GET /actuator/health/readiness` | database-aware status only; components and details stay hidden |
| `GET /api/system/info` | canonical product identity and stable build coordinates |

Errors use `application/problem+json` with a stable `code` and a safe public message. Framework client errors keep their 4xx status; exception messages and stack traces are not returned.

## Configuration

`nookforge.api.public-base-url` must be an absolute, hierarchical HTTP or HTTPS URI with a host. Database host, port, name, username, and password bind through validated `nookforge.database` properties; application code does not read environment variables directly.

The `local` profile uses `127.0.0.1:5433` by default, while the `container` profile uses `postgres:5432`. Both default to the `nookforge` database and user. `POSTGRES_PASSWORD` has no application default and must be supplied at startup.

Flyway owns schema history under `db/migration`. Hibernate uses `validate`, SQL initialization is disabled, and the first migration creates only `installation_metadata`.

The build copies `../../packages/brand/brand.json` to `brand/brand.json` on the classpath. That repository file remains the only manually maintained product-identity source.

## Build

```bash
./mvnw verify
POSTGRES_PASSWORD=nookforge-local-only ./mvnw spring-boot:run
```

The runtime command expects a PostgreSQL service matching the active profile. `NFA-005` owns the supported Compose stack; Ollama is not needed for the current API tests.
