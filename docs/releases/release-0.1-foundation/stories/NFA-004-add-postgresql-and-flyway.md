# NFA-004: Add PostgreSQL and Flyway

## Status

Implemented

## User story

As a maintainer, I want saved app state so a restart does not lose key work.

## Goal

Add PostgreSQL and safe schema change rules.

## Dependencies

`NFA-002`.

## Acceptance criteria

- [x] The API uses PostgreSQL in local and container profiles and does not use H2 as a runtime substitute.
- [x] Flyway owns schema history and the first migration creates only the approved foundation metadata.
- [x] Database settings use validated configuration and contain no hard-coded password.
- [x] Readiness reports database state without exposing connection secrets.
- [x] Persistence code stays in an outbound adapter and does not leak JPA entities to HTTP.
- [x] Testcontainers proves migration, read, write, restart, and clean database behavior.
- [x] The development guide explains how later stories add new forward-only migrations.

## Out of scope

This story does not add task, file, workspace, step, or artifact tables.
