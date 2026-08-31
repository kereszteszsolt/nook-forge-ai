# ADR-005: Use PostgreSQL and local artifact storage

- Status: Accepted
- Date: 2026-08-31

## Context

Tasks, steps, workspaces, file metadata, model identity, and artifacts need durable state. Large uploads and generated ZIP files do not belong in normal relational columns. Replacing an in-memory database later would add needless migration work.

## Decision

Use PostgreSQL from the first release and manage schema changes with Flyway. Store file bytes in a local volume behind one storage adapter. Store generated relative keys, checksums, media types, and state in PostgreSQL.

## Consequences

Restarted containers keep task and artifact history. Integration tests use real PostgreSQL through Testcontainers. The storage adapter owns path safety and cleanup.

No vector extension is planned because Releases 0.1 through 0.5 do not use RAG.
