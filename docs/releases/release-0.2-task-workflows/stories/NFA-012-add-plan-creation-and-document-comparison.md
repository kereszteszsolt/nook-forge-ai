# NFA-012: Add plan creation and document comparison

## Status

Planned

## User story

As a user, I want a plan or a fair file check so I can make sense of my options.

## Goal

Add plan work and a two-file compare flow.

## Dependencies

`NFA-010` and `NFA-011`.

## Acceptance criteria

- [ ] `CREATE_PLAN` accepts a short goal and optional source file and returns phases, tasks, risks, and questions.
- [ ] `COMPARE_DOCUMENTS` accepts exactly two ready files and returns common, added, removed, changed, and warning items.
- [ ] Comparison results cite the side and source location for each supported claim.
- [ ] The comparison task does not rank a legal, medical, insurance, or financial choice for the user.
- [ ] Both flows use the same task state, artifact, provider, model, and error rules as Release 0.2.
- [ ] The old Release 0.1 plan behavior has one live implementation behind the durable task flow.
- [ ] Focused tests cover goal-only, file-backed, equal, changed, missing, hostile, and over-limit input.
- [ ] No task sends content to a provider other than Ollama.

## Out of scope

This story does not add three-way comparison, web research, or automatic user decisions.
