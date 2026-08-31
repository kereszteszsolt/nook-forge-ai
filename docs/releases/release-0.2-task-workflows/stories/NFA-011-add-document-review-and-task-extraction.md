# NFA-011: Add document review and task extraction

## Status

Planned

## User story

As a user, I want to review a file and pull out tasks so I can act on it.

## Goal

Add two small AI flows with clear source proof.

## Dependencies

`NFA-010`.

## Acceptance criteria

- [ ] `REVIEW_DOCUMENT` returns findings, gaps, unclear text, risks, questions, and recommendations.
- [ ] `EXTRACT_TASKS` returns clear tasks with optional date, priority, source reference, and open question data.
- [ ] Both task types use task-specific AI ports and validated structured result records.
- [ ] Prompts treat file text as data and require unknown or conflicting facts to stay explicit.
- [ ] Result source references use the stored file ID and stable line ranges when available.
- [ ] List sizes, field lengths, and one repair attempt are bounded by the result schema.
- [ ] Focused tests cover valid, empty, hostile, conflicting, malformed, and timeout cases.
- [ ] The task record stores the exact provider, model, and prompt template version.

## Out of scope

This story does not compare files, create a plan, or accept more than one file.
