# NFA-022: Build the multi-file and archive user flow

## Status

Planned

## User story

As a user, I want to see the file tree and progress so I can trust archive work.

## Goal

Build the Angular flow for many files and ZIP jobs.

## Dependencies

`NFA-016` through `NFA-021`.

## Acceptance criteria

- [ ] The new task flow accepts several files or one ZIP and shows the configured limits before upload.
- [ ] The workspace view shows a safe relative tree and every accepted, ignored, rejected, or failed state.
- [ ] Archive scan, extraction, classification, parsing, selection, AI work, and artifact steps are visible.
- [ ] Users can inspect manifest totals, file reasons, selected source scope, and omissions.
- [ ] The UI gives clear retry guidance for provider, parser, archive, storage, and limit failures.
- [ ] Keyboard, focus, long names, deep safe paths, large lists, narrow layout, and reduced motion are tested.
- [ ] Playwright captures a safe ZIP path and two rejected archive paths with invented fixtures for the user guide.
- [ ] No browser code reads a local directory outside the files the user chose.

## Out of scope

This story does not add drag-and-drop folders, file-system MCP, or automatic source changes.
