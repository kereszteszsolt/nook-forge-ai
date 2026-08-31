# NFA-014: Build the task workspace in Angular

## Status

Planned

## User story

As a user, I want one task screen so I can start work and read the result.

## Goal

Build the main Angular flow for files, steps, and results.

## Dependencies

`NFA-009` through `NFA-013`.

## Acceptance criteria

- [ ] The new task screen supports the four Release 0.2 task types and only their valid input shapes.
- [ ] File validation, model state, queued work, step progress, completion, and failure have clear views.
- [ ] Task history persists after reload and opens the saved input, steps, result, and artifacts.
- [ ] Result views show source references, unknowns, and inference labels when the task provides them.
- [ ] The interface sanitizes Markdown and enables no raw model HTML.
- [ ] Keyboard, focus, form labels, narrow layout, reduced motion, and retry states are tested.
- [ ] Playwright covers one success and one safe failure path with invented files.
- [ ] No chat bubble layout, global state library, or direct provider control is added.

## Out of scope

This story does not add ZIP upload, multi-file trees, or documentation generation screens.
