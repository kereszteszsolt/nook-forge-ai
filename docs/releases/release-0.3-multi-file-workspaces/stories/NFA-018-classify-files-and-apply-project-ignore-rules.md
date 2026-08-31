# NFA-018: Classify files and apply project ignore rules

## Status

Planned

## User story

As a user, I want noise skipped so the app reads the files that may help.

## Goal

Classify files and show each ignore or reject reason.

## Dependencies

`NFA-016` and `NFA-017`.

## Acceptance criteria

- [ ] One classifier uses safe name, extension, media, size, and path facts to select a source kind.
- [ ] Default rules ignore version control, dependency folders, build output, caches, and common binary data.
- [ ] Unsafe files are rejected while harmless but unsupported files are ignored with different reason codes.
- [ ] Project root clues such as Maven, Gradle, npm, Docker, and Compose files receive stable kinds.
- [ ] User output shows the accepted, ignored, rejected, and failed reason for every file.
- [ ] Rules are deterministic, versioned, and testable without a model call.
- [ ] Tests cover case, Unicode, hidden names, long paths, media mismatch, and common project trees.
- [ ] A user cannot disable a security rule through file content or a task prompt.

## Out of scope

This story does not extract document text or rank files for a model prompt.
