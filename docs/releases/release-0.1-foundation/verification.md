# Release 0.1: Foundation and first structured AI path verification

## Status

In progress. `NFA-001` through `NFA-005` are implemented, while the remaining Release 0.1 stories stay planned.

## Story evidence

| Story | Plan approved | Implementation approved | Focused checks | Review | Commit approved | Commit hash | Status |
| --- | --- | --- | --- | --- | --- | --- | --- |
| [`NFA-001`](stories/NFA-001-establish-the-repository-baseline.md) | Approved 2026-08-31 | Approved 2026-08-31 | Passed | Passed | Approved 2026-08-31 | `8b4d038` | Implemented |
| [`NFA-002`](stories/NFA-002-build-the-spring-boot-app-shell.md) | Approved 2026-08-31 | Approved 2026-08-31 | Passed | Passed | Approved 2026-08-31 | `9b4f4fb` | Implemented |
| [`NFA-003`](stories/NFA-003-build-the-angular-app-shell-and-token-flow.md) | Approved 2026-08-31 | Approved 2026-08-31 | Passed | Passed | Approved 2026-08-31 | `21f48fa` | Implemented |
| [`NFA-004`](stories/NFA-004-add-postgresql-and-flyway.md) | Approved 2026-08-31 | Approved 2026-08-31 | Passed | Passed | Approved 2026-08-31 | `f52e59e` | Implemented |
| [`NFA-005`](stories/NFA-005-add-docker-and-both-ollama-modes.md) | Approved 2026-08-31 | Approved 2026-08-31 | Passed | Passed | Pending | Pending | Implemented |
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

## NFA-002 focused checks

The maintainer approved the plan, implementation, and commit separately on 2026-08-31. Implementation commit `9b4f4fb` records Java 21, Spring Boot 4.1.1, the minimal API package boundaries, validated configuration, canonical brand loading, safe system and health HTTP contracts, one `ProblemDetail` boundary, ArchUnit guards, and focused tests; PostgreSQL, Angular, Docker Compose, LangChain4j, Ollama, and provider clients remain out of scope.

Acceptance criteria were checked in their listed order:

1. Maven Wrapper Plugin 3.3.4 generated the checked-in `bin` wrapper; its properties pin Maven 3.9.16 and the distribution SHA-256, while `./mvnw --version` reported Maven 3.9.16 and Eclipse Adoptium Java 21.0.12.
2. Seven production classes use the `io.nookforge` base package with live `bootstrap`, `shared.brand`, `shared.config`, `shared.error`, and `system.adapter.in.web` boundaries only.
3. Tests prove valid and invalid `nookforge.api.public-base-url` binding and strict brand loading; an ArchUnit rule rejects direct `System.getenv` access.
4. Mock MVC tests passed for liveness, readiness, and `/api/system/info`; health detail stays hidden and product data comes from the copied canonical `packages/brand/brand.json` resource.
5. Validation, missing-route, and unexpected-failure tests passed for stable `ProblemDetail` status, type, title, detail, instance, code, content type, and safe-message behavior.
6. Eight ArchUnit tests passed for domain, application, web, persistence, AI, field-injection, environment-read, and top-level package-cycle rules.
7. `./mvnw --batch-mode --no-transfer-progress verify` passed with 24 tests, 0 failures, 0 errors, and 0 skipped; Spotless kept 13 Java files clean, the executable JAR was repackaged, and JaCoCo passed its 80% gate with 66 of 70 lines covered, or 94.29%.
8. No empty layer, generic base service, field injection, or provider client exists; `./mvnw dependency:tree -Dscope=runtime` passed with only the Spring Boot Actuator, Validation, and Web MVC runtime graph and no PostgreSQL, JPA, LangChain4j, or Ollama dependency.

The repository audit passed with 3 agents, 6 skills, 36 stories, and valid local links. All 6 audit unit tests passed, `git diff --check` passed, and repository text rules normalize both Wrapper scripts and other tracked text to LF.

The Java 21 container emitted a non-failing Mockito dynamic-agent and Surefire dumpstream warning. The final Maven process still exited successfully with the exact passing counts above. A read-only review found four issues in source-of-truth wording, coverage evidence, framework 4xx handling, and URI validation; all four were fixed, their focused tests passed, and the re-review found no regression.

## NFA-003 focused checks

The maintainer approved the plan, implementation, and commit separately on 2026-08-31. Implementation commit `21f48fa` records the verified Angular shell and token flow; no Penpot link was supplied, so no Penpot structure, write, ID, export, or final handoff is claimed, and `NFA-007` still owns the first deterministic browser evidence.

Acceptance criteria were checked in their listed order:

1. Angular CLI 22.1.6 generated the standalone zoneless workspace with strict compiler settings, while `package.json` pins Node 24.20.0, npm 11.19.0, Angular 22.1.4, TypeScript 6.0.3, and every direct npm dependency without ranges; `package-lock.json` uses lockfile version 3.
2. Six unit tests passed for canonical identity, active navigation, the dashboard, `/tasks/new`, `/history`, explicit monitoring unavailability, the `**` fallback, and the privacy-safe visible bootstrap-error fallback.
3. Inspection found live feature code under `features/dashboard`, `features/tasks`, and `features/monitoring`, shared empty-state UI under `shared/ui`, and used layout, API, configuration, not-found, and startup-error boundaries under `core` without empty forwarding folders.
4. `npm run tokens:check` passed after the dependency-free generator compared `packages/design-tokens/tokens.json` with the checked `generated/tokens.css`; the Angular production build loads that CSS before application styles.
5. The generated CSS contains stable kebab-case `--nf-*` names for semantic color, type, space, radius, elevation, and motion groups, plus the small shell layout group.
6. Component and stylesheet inspection found a skip link, semantic header, navigation, and main landmarks, `aria-current`, text-backed status meaning, visible `:focus-visible`, a narrow two-column navigation state, empty states, explicit unavailable API text, and reduced-motion handling.
7. In the pinned Node container, `npm run lint` passed; `npm test` passed 2 files and 6 tests; both token and brand drift checks passed; and `npm run build` produced a 230.44 kB initial production bundle with five lazy route chunks.
8. `npm ls --depth=0` passed with the exact direct tree, while direct-package and source scans found no global state library, second UI component library, `fetch`, or `HttpClient` use.

The web checks used `node:24.20.0-bookworm-slim`, resolved at manifest digest `sha256:ba849c60be29959425b8734d57b8b4b7d56f98edd9504c9af091d5281095a71e`, with Node 24.20.0 and npm 11.19.0. The final direct tool set was Angular CLI and build tooling 22.1.6, Angular 22.1.4, TypeScript 6.0.3, Vitest 4.1.11, Angular ESLint 22.2.0, and ESLint 10.9.1.

`npm ci` emitted a non-failing npm 11 notice for four transitive packages whose install scripts are not covered by an explicit allow list. Lint, tests, drift checks, production compilation, and dependency-tree validation still exited successfully; no host Node installation was used, and the run-owned Linux dependency tree was removed from the Windows checkout after verification.

A read-only review found missing explicit strict compiler switches, a hidden bootstrap-failure state, and an unapproved Angular scaffold favicon. The fixes enabled TypeScript and Angular template strictness, added a tested visible privacy-safe startup fallback, removed the framework asset and raw theme color, and passed the full web suite and re-review without a remaining finding.

## NFA-004 focused checks

The maintainer approved the plan, implementation, and commit separately on 2026-08-31. Implementation commit `f52e59e` adds the PostgreSQL and Flyway foundation only; Compose, task, workspace, source-file, step, and artifact schemas remain outside this story.

Acceptance criteria were checked in their listed order:

1. The lifecycle test started the application first with `local` and then with `container` against PostgreSQL 18.6; Maven Enforcer rejected any direct or transitive H2 dependency, and the runtime tree contained PostgreSQL JDBC 42.7.13 with no H2.
2. Flyway 12.4.0 applied exactly `V1__create_installation_metadata.sql`; inspection found only `flyway_schema_history` and `installation_metadata` in the public schema and one successful V1 history row.
3. Five configuration tests passed for accepted coordinates and rejected empty-password, unsafe-host, invalid-port, and unsafe-database cases; profile files contain no password default, and application code performs no direct environment read.
4. A live HTTP check returned `200 {"status":"UP"}` with PostgreSQL available and `503 {"status":"DOWN"}` after it stopped; both responses omitted components, details, JDBC data, host, database, user, and the generated test password, while liveness stayed `UP`.
5. The installation domain value and outbound port stay independent of JPA; the entity and Spring Data repository are package-private in the persistence adapter, and 9 ArchUnit rules passed including the concrete-adapter boundary.
6. One run-owned Testcontainers scenario proved a clean database, migration, empty initial state, adapter write and read, duplicate rejection, Spring-context close and restart, retained data, safe health degradation, and explicit container cleanup; the post-run Testcontainers container query returned no resources.
7. The development guide now requires new `V<n>__short_description.sql` files, forbids editing applied migrations, keeps Flyway clean disabled, and requires a new forward-only repair migration.

The final Docker-hosted command ran Maven 3.9.16 on Java 21.0.12 and completed `clean verify` with 28 tests, 0 failures, 0 errors, and 0 skipped. Spotless kept 22 Java files clean, all 9 ArchUnit rules passed, and JaCoCo covered 114 of 118 lines, or 96.61%, with 21 of 26 branches covered.

The exact nested-Docker verification and cleanup checks were:

```bash
docker run --rm \
  --add-host=host.docker.internal:host-gateway \
  -e TESTCONTAINERS_HOST_OVERRIDE=host.docker.internal \
  -e TESTCONTAINERS_RYUK_DISABLED=true \
  -v /var/run/docker.sock:/var/run/docker.sock \
  -v nookforge-maven-cache:/root/.m2 \
  -v "$PWD":/workspace \
  -w /workspace/apps/api \
  maven:3.9.16-eclipse-temurin-21 \
  ./mvnw --batch-mode --no-transfer-progress clean verify

docker ps -a \
  --filter label=org.testcontainers=true \
  --format '{{.ID}} {{.Image}} {{.Status}}'
```

The Maven command passed with the counts above, and the cleanup query returned no rows.

The PostgreSQL image was `postgres:18.6-bookworm` at `postgres@sha256:1c59e2c3c818eaa0f0628f695b36e7c9e362d6b219b36a54a32df645cbd7e1af`. Spring Boot 4.1.1 managed Flyway 12.4.0, Testcontainers 2.0.5, and PostgreSQL JDBC 42.7.13.

Docker Desktop could not expose the Ryuk callback port to the nested Maven build container. The successful verification therefore used `TESTCONTAINERS_HOST_OVERRIDE=host.docker.internal`, disabled Ryuk for that build container only, and relied on the test's scoped `try` cleanup; a post-run Docker query confirmed that no Testcontainers resource remained. The expected database-down health probe logged a safe connection failure, and the existing non-failing Mockito dynamic-agent and Surefire dumpstream warnings remained.

The first read-only review found two low-severity documentation issues: the current-state adapter-to-port arrow was reversed, and the verification record omitted the exact nested-Docker commands. Both were fixed, `git diff --check` stayed clean, and the focused re-review found no remaining issue.

## NFA-005 focused checks

The maintainer approved the plan and implementation separately on 2026-08-31. NFA-005 adds only the two Docker deployment modes, runtime images, and validated Ollama settings; the LangChain4j client, first model call, and joined browser flow remain in `NFA-006` and `NFA-007`.

Acceptance criteria were checked in their listed order:

1. Resolved base configuration contained exactly `postgres`, `api`, and `web`; an isolated `nfa005-base-proof` stack made all three healthy and passed the configured external endpoint to the API.
2. Base configuration contained no Ollama service or model volume; an API-container request reached an independent Ollama fixture through `host.docker.internal` and returned HTTP 200 from `/api/version`.
3. The managed merge added exactly one official `ollama/ollama:0.32.14` service and one `ollama-models` volume mounted at `/root/.ollama`; `ollama list` stayed empty.
4. Resolved defaults mapped `127.0.0.1:11435` to `11434` and set the API endpoint to `http://ollama:11434`; the live test used non-conflicting port `21435` and returned version `0.32.14`.
5. Resolved web, API, PostgreSQL, and managed Ollama host ports used `127.0.0.1`; the API resolved `host.docker.internal=host-gateway` on Linux.
6. `.env.example` lists the current bind, port, database, and Ollama-only AI variables plus disabled audit-required Langfuse placeholders; `.env` and `.env.*` remain ignored and excluded from Docker build context.
7. Both Compose merges passed validation; PostgreSQL, API, web, and managed Ollama used focused healthchecks, while `depends_on` waited only for stack-owned services.
8. API and web filesystem scans found no `ollama`, `.ollama`, `.env`, or `.env.*`; image config and history contained no cloud provider, model, password, or API key, and the API ran as numeric non-root user `10001:10001`.

The primary isolated runtime commands were:

```bash
POSTGRES_PASSWORD=nfa005-validation-only \
OLLAMA_BASE_URL=http://host.docker.internal:21434 \
WEB_PORT=14200 API_PORT=18080 POSTGRES_PORT=15433 \
docker compose -p nfa005-base-proof \
  up --detach --wait --wait-timeout 180 --build

POSTGRES_PASSWORD=nfa005-validation-only \
WEB_PORT=24200 API_PORT=28080 POSTGRES_PORT=25433 \
OLLAMA_CONTAINER_PORT=21435 \
docker compose -p nfa005-managed-proof \
  -f docker-compose.yml \
  -f docker-compose.ollama.yml \
  up --detach --wait --wait-timeout 180 --build
```

The managed Ollama volume retained a synthetic marker after an Ollama container recreate, while its model list remained empty. Both test projects were removed with their containers, networks, and volumes; the independent `--rm` Ollama fixture and isolated npm dependency volume were also removed, and no Testcontainers container remained.

The final Java command ran Maven 3.9.16 on Java 21.0.12 and completed `clean verify` with 34 tests, 0 failures, 0 errors, and 0 skipped. Spotless kept 24 Java files clean, all 9 ArchUnit rules passed, and JaCoCo covered 126 of 130 lines, or 96.92%, with 36 of 52 branches covered.

The web command used `node:24.20.0-bookworm-slim` with an isolated `nfa005-web-node-modules` volume. Lint passed; Vitest passed 2 files and 6 tests; token and brand drift checks passed; and the production build emitted a 230.44 kB initial bundle.

Both `docker compose ... config --quiet` commands passed. The repository audit passed with 3 agents, 6 skills, 36 stories, and valid local links; all 6 audit unit tests and `git diff --check` passed.

Docker 29.6.1 and Compose 5.3.0 ran the proof. Registry digests were recorded for Ollama `sha256:9d30908e41144b1f1da89b9d8e33c07e4aeb43ff41a8660241b1686e2cc330ad`, Node `sha256:ba849c60be29959425b8734d57b8b4b7d56f98edd9504c9af091d5281095a71e`, Maven `sha256:8f6ac126f7810bb5549c4cd122d2bf0e9cda5bdeb0838aa928f09e779fd8bef8`, and PostgreSQL `sha256:1c59e2c3c818eaa0f0628f695b36e7c9e362d6b219b36a54a32df645cbd7e1af`. The builds resolved Temurin at `sha256:96975602e131485862eb8cd32927face8a06d7591a5e865944b634a701d9df72` and Nginx at `sha256:ddde39c6e51f02fde7410c2e9c234cf2d0a4c7bdbbe176aeb37d8ad7ab4eb58c`.

The first Docker build exposed the Maven image's `MAVEN_CONFIG` collision with the generated Wrapper; the Dockerfile now scopes that variable to empty for the Wrapper invocation, and the repeat build passed. The first full Java run passed all tests but stopped on two Spotless line wraps; the corrected final run passed. npm repeated the known non-failing warning about four transitive install scripts, and the database-down test emitted its expected safe health warning.

The first read-only review found no technical defect and requested two documentation consistency fixes: align the story and release evidence with the implemented state, and mark the Ollama environment contract current while keeping provider calls in `NFA-006`. Both fixes were applied, and the focused re-review found no remaining issue.

## Release-wide checks

Pending. Record exact commands, tool and model versions, short results, supported Compose modes, runtime fixtures, screenshots, and links to any large log files after all stories pass.

## Evidence rule

Do not mark a row implemented before all story criteria are checked and its proof is recorded. Plan approval, implementation approval, commit approval, push approval, and next-story approval are separate actions.
