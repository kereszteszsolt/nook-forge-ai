# NFA-019: Extract supported document and code content

## Status

Planned

## User story

As a user, I want common docs and code read as text so the task has useful facts.

## Goal

Add tested parsers for the first safe file set.

## Dependencies

`NFA-018`.

## Acceptance criteria

- [ ] The extractor registry supports the approved text, config, source, PDF, and DOCX types.
- [ ] Each extractor returns bounded text plus page, line, section, key path, or symbol hints when available.
- [ ] Parser choice uses detected type and approved file kind rather than the extension alone.
- [ ] Macros, scripts, remote resources, external entities, and embedded objects stay disabled.
- [ ] A parser failure marks only its file failed and keeps the rest of the workspace usable.
- [ ] Extractor and parser versions are recorded in the manifest for later proof.
- [ ] Fixture tests cover normal, empty, malformed, oversized, encoded, and hostile documents.
- [ ] No parser compiles, imports, installs, or executes uploaded project code.

## Out of scope

This story does not add OCR, image understanding, audio, video, or archive formats beyond ZIP.
