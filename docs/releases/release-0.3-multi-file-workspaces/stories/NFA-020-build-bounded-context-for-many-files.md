# NFA-020: Build bounded context for many files

## Status

Planned

## User story

As a user, I want large file sets kept in bounds so the model does not miss scope.

## Goal

Build a clear and repeatable source budget.

## Dependencies

`NFA-016` through `NFA-019`.

## Acceptance criteria

- [ ] Each task type has a configured source budget that reserves space for system rules and output schema.
- [ ] A deterministic selector ranks structural files, task hints, size, and file kind before model work.
- [ ] Large accepted files use bounded extraction or per-file summaries with stored source proof.
- [ ] The task result records every file or section that was included, shortened, or omitted.
- [ ] The same workspace revision and settings produce the same source order before model variation.
- [ ] An input that cannot fit the minimum safe scope fails with a clear user-facing reason.
- [ ] Tests cover small, large, duplicate, conflicting, and over-budget workspaces.
- [ ] The implementation does not add vector search, embeddings, or hidden retrieval.

## Out of scope

This story does not add RAG, semantic search, reranking, or a model-driven file selector.
