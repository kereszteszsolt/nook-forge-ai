# NFA-006: Add the first structured AI task

## Status

Planned

## User story

As a user, I want one AI task so I can see a useful plan.

## Goal

Turn a short goal into a clear plan.

## Dependencies

`NFA-002`, `NFA-004`, and `NFA-005`.

## Acceptance criteria

- [ ] A task-specific `PlanGenerator` port accepts a short goal and returns a typed plan result.
- [ ] One LangChain4j adapter owns the prompt, structured output mapping, validation, and bounded repair.
- [ ] Ollama model creation exists only in infrastructure configuration and uses the validated settings.
- [ ] The Release 0.1 preview endpoint returns title, phases, tasks, risks, and open questions.
- [ ] Uploaded or user text is delimited as data and cannot replace system task rules.
- [ ] Unit tests use a fake model path for valid, invalid, timeout, and repair-failure cases.
- [ ] A real installed Ollama model passes one privacy-safe smoke test in both deployment modes.
- [ ] No OpenRouter, provider fallback, model router, tool call, or LangGraph4j code is added.

## Out of scope

This story does not create durable tasks, accept files, or keep task history.
