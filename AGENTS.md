# AGENTS.md

## Project

**Nook Forge** is the display name for the `nook-forge-ai` repository. It is planned as a local-first file and task workspace built with Angular, Java 21, Spring Boot, LangChain4j, PostgreSQL, Docker Compose, and Ollama.

The core path is small: add files, choose a task, watch clear steps, inspect a structured result, and export generated artifacts. Original files stay read-only.

## Planning state

This repository contains the verified API shell from `NFA-002`, Angular shell from `NFA-003`, PostgreSQL and Flyway foundation from `NFA-004`, and both Docker/Ollama deployment modes from `NFA-005`. It does not yet contain an AI feature, joined API flow, or runnable product workflow.

A plan is not implemented behavior. Do not write docs, badges, examples, or release notes that claim a command or feature works before its story is implemented and verified.

## Source of truth

Read these files before work:

- `docs/story-workflow.md` for plan, implementation, commit, and evidence gates;
- the next valid `NFA-*` story for scope and ordered acceptance criteria;
- the matching release `implementation-plan.md` for the agreed target;
- `docs/architecture.md` for dependency direction;
- `docs/security.md` and `docs/file-workspaces.md` for file and archive rules;
- `docs/design/README.md` for token and Penpot MCP rules;
- `docs/visual-documentation.md` for screenshots and vertical Mermaid diagrams.

## Product principles

- Keep local use simple and account-free.
- Bind supported local services to loopback by default.
- Keep original files read-only and export new artifacts separately.
- Treat uploaded text as untrusted data, not as model instructions.
- Show observed facts, model inferences, and unknowns as different things.
- Keep task state, file metadata, model identity, and artifact proof across restarts.
- Keep the base app useful without Langfuse, Prometheus, or Grafana.
- Do not send content to a cloud provider unless a later story adds an explicit opt-in.

## Repository architecture

Use a polyglot monorepo without Nx or Turborepo:

```text
apps/api                 Java 21 and Spring Boot
apps/web                 Angular and TypeScript
packages/brand           shared product identity
packages/design-tokens   repository-owned token source
infra                    Compose and service configuration
docs                     product, design, architecture, and release proof
```

Maven owns the Java build. npm and Angular CLI own the web build. Root scripts may coordinate checks, but they must not hide the native commands.

## Backend architecture

Build one Spring Boot modular monolith. Use package-by-feature boundaries with small ports and adapters where an external dependency exists.

Planned main features:

```text
task
workspace
sourcefile
artifact
documentation
shared
bootstrap
```

Rules:

- Keep controllers thin and map HTTP work at the edge.
- Keep use-case order and transactions in application services.
- Keep JPA entities inside persistence adapters.
- Keep LangChain4j and Ollama types inside AI adapters and configuration.
- Build concrete dependencies in Spring configuration, not in domain objects.
- Use constructor injection and validated `@ConfigurationProperties`.
- Do not add a repository class, abstraction, or module when a direct small design is clear.
- Use ArchUnit tests to guard the agreed dependency direction.

## AI provider rules

The current supported provider is Ollama only. The application depends on task-specific AI ports, while a LangChain4j adapter owns prompts, structured output mapping, and the configured chat model.

Provider selection belongs in infrastructure configuration. `OpenRouter` and other providers are future candidates only.

Never add silent local-to-cloud fallback. A later cloud provider must be explicit in config and UI, use secrets from the environment, and require clear user consent before file content leaves the machine.

## File and archive rules

- Store uploads under generated server paths, never user paths.
- Keep the original file name as metadata only.
- Check size, count, path depth, media type, and archive expansion before analysis.
- Reject absolute paths, parent traversal, links, devices, and unsafe ZIP entries.
- Do not run uploaded code, scripts, macros, or binaries.
- Ignore build output, dependency folders, version-control data, and known binary files.
- Keep extracted work in an isolated workspace and clean temporary data.
- Do not modify, delete, or overwrite an original upload as an AI side effect.

## Frontend rules

- Use Angular with strict TypeScript and standalone feature boundaries.
- Keep API calls behind one typed client boundary.
- Keep task, workspace, file, and artifact state inside their features.
- Use design tokens for color, type, space, radius, elevation, and motion.
- Sanitize rendered Markdown and treat AI output as untrusted content.
- Cover empty, loading, progress, success, partial, error, retry, and unavailable states.
- Inspect a user-supplied Penpot link before planning when Penpot MCP is available.
- Use approved Penpot writes only after separate plan and implementation approvals.
- Capture deterministic Playwright evidence for README, user-guide, and release documentation.
- Use vertical Mermaid flowcharts by default and split wide diagrams.
- Do not add a global state library or a second UI library without an approved story.

## Docker and configuration

The supported design has two Ollama modes:

1. Base Compose connects to an Ollama endpoint supplied through `OLLAMA_BASE_URL`.
2. `docker-compose.ollama.yml` adds a Nook Forge-owned Ollama container and volume.

The external endpoint may be a native service, another container, or another reachable host. Ollama must not be installed in the API or web image.

Commit `.env.example`. Never commit `.env` or any secret-bearing variant.

## Observability rules

Release 0.5 plans Langfuse, Prometheus, and Grafana as an optional local stack. The base app must still start without them.

- Do not log raw document text, prompts, responses, keys, or passwords.
- Keep Langfuse content tracing off by default.
- Recheck the current supported Java-to-Langfuse path during `NFA-032`.
- Keep the minimum required trace integration inside an outbound adapter.
- Do not add a collector or a second tracing platform without a new approved story.
- Use bounded metric labels and never label by file, task, or workspace ID.
- Keep app metrics and Langfuse traces correlated by a safe request or task key.
- Kibana is only a future option if Elasticsearch gains a real use case.

## Story execution

Work on one story at a time and follow its acceptance criteria in order.

1. Name the next valid story, its scope, likely files, and checks.
2. Use the architect role for a cross-cutting story and present the plan.
3. Ask for clear approval of the plan.
4. After plan approval, ask again for clear approval to implement it.
5. Implement only after both approvals.
6. Run focused checks and show exact results.
7. Use the reviewer role when the change is cross-cutting or risky.
8. Propose one commit message and ask for clear commit approval.
9. Create the commit only after that approval and report its hash.
10. Ask before any push and before the next story.

Approval for one gate does not grant approval for another. Do not edit implementation files before implementation approval. Do not commit, push, continue, force-push, reset shared history, or rewrite commits without clear approval for that action.

## Story writing rules

- Use the sections and status values in `docs/story-workflow.md`.
- Keep a prose block at five sentences or less.
- Keep each criterion to one short, testable sentence.
- Use four to eight criteria.
- Keep `User story` plus `Goal` at Flesch Reading Ease 80 or more.
- Put long proof in the release `verification.md` file.
- Do not add issue or limitation sections to story files.

## Source comment rules

- Add a comment only when names and structure cannot make the reason clear.
- Explain why; do not narrate what the code does.
- Prefer one short sentence.
- Use at most three short sentences in one normal comment block.
- Use at most five short sentences in one Javadoc, JSDoc, or docstring.
- Do not paste plans, story text, history, or logs into source comments.
- Keep SPDX and required tool directives unchanged.

## Codex roles

Use the smallest useful role:

- `architect` is read-only and plans one story;
- `implementation_worker` implements one story after both approvals;
- `reviewer` is read-only and checks scope, proof, safety, and needless complexity.

Repository skills:

- `full-stack-delivery` for Angular, Spring Boot, HTTP, persistence, and Docker work;
- `ai-workflows` for LangChain4j, structured outputs, prompts, and provider boundaries;
- `file-workspace-safety` for files, ZIPs, extraction, storage, and exports;
- `design-handoff` for design tokens, Penpot MCP, UI states, and screenshots;
- `observability` for logs, Langfuse, Prometheus, Grafana, health, and recovery;
- `release-evidence` for stories, approval records, repository checks, and release proof.

Do not invoke every role or skill for a small documentation fix.

## License headers

New hand-written source files use:

```text
SPDX-FileCopyrightText: 2026 Keresztes Zsolt <https://kereszteszsolt.hu>
SPDX-License-Identifier: Apache-2.0
```

Keep the header at the start of Java, TypeScript, JavaScript, SCSS, Python, SQL, and shell source files. Do not add it to Markdown, JSON, TOML, Dockerfiles, Compose files, environment examples, ignore files, generated files, lock files, or binary assets.

## Verification

Run the repository audit before and after story work:

```bash
python3 .agents/skills/release-evidence/scripts/verify_repository.py
python3 -m unittest discover \
  -s .agents/skills/release-evidence/scripts \
  -p 'test_*.py'
```

After the owning stories exist, add the focused native checks shown in `docs/testing.md`. A release claim also needs both Compose configuration checks and a real-model smoke path for every supported Ollama mode.

## Release boundary

Releases 0.1 through 0.5 do not add LangGraph4j, application MCP integration, OpenRouter, another cloud provider, Elasticsearch, Kibana, Kubernetes, Helm, RAG, or automatic source-file edits. These are candidates, not promises.

Penpot MCP is different: when the user supplies a design link and the connection is available, it must be used as the read-first development and design handoff tool. It is not an application runtime feature.
