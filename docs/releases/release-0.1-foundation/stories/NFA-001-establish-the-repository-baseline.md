# NFA-001: Establish the repository baseline

## Status

Planned

## User story

As a maintainer, I want one clear repo so each app has a known home.

## Goal

Set the base rules. Keep each build easy to run.

## Dependencies

None.

## Acceptance criteria

- [ ] The repository uses the approved `apps`, `packages`, `infra`, and `docs` roots without Nx or Turborepo.
- [ ] Canonical product and technical identity stays in `packages/brand/brand.json`.
- [ ] `.env.example` is committed while `.env` and secret variants stay ignored.
- [ ] `AGENTS.md`, Codex roles, repository skills, and the story workflow are installed and linked.
- [ ] Apache-2.0, SPDX, editor, line-ending, and ignore rules match the documented policy.
- [ ] The dependency-free repository audit and its unit tests pass.
- [ ] The README states that product features remain planned until their stories pass.

## Out of scope

This story does not create a runnable Java, Angular, database, Docker, or AI feature.
