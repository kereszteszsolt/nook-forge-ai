# NFA-021: Add workspace summary and consistency review

## Status

Planned

## User story

As a user, I want a set summary and a conflict check so I can spot key facts.

## Goal

Add two cross-file flows with visible source scope.

## Dependencies

`NFA-020`.

## Acceptance criteria

- [ ] `SUMMARIZE_WORKSPACE` returns file groups, key facts, decisions, tasks, risks, and missing data.
- [ ] `FIND_INCONSISTENCIES` returns conflicting claims with source sides and a clear conflict class.
- [ ] Both flows use the immutable manifest revision and stored context-selection proof.
- [ ] Observed facts, AI inferences, and unknowns use distinct structured fields.
- [ ] A result cannot claim full workspace coverage when any accepted content was omitted.
- [ ] The same prompt, schema, repair, provider, model, and error rules apply to both tasks.
- [ ] Focused tests cover matching, conflicting, missing, hostile, partial, and over-budget data.
- [ ] No flow edits a source file or chooses a legal, medical, insurance, or financial action.

## Out of scope

This story does not generate project documentation or compare more than the selected workspace scope.
