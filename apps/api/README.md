# Nook Forge API

This directory contains the Java 21 and Spring Boot 4.1.1 API shell created by `NFA-002`. The source uses the `io.nookforge` base package, validated configuration, a small system web adapter, one safe error boundary, and no database or AI provider client.

## Current verification state

The source and pinned Maven build are present. The official Maven Wrapper 3.3.4 targets Maven 3.9.16 with a pinned distribution checksum, and `./mvnw verify` passes on Java 21 without external services.

## HTTP contract

| Method and path | Response |
| --- | --- |
| `GET /actuator/health/liveness` | status only; components and details stay hidden |
| `GET /actuator/health/readiness` | status only; components and details stay hidden |
| `GET /api/system/info` | canonical product identity and stable build coordinates |

Errors use `application/problem+json` with a stable `code` and a safe public message. Framework client errors keep their 4xx status; exception messages and stack traces are not returned.

## Configuration

`nookforge.api.public-base-url` must be an absolute, hierarchical HTTP or HTTPS URI with a host. Spring configuration binding owns overrides; application code does not read environment variables directly.

The build copies `../../packages/brand/brand.json` to `brand/brand.json` on the classpath. That repository file remains the only manually maintained product-identity source.

## Build

```bash
./mvnw verify
./mvnw spring-boot:run
```

No Ollama, PostgreSQL, or other external service is needed for the `NFA-002` tests.
