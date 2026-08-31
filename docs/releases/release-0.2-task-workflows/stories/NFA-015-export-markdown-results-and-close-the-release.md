# NFA-015: Export Markdown results and close the release

## Status

Planned

## User story

As a user, I want a Markdown file so I can keep the task result.

## Goal

Create a safe file. Close this release.

## Dependencies

`NFA-008` through `NFA-014`.

## Acceptance criteria

- [ ] A completed task can create one Markdown artifact from its validated structured result.
- [ ] The artifact uses a safe generated name, declared media type, checksum, and bounded size.
- [ ] A failed export leaves the task result intact and records a bounded artifact failure.
- [ ] README, user guide, architecture, development, testing, and technology docs match Release 0.2.
- [ ] Reviewed Playwright screenshots show task creation, progress, result, history, failure, and export with invented data.
- [ ] The screenshot index records each route, viewport, fixture, capture method, and related story.
- [ ] Release verification records focused checks, both Ollama modes, restart proof, review, and the approved commit.
- [ ] No Release 0.3 ZIP or multi-file behavior is described as implemented.

## Out of scope

This story does not create result ZIP files or modify any uploaded source file.
