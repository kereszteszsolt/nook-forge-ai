# NFA-028: Validate facts and build safe documentation bundles

## Status

Planned

## User story

As a user, I want claims checked and packed safely so I can review the docs before use.

## Goal

Mark facts, gaps, and guesses, then build a safe bundle.

## Dependencies

`NFA-024` through `NFA-027`.

## Acceptance criteria

- [ ] A validation pass checks required sections, local links, image targets, Mermaid blocks, duplicate claims, and unsupported commands.
- [ ] Each generated claim keeps `OBSERVED`, `INFERRED`, or `UNKNOWN` status and source references where they exist.
- [ ] A generated-only bundle contains proposed docs, approved image assets, a fact manifest, a source manifest, and validation results.
- [ ] An optional augmented-copy bundle copies safe original files and adds generated docs under new archive output.
- [ ] The augmented copy never replaces the source archive and never includes ignored, rejected, secret, or temporary files.
- [ ] Checksums cover every generated file, copied visual asset, and the final bundle manifest.
- [ ] Tests cover broken links, missing images, invalid Mermaid, unsupported claims, secret files, name collisions, and cleanup.
- [ ] A bundle with blocking validation errors cannot be labeled ready.

## Out of scope

This story does not write to Git or modify a live working tree.
