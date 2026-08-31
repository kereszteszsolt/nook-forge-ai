# NFA-023: Export a result bundle and close the release

## Status

Planned

## User story

As a user, I want one result ZIP so I can keep the new files.

## Goal

Pack the new files. Close this release.

## Dependencies

`NFA-016` through `NFA-022`.

## Acceptance criteria

- [ ] A completed multi-file task can create one generated-only ZIP with reports, artifacts, and a JSON manifest.
- [ ] The result archive uses safe deterministic paths and contains no server path, secret, or temporary file.
- [ ] The export manifest records source checksums, manifest revision, task settings, omissions, and artifact checksums.
- [ ] Export streams enforce configured output size and clean partial files after failure.
- [ ] The original source files and ZIP are never added or changed unless a later export mode says so.
- [ ] README, user guide, architecture, file, security, testing, and screenshot docs match Release 0.3.
- [ ] Release verification covers archive attacks, both Ollama modes, restart, screenshots, review, and commit proof.
- [ ] No Release 0.4 documentation generator is described as current behavior.

## Out of scope

This story does not build an augmented copy of the source archive or patch project files.
