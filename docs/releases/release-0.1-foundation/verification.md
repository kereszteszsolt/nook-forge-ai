# Release 0.1: Foundation and first structured AI path verification

## Status

In progress. `NFA-001` is implemented, while the remaining Release 0.1 stories stay planned.

## Story evidence

| Story | Plan approved | Implementation approved | Focused checks | Review | Commit approved | Commit hash | Status |
| --- | --- | --- | --- | --- | --- | --- | --- |
| [`NFA-001`](stories/NFA-001-establish-the-repository-baseline.md) | Approved 2026-08-31 | Approved 2026-08-31 | Passed | Passed | Approved 2026-08-31 | `8b4d038` | Implemented |
| [`NFA-002`](stories/NFA-002-build-the-spring-boot-app-shell.md) | Pending | Pending | Pending | Pending | Pending | Pending | Planned |
| [`NFA-003`](stories/NFA-003-build-the-angular-app-shell-and-token-flow.md) | Pending | Pending | Pending | Pending | Pending | Pending | Planned |
| [`NFA-004`](stories/NFA-004-add-postgresql-and-flyway.md) | Pending | Pending | Pending | Pending | Pending | Pending | Planned |
| [`NFA-005`](stories/NFA-005-add-docker-and-both-ollama-modes.md) | Pending | Pending | Pending | Pending | Pending | Pending | Planned |
| [`NFA-006`](stories/NFA-006-add-the-first-structured-ai-task.md) | Pending | Pending | Pending | Pending | Pending | Pending | Planned |
| [`NFA-007`](stories/NFA-007-join-the-first-full-stack-path-and-publish-guides.md) | Pending | Pending | Pending | Pending | Pending | Pending | Planned |

## NFA-001 focused checks

The maintainer approved the plan, implementation, and commit separately on 2026-08-31. A read-only review found no issues, and implementation commit `8b4d038` records the completed story.

Acceptance criteria were checked in their listed order:

1. `for root in apps packages infra docs; do test -d "$root"; done` passed, and no Nx or Turborepo configuration or package dependency was found.
2. `python3 -m json.tool packages/brand/brand.json` passed, and repository links identify that file as the canonical product and technical identity.
3. `git ls-files --error-unmatch .env.example` returned `.env.example`; `git check-ignore -v` matched `.env`, nested `.env` files, and `.env.*` secret variants while the tracked-file check found no committed secret variant.
4. File checks found `AGENTS.md`, three `.codex/agents/*.toml` roles, six `.agents/skills/*/SKILL.md` skills, `.codex/README.md`, and `docs/story-workflow.md`; the root README links the instruction, setup, and workflow files.
5. Inspection confirmed Apache License 2.0, required SPDX headers in hand-written Python files, LF rules in `.editorconfig` and `.gitattributes`, and documented ignore rules; `git ls-files --eol` found no tracked CRLF or mixed text file.
6. `python3 .agents/skills/release-evidence/scripts/verify_repository.py` passed with 3 agents, 6 skills, 36 stories, and valid local links; `python3 -m unittest discover -s .agents/skills/release-evidence/scripts -p 'test_*.py'` passed all 6 tests.
7. The root README now separates the implemented `NFA-001` delivery baseline from runnable application code beginning with `NFA-002`, and states that product features remain planned until their owning stories are implemented and verified.

## Release-wide checks

Pending. Record exact commands, tool and model versions, short results, supported Compose modes, runtime fixtures, screenshots, and links to any large log files after all stories pass.

## Evidence rule

Do not mark a row implemented before all story criteria are checked and its proof is recorded. Plan approval, implementation approval, commit approval, push approval, and next-story approval are separate actions.
