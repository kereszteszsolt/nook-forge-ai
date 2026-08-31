# NFA-026: Generate architecture and configuration guides

## Status

Planned

## User story

As a dev, I want clear system and setup notes so I can use the app.

## Goal

Use known facts to draft both guides.

## Dependencies

`NFA-024` and `NFA-025`.

## Acceptance criteria

- [ ] The task can create proposed `docs/architecture.md` and `docs/configuration.md` artifacts.
- [ ] Architecture content maps detected modules, entry points, calls, storage, and external systems to sources.
- [ ] Mermaid flowcharts use checked facts and a top-to-bottom layout unless a recorded reason needs another form.
- [ ] Wide systems split into smaller diagrams, and inferred edges are marked clearly.
- [ ] Configuration content lists found variables, files, defaults, secrets, ports, and profiles without printing secret values.
- [ ] Missing startup, dependency, or deployment facts become explicit questions instead of invented commands.
- [ ] Generated links and Mermaid blocks pass local syntax and target checks where tools allow.
- [ ] Tests cover multi-module, missing config, duplicate config, secret-like data, and conflicting port fixtures.

## Out of scope

This story does not generate development, testing, or user-guide documents.
