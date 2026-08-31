# NFA-008: Add the task, workspace, file, and artifact model

## Status

Planned

## User story

As a user, I want my work saved as tasks so I can return to it later.

## Goal

Add small records for workspaces, files, steps, and artifacts.

## Dependencies

`NFA-007`.

## Acceptance criteria

- [ ] Flyway adds workspace, source file, task, task step, artifact, and task result tables with stable IDs.
- [ ] Domain states and transitions match the documented workspace, file, task, and step rules.
- [ ] JPA entities stay inside persistence adapters and map to domain or application records.
- [ ] Each task stores its type, provider, model, prompt version, timestamps, and bounded failure code.
- [ ] Each artifact stores a safe key, media type, byte size, and SHA-256 checksum.
- [ ] The Release 0.1 preview endpoint is removed when the task API owns the plan path.
- [ ] Migration and repository tests cover create, transition, restart, and invalid state cases.
- [ ] No status may skip a domain transition through a controller or repository shortcut.

## Out of scope

This story does not upload file bytes, run a background task, or add a new AI task type.
