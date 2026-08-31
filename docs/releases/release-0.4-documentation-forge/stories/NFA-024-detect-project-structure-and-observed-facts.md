# NFA-024: Detect project structure and observed facts

## Status

Planned

## User story

As a developer, I want the app to find real project facts so docs start from proof.

## Goal

Read build, code, config, and doc clues without running the project.

## Dependencies

`NFA-023`.

## Acceptance criteria

- [ ] A project analysis task detects root modules, languages, build tools, entry points, config, containers, and current docs.
- [ ] Detectors use parsed files and deterministic rules before any model inference.
- [ ] Each observed fact stores a type, value, source file, location, detector, and confidence class.
- [ ] Conflicting observed facts stay separate and create an explicit unresolved item.
- [ ] The analysis records ignored, failed, shortened, and omitted source scope.
- [ ] Uploaded projects are never built, tested, imported, installed, or executed.
- [ ] Fixture projects cover Java, Angular, mixed stacks, missing docs, and conflicting versions.
- [ ] The fact model remains independent from one documentation template or model provider.

## Out of scope

This story does not generate a README, architecture guide, or user guide.
