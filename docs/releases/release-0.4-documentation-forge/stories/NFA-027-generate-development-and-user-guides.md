# NFA-027: Generate development and user guides

## Status

Planned

## User story

As a developer, I want build and use guides so others can work with the app.

## Goal

Draft development, test, and user guides from known facts.

## Dependencies

`NFA-024` through `NFA-026`.

## Acceptance criteria

- [ ] The task can create proposed development, testing, and user-guide Markdown artifacts.
- [ ] Development commands come from checked build files, scripts, Compose files, or existing docs and keep source proof.
- [ ] Testing content separates found checks from recommended checks that are not yet implemented.
- [ ] The user guide describes only detected screens, routes, API flows, or existing feature text.
- [ ] Verified source screenshot assets may be reused, while missing images become clear review notes.
- [ ] Generated examples use invented data and contain no source secret, personal path, or live prompt.
- [ ] Tests cover projects with full, partial, stale, and conflicting build, visual, and user documentation.
- [ ] The task does not build, run, or start the uploaded app to capture a new screenshot.

## Out of scope

This story does not generate API reference files, code comments, or source patches.
