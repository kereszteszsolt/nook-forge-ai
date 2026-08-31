# NFA-035: Add cleanup, recovery, and runtime limits

## Status

Planned

## User story

As a user, I want the app to deal with stuck work after a restart.

## Goal

Set clear limits. Clean old temp files.

## Dependencies

`NFA-030` through `NFA-034`.

## Acceptance criteria

- [ ] Startup finds stale queued or running tasks and applies one documented recovery or failure rule.
- [ ] Temporary uploads, extracted files, partial artifacts, and expired workspaces use safe scheduled cleanup.
- [ ] Cleanup uses database ownership, storage keys, grace periods, and idempotent delete rules.
- [ ] Disk use, executor saturation, queue depth, model timeout, artifact size, and task age have validated limits.
- [ ] A limit breach returns a bounded error and leaves original files and prior artifacts safe.
- [ ] Recovery and cleanup events emit safe logs and metrics without high-cardinality labels.
- [ ] Tests cover process interruption, stale state, duplicate cleanup, locked file, low disk, and observability outage.
- [ ] The operator can run a dry-run cleanup report before destructive local cleanup.

## Out of scope

This story does not add distributed locks, multiple API replicas, task migration, or a broker.
