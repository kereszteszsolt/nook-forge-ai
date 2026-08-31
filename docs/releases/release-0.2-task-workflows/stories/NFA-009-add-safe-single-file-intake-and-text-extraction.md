# NFA-009: Add safe single-file intake and text extraction

## Status

Planned

## User story

As a user, I want to add one safe file so the app can use its text.

## Goal

Store the file, check it, and show why it was used or refused.

## Dependencies

`NFA-008`.

## Acceptance criteria

- [ ] The API accepts one file with a configured request and file size limit.
- [ ] The storage adapter writes to a generated path and keeps the original name as metadata only.
- [ ] The intake path records SHA-256, detected media type, size, and accepted or rejected state.
- [ ] The first extractor set supports plain text and Markdown with stable line references.
- [ ] Unsafe names, unsupported types, empty content, and over-limit data return safe error codes.
- [ ] Original file bytes become read-only to task workflows after intake succeeds.
- [ ] Tests cover path control, checksum, media mismatch, cleanup, retry, and restart behavior.
- [ ] No parser executes macros, scripts, links, or uploaded code.

## Out of scope

This story does not accept ZIP files, PDF, DOCX, or more than one source file.
