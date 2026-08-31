# Codex project setup

This repository keeps three focused Codex roles:

- `architect` plans one story and edits nothing;
- `implementation_worker` implements one story only after plan and implementation approval;
- `reviewer` checks scope, regressions, evidence, safety, design, and needless complexity.

`AGENTS.md` and `docs/story-workflow.md` are the source of truth. Plan approval is not implementation approval. Implementation approval is not commit approval. Commit approval is not push or next-story approval.

For an active UI story, a user-supplied Penpot link triggers read-first MCP inspection when the connection is available. Design writes, Angular work, screenshot capture, and commit still follow the normal approval gates.
