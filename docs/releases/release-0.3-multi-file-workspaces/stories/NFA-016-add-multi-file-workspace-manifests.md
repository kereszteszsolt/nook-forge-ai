# NFA-016: Add multi-file workspace manifests

## Status

Planned

## User story

As a user, I want one workspace for many files so I can work on a full set.

## Goal

Track each file and keep one clear manifest.

## Dependencies

`NFA-015`.

## Acceptance criteria

- [ ] A workspace accepts a configured number of source files and records one manifest entry per input.
- [ ] Manifest entries keep safe relative names, size, checksum, media type, state, and reason.
- [ ] Duplicate bytes and duplicate normalized paths follow clear and tested rules.
- [ ] Workspace totals show accepted, ignored, rejected, failed, and processed bytes and file counts.
- [ ] Task input selects an immutable manifest revision so later uploads do not change a running task.
- [ ] Database and storage changes remain consistent after a partial upload failure.
- [ ] Tests cover duplicate, order, retry, restart, delete, and limit behavior.
- [ ] No raw server path appears in the API or manifest export.

## Out of scope

This story does not accept a ZIP archive or add a new file parser.
