# Story and Codex workflow

## Scope

This guide is the source of truth for every Nook Forge story. Codex works on one story at a time and follows its acceptance criteria in order.

## Story sections

Each story uses these sections in this order:

1. `Status`
2. `User story`
3. `Goal`
4. `Dependencies`
5. `Acceptance criteria`
6. `Out of scope`

Use `Planned`, `In progress`, or `Implemented`. A planned story has unchecked criteria. An implemented story has all criteria checked and linked proof in its release verification file.

## Story writing rules

- Keep each prose block at five sentences or less.
- Aim for three short sentences when that is enough.
- Keep each acceptance criterion to one short, testable sentence.
- Use four to eight criteria per story.
- Keep the combined `User story` and `Goal` at Flesch Reading Ease 80 or more.
- Put paths, code names, settings, and commands in code style.
- Put long commands, logs, totals, and proof in the release `verification.md` file.
- Do not add issue or limitation sections to story files.
- Do not change a shipped fact just to simplify a later story.

## Acceptance criterion order

Codex uses the listed criteria as an ordered checklist. It verifies each criterion before it claims the next one. Shared setup may support more than one criterion, but Codex may not skip, reorder, or mark a later criterion early without clear user approval.

## Approval gates

Codex uses three separate approval gates and two separate continuation gates.

1. Codex names the next valid story, scope, likely files, and checks.
2. The architect presents a plan for cross-cutting work.
3. Codex asks for clear **plan approval**.
4. After plan approval, Codex asks again for clear **implementation approval**.
5. Codex edits implementation files only after implementation approval.
6. Codex runs focused checks and shows exact results.
7. The reviewer checks risky or cross-cutting work.
8. Codex proposes one commit message and asks for clear **commit approval**.
9. Codex creates the commit only after commit approval.
10. Codex reports the commit hash.
11. Codex asks separately before any push.
12. Codex asks separately before the next story.

Plan approval does not grant implementation approval. Implementation approval does not grant commit approval. Commit approval does not grant push or next-story approval.

## Plan content

A story plan should stay small and include:

- files or packages likely to change;
- dependency and data flow;
- schema or migration effect;
- config and Docker effect;
- UI states and API contract effect;
- security and privacy effect;
- failures, rollback, and cleanup;
- focused tests and release evidence.

The plan must call out replaced code that will be removed. It must not introduce a later story in disguise.

For a UI story with a user-supplied Penpot link, the architect inspects the focused design through MCP before planning when access is available. The plan records visual states, token impact, screenshot proof, and any missing design access without inventing evidence.

## Source comment rules

These rules apply to code comments and docstrings, not user-facing help text.

- Add a comment only when names and structure cannot make the reason clear.
- Explain why a choice exists; do not repeat what the code does.
- Prefer one short sentence.
- Use at most three short sentences in a normal comment block.
- Use at most five short sentences in a Javadoc, JSDoc, or docstring.
- Put plans, change history, story text, and long design notes in Markdown docs.
- Keep SPDX headers and required tool directives unchanged.

## Evidence rules

The release `verification.md` file stores short proof for each story. It records plan approval, implementation approval, key commands, results, review, commit approval, and commit hash.

Large logs remain separate files. A failed or blocked criterion stays unchecked, and the story stays `Planned` or `In progress`.

Visual proof follows `docs/visual-documentation.md`. Product screenshots use deterministic Playwright automation, and Mermaid flowcharts use top-to-bottom direction by default.

## Story selection

The next valid story is the lowest planned story whose dependencies are implemented. A later story may start only with clear user approval and a written reason in the release verification file.

A release closes only when all of its stories are implemented and the release-wide checks pass.

## Commit and history safety

Codex may not commit, push, force-push, reset shared history, rewrite commits, or amend an approved commit without clear approval for that action. A recovery commit needs its own scope, checks, message, and approval.
