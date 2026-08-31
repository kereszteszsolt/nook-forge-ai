# NFA-025: Generate a README and project overview

## Status

Planned

## User story

As a dev, I want a README draft so a new reader can know the app.

## Goal

Use facts to write a clear intro.

## Dependencies

`NFA-024`.

## Acceptance criteria

- [ ] The task creates proposed `README.md` and `docs/overview.md` artifacts without replacing the source README.
- [ ] Stable templates define required sections while the AI fills only evidence-backed content.
- [ ] Commands appear only when found in source files or are marked as unverified proposals.
- [ ] Badges, features, ports, models, and deployment modes cannot claim facts outside the project evidence.
- [ ] Observed, inferred, and unknown statements remain visible in the structured result and review view.
- [ ] The generated README uses verified source images or records a clear visual-evidence gap without inventing screenshots.
- [ ] The generated README links only to files that exist in the generated bundle or copied source tree.
- [ ] Tests cover complete, sparse, conflicting, hostile, and already-documented fixture projects.

## Out of scope

This story does not generate architecture, configuration, development, testing, or user-guide files.
