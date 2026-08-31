# NFA-004: Add PostgreSQL and Flyway

## Status

Planned

## User story

As a maintainer, I want saved app state so a restart does not lose key work.

## Goal

Add PostgreSQL and safe schema change rules.

## Dependencies

`NFA-002`.

## Acceptance criteria

- [ ] The API uses PostgreSQL in local and container profiles and does not use H2 as a runtime substitute.
- [ ] Flyway owns schema history and the first migration creates only the approved foundation metadata.
- [ ] Database settings use validated configuration and contain no hard-coded password.
- [ ] Readiness reports database state without exposing connection secrets.
- [ ] Persistence code stays in an outbound adapter and does not leak JPA entities to HTTP.
- [ ] Testcontainers proves migration, read, write, restart, and clean database behavior.
- [ ] The development guide explains how later stories add new forward-only migrations.

## Out of scope

This story does not add task, file, workspace, step, or artifact tables.
