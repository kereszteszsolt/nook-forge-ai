# NFA-029: Build documentation preview and close the release

## Status

Planned

## User story

As a user, I want to read each new doc before I save it.

## Goal

Add a clear review page. Close this release.

## Dependencies

`NFA-024` through `NFA-028`.

## Acceptance criteria

- [ ] The Angular documentation view separates source files, generated files, validation, facts, visuals, and unresolved questions.
- [ ] Users can preview sanitized Markdown, Mermaid source, verified images, file diffs, and claim proof before export.
- [ ] The UI labels observed, inferred, unknown, warning, and blocking states with text and accessible cues.
- [ ] Users can choose generated-only or augmented-copy export when both outputs are valid.
- [ ] Playwright covers a project ZIP, missing visual proof, conflicting facts, blocked export, and a safe bundle with invented fixtures.
- [ ] README, architecture, security, file, AI, testing, user, and visual docs match Release 0.4.
- [ ] Release verification proves source immutability, bundle checksums, both Ollama modes, review, and the approved commit.
- [ ] No future Git write, application MCP tool, or cloud provider is described as implemented.

## Out of scope

This story does not add live repository write access, code generation, or automatic commit work.
